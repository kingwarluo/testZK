package com.sftp;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.List; 
import java.util.Properties; 
import java.util.Vector; 
   
import org.apache.log4j.Logger; 
   
import com.jcraft.jsch.Channel; 
import com.jcraft.jsch.ChannelSftp; 
import com.jcraft.jsch.JSch; 
import com.jcraft.jsch.Session; 
import com.jcraft.jsch.SftpATTRS; 
import com.jcraft.jsch.SftpException; 

public class SFTPUtils {

    private static Logger log = Logger.getLogger(SFTPUtils.class.getName());

    /**
     * 服务器连接ip
     */
    private String host;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 端口号
     */
    private int port = 22;
    private static ChannelSftp sftp = null;
    private Session sshSession = null;

    public SFTPUtils() {
    }

    public SFTPUtils(String host, int port, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * 通过SFTP连接服务器
     */
    public void connect() {
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            if (log.isInfoEnabled()) {
                log.info("Session created.");
            }
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            if (log.isInfoEnabled()) {
                log.info("Session connected.");
            }
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            if (log.isInfoEnabled()) {
                log.info("Opening Channel.");
            }
            sftp = (ChannelSftp) channel;
            if (log.isInfoEnabled()) {
                log.info("Connected to " + host + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                if (log.isInfoEnabled()) {
                    log.info("sftp is closed already");
                }
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                if (log.isInfoEnabled()) {
                    log.info("sshSession is closed already");
                }
            }
        }
    }


}