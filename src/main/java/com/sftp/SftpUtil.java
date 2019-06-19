package com.sftp;

import com.jcraft.jsch.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class SftpUtil {

    private final static Logger log = LoggerFactory.getLogger(SftpUtil.class);

    /**
     * SFTP
     */
    public static final String SFTP = "sftp";
    /**
     * 通道
     */
    private ChannelSftp channel;
    /**
     * session
     */
    private Session session;

    /**
     * 规避多线程并发
     */
    private static ThreadLocal<SftpUtil> sftpLocal = new ThreadLocal<SftpUtil>();

    /**
     * 获取sftpchannel
     *
     * @param connectConfig 连接配置
     * @return
     * @throws Exception
     * @throws JSchException
     */
    private void init(ConnectConfig connectConfig) throws JSchException {
        String host = connectConfig.getHost();
        int port = connectConfig.getPort();

        String userName = connectConfig.getUserName();

        //创建JSch对象  
        JSch jsch = new JSch();

        //添加私钥(信任登录方式)  
        if (StringUtils.isNotBlank(connectConfig.getPrivateKey())) {
            jsch.addIdentity(connectConfig.getPrivateKey());
        }

        session = jsch.getSession(userName, host, port);
        if (log.isInfoEnabled()) {
            log.info(" JSCH Session created,sftpHost = {}, sftpUserName={}", host, userName);
        }

        //设置密码  
        if (StringUtils.isNotBlank(connectConfig.getPassWord())) {
            session.setPassword(connectConfig.getPassWord());
        }

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        //设置超时  
        session.setTimeout(connectConfig.getTimeout());
        //建立连接  
        session.connect();
        if (log.isInfoEnabled()) {
            log.info("JSCH Session connected.sftpHost = {}, sftpUserName={}", host, userName);
        }

        //打开SFTP通道  
        channel = (ChannelSftp) session.openChannel(SFTP);
        //建立SFTP通道的连接  
        channel.connect();
        if (log.isInfoEnabled()) {
            log.info("Connected successfully to sftpHost = {}, sftpUserName={}", host, userName);
        }
    }

    /**
     * 是否已连接
     *
     * @return
     */
    private boolean isConnected() {
        return null != channel && channel.isConnected();
    }

    /**
     * 获取本地线程存储的sftp客户端
     *
     * @return
     * @throws Exception
     */
    public static SftpUtil getSftpUtil(ConnectConfig connectConfig) throws Exception {
        SftpUtil sftpUtil = sftpLocal.get();
        if (null == sftpUtil || !sftpUtil.isConnected()) {
            sftpLocal.set(new SftpUtil(connectConfig));
        }
        return sftpLocal.get();
    }

    /**
     * 释放本地线程存储的sftp客户端
     */
    public static void release() {
        if (null != sftpLocal.get()) {
            sftpLocal.get().closeChannel();
            sftpLocal.set(null);
        }
    }

    /**
     * 构造函数
     * <p>
     * 非线程安全，故权限为私有
     * </p>
     *
     * @throws Exception
     */
    private SftpUtil(ConnectConfig connectConfig) throws Exception {
        super();
        init(connectConfig);
    }

    /**
     * 关闭通道
     *
     * @throws Exception
     */
    public void closeChannel() {
        if (null != channel) {
            try {
                channel.disconnect();
            } catch (Exception e) {
                log.error("关闭SFTP通道发生异常:", e);
            }
        }
        if (null != session) {
            try {
                session.disconnect();
            } catch (Exception e) {
                log.error("SFTP关闭 session异常:", e);
            }
        }
    }

    /**
     * 下载文件
     *
     * @param downDir 下载目录
     * @param src     源文件
     * @param dst     保存后的文件名称或目录
     * @throws Exception
     */
    public void downFile(String downDir, String src, String dst) throws Exception {
        channel.cd(downDir);
        channel.get(src, dst);
    }


    public void downloadFile(String remoteFile, String remotePath, String localFile) throws Exception {
        OutputStream output = null;
        File file = null;
        try {
            file = new File(localFile);
            output = new FileOutputStream(file);
            channel.cd(remotePath);
            channel.get(remoteFile, output);
        } catch (Exception e) {
            throw new Exception("Download file error.");
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    throw new Exception("Close stream error.");
                }
            }

        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件全路径
     * @throws SftpException
     */
    public void deleteFile(String filePath) throws SftpException {
        channel.rm(filePath);
    }

    @SuppressWarnings("unchecked")
    public List<String> listFiles(String dir) throws SftpException {
        Vector<ChannelSftp.LsEntry> files = channel.ls(dir);
        if (null != files) {
            List<String> fileNames = new ArrayList<String>();
            Iterator<ChannelSftp.LsEntry> iter = files.iterator();
            while (iter.hasNext()) {
                String fileName = iter.next().getFilename();
                if (StringUtils.equals(".", fileName) || StringUtils.equals("..", fileName)) {
                    continue;
                }
                fileNames.add(fileName);
            }
            return fileNames;
        }
        return null;
    }

    /**
     * 上传单个文件
     *
     * @param remotePath：远程保存目录
     * @param remoteFileName：保存文件名
     * @param localPath：本地上传目录(以路径符号结束)
     * @param localFileName：上传的文件名
     * @return
     */
    public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FileInputStream in = null;
        try {
            createDir(remotePath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            channel.put(in, remoteFileName);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 创建目录
     *
     * @param createpath
     * @return
     */
    public boolean createDir(String createpath) {
        try {
            if (isDirExist(createpath)) {
                channel.cd(createpath);
                return true;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString())) {
                    channel.cd(filePath.toString());
                } else {
                    // 建立目录
                    channel.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    channel.cd(filePath.toString());
                }

            }
            channel.cd(createpath);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断目录是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = channel.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

}  