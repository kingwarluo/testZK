package com.mysqlbinlog;

import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.deserialization.ChecksumType;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

import java.io.File;
import java.io.IOException;

public class Sample {

    public static void main(String[] args) throws IOException {
        String filePath="C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Data\\mysql-bin.000005";
        File binlogFile = new File(filePath);
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setChecksumType(ChecksumType.CRC32);
        BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile, eventDeserializer);
        try {
            for (Event event; (event = reader.readEvent()) != null; ) {
                System.out.println(event.toString());
            }
        } finally {
            reader.close();
        }


    }

}
