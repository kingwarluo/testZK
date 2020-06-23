package com.es;

import com.google.common.collect.Maps;
import org.elasticsearch.search.sort.SortOrder;

import java.util.Map;

/**
 * Created by lijizhong on 2017/9/6.
 */
public enum EsMemberSortTypeEnums {
    SORT_DESC(0, SortOrder.DESC),
    SORT_ASC(1, SortOrder.ASC);
    private Integer value;
    private SortOrder sortOrder;  
    
    EsMemberSortTypeEnums(Integer value, SortOrder sortOrder) {
        this.value = value;
        this.sortOrder = sortOrder;
    }

    private static Map<Integer, EsMemberSortTypeEnums> SORT_TYPE_MAP = Maps.newHashMap();
    static {
        for (EsMemberSortTypeEnums typeEnums : EsMemberSortTypeEnums.values()) {
            SORT_TYPE_MAP.put(typeEnums.getValue(), typeEnums);
        }
    }

    public static EsMemberSortTypeEnums valueof(Integer value) {
        return SORT_TYPE_MAP.get(value);
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}
