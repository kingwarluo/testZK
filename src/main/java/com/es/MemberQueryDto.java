//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.es;

import java.io.Serializable;

public class MemberQueryDto implements Serializable {
    private static final long serialVersionUID = 7133596222484311057L;
    private Integer id;
    private Integer memberId;
    private String memberName;
    private Integer pageNum = 1;
    private int orderBy = 0;
    private int orderMember = 1;
    private int orderType = 0;
    private String orderSelect;
    private Integer pageSize = 20;
    private Integer startIndex;
    private Integer endIndex;
    private Integer userId;
    private Integer deptId;
    private String mobile;
    private Integer areaId;
    private String source;
    private Integer typeCode;
    private String levelCode;
    private Integer status;
    private Integer isLookcar;
    private String createStartTime;
    private String createEndTime;
    private String trackerStartTime;
    private String trackerEndTime;
    private String planTrackerStartTime;
    private String planTrackerEndTime;
    private Integer minBudget;
    private Integer maxBudget;
    private String defeatReason;
    private Integer blackList;
    private Integer hidden;
    private Integer userRoleType;

    public MemberQueryDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getUserRoleType() {
        return this.userRoleType;
    }

    public void setUserRoleType(Integer userRoleType) {
        this.userRoleType = userRoleType;
    }

    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getIsLookcar() {
        return this.isLookcar;
    }

    public void setIsLookcar(Integer isLookcar) {
        this.isLookcar = isLookcar;
    }

    public String getCreateStartTime() {
        return this.createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return this.createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getTrackerStartTime() {
        return this.trackerStartTime;
    }

    public void setTrackerStartTime(String trackerStartTime) {
        this.trackerStartTime = trackerStartTime;
    }

    public String getTrackerEndTime() {
        return this.trackerEndTime;
    }

    public void setTrackerEndTime(String trackerEndTime) {
        this.trackerEndTime = trackerEndTime;
    }

    public Integer getMinBudget() {
        return this.minBudget;
    }

    public void setMinBudget(Integer minBudget) {
        this.minBudget = minBudget;
    }

    public Integer getMaxBudget() {
        return this.maxBudget;
    }

    public void setMaxBudget(Integer maxBudget) {
        this.maxBudget = maxBudget;
    }

    public String getDefeatReason() {
        return this.defeatReason;
    }

    public void setDefeatReason(String defeatReason) {
        this.defeatReason = defeatReason;
    }

    public int getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public int getOrderType() {
        return this.orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Integer getStartIndex() {
        if (this.pageNum >= 1) {
            this.startIndex = this.pageSize * (this.pageNum - 1);
        }

        return this.startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        this.endIndex = this.pageSize;
        return this.endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public String getOrderSelect() {
        if (this.orderMember == 1) {
            if (this.orderBy == 0) {
                if (this.orderType == 0) {
                    this.orderSelect = "mt.plan_tracker_time  desc";
                } else {
                    this.orderSelect = "mt.plan_tracker_time";
                }
            } else if (this.orderBy == 1) {
                if (this.orderType == 0) {
                    this.orderSelect = "m.create_date  desc";
                } else {
                    this.orderSelect = "m.create_date";
                }
            }
        } else if (this.orderBy == 0) {
            if (this.orderType == 0) {
                this.orderSelect = "mt.tracker_date  desc";
            } else {
                this.orderSelect = "mt.tracker_date";
            }
        } else if (this.orderBy == 1) {
            if (this.orderType == 0) {
                this.orderSelect = "m.create_date  desc";
            } else {
                this.orderSelect = "m.create_date";
            }
        }

        return this.orderSelect;
    }

    public void setOrderSelect(String orderSelect) {
        this.orderSelect = orderSelect;
    }

    public Integer getBlackList() {
        return this.blackList;
    }

    public void setBlackList(Integer blackList) {
        this.blackList = blackList;
    }

    public Integer getHidden() {
        return this.hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Integer getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public int getOrderMember() {
        return this.orderMember;
    }

    public void setOrderMember(int orderMember) {
        this.orderMember = orderMember;
    }

    public String getLevelCode() {
        return this.levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getPlanTrackerStartTime() {
        return this.planTrackerStartTime;
    }

    public void setPlanTrackerStartTime(String planTrackerStartTime) {
        this.planTrackerStartTime = planTrackerStartTime;
    }

    public String getPlanTrackerEndTime() {
        return this.planTrackerEndTime;
    }

    public void setPlanTrackerEndTime(String planTrackerEndTime) {
        this.planTrackerEndTime = planTrackerEndTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
