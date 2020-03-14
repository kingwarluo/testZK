//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.es;

import java.io.Serializable;
import java.util.List;

public class MemberPaginationDto implements Serializable {
    private static final long serialVersionUID = 6721468905667013592L;
    private int totalPage;
    private int curpage = 1;
    private int listSize;
    private int pageSize = 20;
    private List<MemberListDto> memberList;

    public MemberPaginationDto() {
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurpage() {
        return this.curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getListSize() {
        return this.listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<MemberListDto> getMemberList() {
        return this.memberList;
    }

    public void setMemberList(List<MemberListDto> memberList) {
        this.memberList = memberList;
    }
}
