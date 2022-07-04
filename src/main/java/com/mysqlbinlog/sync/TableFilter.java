package com.mysqlbinlog.sync;

import lombok.Data;

@Data
public class TableFilter {
    private String includes = "tb_shop";
    private String excludes = "";
}
