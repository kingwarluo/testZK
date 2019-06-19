package com.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hbase2_0_1 {

    private Configuration configuration = null;
    private Connection connection = null;
    private static final String TABLE_NAME = "dept";
    private static final String[] FAMILY_NAMES = {"info", "subdept"};
    private static final String[] COLUMN_NAMES = {"age", "name"};

    @Before
    public void before() throws IOException {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "192.168.254.130");// zookeeper地址
        configuration.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
        connection = ConnectionFactory.createConnection(configuration);
    }


    public static void main(String[] args) {
        StringBuilder a = new StringBuilder("1");
        Hbase2_0_1 hbase2_0_1 = new Hbase2_0_1();
        hbase2_0_1.add(a);
        System.out.println("final:" + a.toString());
    }

    public void add(StringBuilder b){
        b.append("2");
        System.out.println("method:" + b.toString());
    }

    @Test
    public void createTable() throws IOException {
        // 创建表管理类
        Admin admin = connection.getAdmin();
        if(admin.isTableAvailable(TableName.valueOf(TABLE_NAME))){
            TableDescriptorBuilder tableDesc = TableDescriptorBuilder.newBuilder(TableName.valueOf(TABLE_NAME));
            for(String familyName : FAMILY_NAMES){
                tableDesc.setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familyName)).build());
            }
            admin.createTable(tableDesc.build());
        }
    }

    @Test
    public void addDataRow() {
        try {
            Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
            Put put = new Put(Bytes.toBytes("10001"));
            // 不写wlan日志
            put.setDurability(Durability.SKIP_WAL);
            // 获取表中的所有列族 client version 2.0.1
            ColumnFamilyDescriptor[] columnFamilyDescriptors = table.getDescriptor().getColumnFamilies();
            for (ColumnFamilyDescriptor columnFamilyDescriptor : columnFamilyDescriptors) {
                String familyName = columnFamilyDescriptor.getNameAsString();
                if (familyName.equals("info")) {
                    for (int i = 0; i < COLUMN_NAMES.length; i++) {
                        // 列族
                        put.addColumn(Bytes.toBytes(familyName),
                                // 列
                                Bytes.toBytes(COLUMN_NAMES[i]),
                                // 列的值
                                Bytes.toBytes(12312));
                    }
                }
            }
            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
        public void getRow(){
        try {
            Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
            Get get = new Get(Bytes.toBytes("10001"));
            for (String column : COLUMN_NAMES) {
                get.addColumn(Bytes.toBytes("info"), Bytes.toBytes(column));
            }
            Result result = table.get(get);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void editRow(){
        try {
            Table table = connection.getTable(TableName.valueOf(TABLE_NAME));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteRow(){
        try {
            Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
            List<Delete> deleteList = new ArrayList<Delete>();
            Delete delete = new Delete(Bytes.toBytes("10001"));
            deleteList.add(delete);
            table.delete(deleteList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
