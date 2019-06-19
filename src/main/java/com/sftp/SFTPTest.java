package com.sftp;

/**
 * @author KingWarLuo（jianhua.luo）
 * @description: ${description}
 * @date 2018-10-15 12:33
 */
public class SFTPTest {

    private static String G1_IP = "119.29.213.240";
    private static int G1_PORT = 2216;
    private static String G1_USERNAME = "";
    private static String G1_PASSWORD = "";

    public static void main(String[] args)
    {
        SFTPUtils sftp = null;
        try {
            // 现在后台的SFTP的账户信息
            sftp = new SFTPUtils(G1_IP, G1_PORT, G1_USERNAME, G1_PASSWORD);
            sftp.connect();
            // 下载
            //boolean flag =  sftp.uploadFile("/test/", "201708081138_o7Lpot_9nrAvyz2dbLFbq7ftn374_ba89d4.jpg", "F:/", "201708081138_o7Lpot_9nrAvyz2dbLFbq7ftn374_ba89d4.jpg");  //上传文件
            //System.out.println(flag);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            sftp.disconnect();
        }
    }

}
