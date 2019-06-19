package com.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpTest {

    private static FTPClient ftpClient;

    public static void main(String[] args) {
        InputStream is = null;
        try {
            if(connect()){
                // 设置目录
                String path = "/var/ftp/ftpuser/upload";
                // 检查上传路径是否存在，不存在返回false
                boolean isExists = ftpClient.changeWorkingDirectory(path);
                if(!isExists){
                    ftpClient.makeDirectory(path);
                }
                ftpClient.changeWorkingDirectory(path);
                File file = new File("C:\\Users\\Administrator\\Desktop\\短信RPC测试.docx");
                is = new FileInputStream(file);
                boolean flag = ftpClient.storeFile(new String(file.getName().getBytes("UTF-8"),"iso-8859-1"), is);
//                boolean flag = ftpClient.storeFile(file.getName(), is);
                System.out.println(flag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean connect(){
        try {
            ftpClient = new FTPClient();
            ftpClient.connect("192.168.254.128", 21);
            ftpClient.setDataTimeout(10000);
            int code = ftpClient.getReplyCode();
            if(FTPReply.isPositiveCompletion(code)){
                if(ftpClient.login("ftpuser", "ftpuser")){
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.setControlEncoding("UTF-8");
                    ftpClient.enterLocalPassiveMode();
                }
                return true;
            }else{
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
