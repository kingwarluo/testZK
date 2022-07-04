package com.mysqlbinlog.sync;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chenwk
 * @version 1.0.0
 * @ClassName BinlogDTO
 * @Description BinlogDTO 对象
 * @createTime 2019年11月25日 11:04
 */
@Data
@AllArgsConstructor
public class BinlogDTO {
    private String event;
    private Object value;
}
