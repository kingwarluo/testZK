package com.mysqlbinlog.sync;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/7/4 16:14
 */
@Slf4j
public class BinlogHandler {

    public void startBinLogListener() throws IOException {
        log.info("监听程序开始启动...");
        BinaryLogClient client = new BinaryLogClient("101.43.122.53", 3306,
                "root", "123456");

        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        HashMap<Long, String> tableMap = new HashMap<>();
        client.setServerId(1);
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(new BinaryLogClient.EventListener() {
            @Override
            public void onEvent(Event event) {
                // binlog事件
                EventData data = event.getData();
                if (data != null) {
                    if (data instanceof TableMapEventData) {
                        TableMapEventData tableMapEventData = (TableMapEventData) data;
                        tableMap.put(tableMapEventData.getTableId(), tableMapEventData.getDatabase() + "." + tableMapEventData.getTable());
                    }
                    // update数据
                    if (data instanceof UpdateRowsEventData) {
                        UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) data;
                        String tableName = tableMap.get(updateRowsEventData.getTableId());
                        if (filterData(tableName)) {
                            String eventKey = tableName + ".update";
                            for (Map.Entry<Serializable[], Serializable[]> row : updateRowsEventData.getRows()) {
                                String msg = JSON.toJSONString(new BinlogDTO(eventKey, row.getValue()));
                                BinlogDTO binlogDTO = JSON.parseObject(msg, BinlogDTO.class);
                                JSONArray value = (JSONArray) binlogDTO.getValue();
                                try {
                                    log.info(new String(Base64.decode(value.getString(1)), "utf-8"));
                                } catch (Base64DecodingException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                log.info("update data : {}", msg);
                            }
                        }
                    }
                    // insert数据
                    else if (data instanceof WriteRowsEventData) {
                        WriteRowsEventData writeRowsEventData = (WriteRowsEventData) data;
                        String tableName = tableMap.get(writeRowsEventData.getTableId());
                        if (filterData(tableName)) {
                            String eventKey = tableName + ".insert";
                            for (Serializable[] row : writeRowsEventData.getRows()) {
                                String msg = JSON.toJSONString(new BinlogDTO(eventKey, row));
                                log.info("insert data : {}", msg);
                            }
                        }
                    } // delete数据
                    else if (data instanceof DeleteRowsEventData) {
                        DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) data;
                        String tableName = tableMap.get(deleteRowsEventData.getTableId());
                        if (filterData(tableName)) {
                            String eventKey = tableName + ".delete";
                            for (Serializable[] row : deleteRowsEventData.getRows()) {
                                String msg = JSON.toJSONString(new BinlogDTO(eventKey, row));
                                log.info("delete data : {}", msg);
                            }
                        }
                    }
                }
            }
        });
        log.info("监听程序已启动...");
        client.connect();
    }

    private boolean filterData(String tableName){
        log.info("filterData current tableName : {}",tableName);
        if(tableName == null){
            return false;
        }
        TableFilter tableFilter = new TableFilter();
        String database = tableName.split(",")[0];
        String allTable = database+".*";
        List includes = Arrays.asList(tableFilter.getIncludes().split(","));
        List excludes = Arrays.asList(tableFilter.getExcludes().split(","));
        if(excludes.contains(allTable)){
            return false;
        }
        if(excludes.contains(tableName)){
            return false;
        }
        if(includes.contains(allTable)){
            return true;
        }
        if(includes.contains(tableName)){
            return true;
        }
        return true;
    }

}
