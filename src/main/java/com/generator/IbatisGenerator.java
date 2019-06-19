package com.generator;

import com.generator.plus.Generator;

/**
 * @description:ibatis生成器
 *
 * @author jianhua.luo
 * @date 2019/5/22
 */
public class IbatisGenerator {

    private static String DB_IP = "10.104.50.25"; //数据库IP

    private static Integer DB_PORT = 3306; //数据库端口

    private static String DB_NAME = "mmc_sic"; //数据库名称

    private static String DB_USER = "mmcsic_r"; //访问用户名

    private static String DB_PWD = "wpwT8WmHtfH5Y4Rs"; //访问密码

    private static String PROJECT_PATH = "mmcsic-service"; //项目绝对路径

    private static String REPOSITORY_PACKAGE = "com.mmc.sic.service.modules.repository"; //数据库生成基本包

    private static String[] FILTER_TABLE = new String[]{
            "t_sic_history_record",
            "t_sic_info",
            "t_sic_follow_record",
            "t_sic_turnover_intention",
            "t_sic_giveup_reason",
            "t_sic_expire"
    };

    public static void main(String[] args) {
        Generator.build().ip(DB_IP)
                .port(DB_PORT)
                .database(DB_NAME)
                .user(DB_USER)
                .password(DB_PWD)
                .projectPath(PROJECT_PATH)
                .repositoryPackage(REPOSITORY_PACKAGE)
                .addTables(FILTER_TABLE)
                .lombokEnable(true) //是否启用lombok插件，建议开启，可以简化代码
                .baseClassPackage("com.mmc.sic.service.api.base")  //基础类包，一个项目使用一套就好
                .repositoryPackage("com.mmc.sic.service.modules.repository")  //模块包名,生成的domain和repository都在这个目录下
                .domainPackage("com.mmc.sic.service.modules.domain")
                .gernerate(); //开始生成
    }

}
