//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.es;

import java.io.Serializable;

public class SearchResultDetailVO implements Serializable {
    private static final long serialVersionUID = -2205371623274485147L;
    private String index;
    private String type;
    private String id;
    private Float score;
    private Long version;
    private String result;
    private String highlight;

    public SearchResultDetailVO() {
    }

    public String getHighlight() {
        return this.highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getScore() {
        return this.score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String toString() {
        return "SearchResultDetailVO{index='" + this.index + '\'' + ", type='" + this.type + '\'' + ", id='" + this.id + '\'' + ", score=" + this.score + ", version=" + this.version + ", result='" + this.result + '\'' + ", highlight='" + this.highlight + '\'' + '}';
    }
}
