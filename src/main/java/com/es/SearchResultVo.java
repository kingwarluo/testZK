//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.es;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

public class SearchResultVo implements Serializable {
    private static final long serialVersionUID = 3931088875852248184L;
    private int total;
    private List<SearchResultDetailVO> results;
    private JSONObject originalData;
    private String scrollId;

    public SearchResultVo() {
    }

    public String getScrollId() {
        return this.scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SearchResultDetailVO> getResults() {
        return this.results;
    }

    public void setResults(List<SearchResultDetailVO> results) {
        this.results = results;
    }

    public JSONObject getOriginalData() {
        return this.originalData;
    }

    public void setOriginalData(JSONObject originalData) {
        this.originalData = originalData;
    }

    public Object getValueByPath(String path) {
        return getValueByPath(this.originalData, path);
    }

    public String toString() {
        return "SearchResultVo [total=" + this.total + ", results=" + this.results + ", originalData=" + this.originalData + "]";
    }

    public static Object getValueByPath(JSONObject obj, String path) {
        String[] keys = path.split("/");
        Object tem = obj;
        String[] arr$ = keys;
        int len$ = keys.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String key = arr$[i$];
            if (!(tem instanceof JSONObject)) {
                return null;
            }

            tem = ((JSONObject)tem).get(key);
        }

        return tem;
    }
}
