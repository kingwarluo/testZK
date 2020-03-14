//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.es;

import java.io.Serializable;
import java.util.List;

public class MemberListDto implements Serializable {
    private static final long serialVersionUID = -3854875282717466374L;
    private Integer id;
    private Integer sex;
    private String memberName;
    private String levelName;
    private Integer levelCode;
    private Integer employeeId;
    private String mobile;
    private String tel;
    private Integer visitStoreNum;
    private Integer trackeNum;
    private String plan_visit_time;
    private String createTime;
    private String defeatReasonCode;
    private List<String> defeatReason;
    private String trackerTime;
    private Integer blackList;
    private Integer isCross;
    private Integer trackeType;
    private Integer intelligence;
    private Integer status;
    private Integer isTrackedByMe;
    private Integer salesConsultantId;
    private String salesConsultantName;
    private String consultTime;

    public MemberListDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getSalesConsultantId() {
        return this.salesConsultantId;
    }

    public void setSalesConsultantId(Integer salesConsultantId) {
        this.salesConsultantId = salesConsultantId;
    }

    public String getSalesConsultantName() {
        return this.salesConsultantName;
    }

    public void setSalesConsultantName(String salesConsultantName) {
        this.salesConsultantName = salesConsultantName;
    }

    public Integer getIsTrackedByMe() {
        return this.isTrackedByMe;
    }

    public void setIsTrackedByMe(Integer isTrackedByMe) {
        this.isTrackedByMe = isTrackedByMe;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getVisitStoreNum() {
        return this.visitStoreNum;
    }

    public void setVisitStoreNum(Integer visitStoreNum) {
        this.visitStoreNum = visitStoreNum;
    }

    public String getPlan_visit_time() {
        return this.plan_visit_time;
    }

    public void setPlan_visit_time(String plan_visit_time) {
        this.plan_visit_time = plan_visit_time;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getTrackeNum() {
        return this.trackeNum;
    }

    public void setTrackeNum(Integer trackeNum) {
        this.trackeNum = trackeNum;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelCode() {
        return this.levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    public String getTrackerTime() {
        return this.trackerTime;
    }

    public void setTrackerTime(String trackerTime) {
        this.trackerTime = trackerTime;
    }

    public Integer getBlackList() {
        return this.blackList;
    }

    public void setBlackList(Integer blackList) {
        this.blackList = blackList;
    }

    public Integer getIsCross() {
        return this.isCross;
    }

    public void setIsCross(Integer isCross) {
        this.isCross = isCross;
    }

    public Integer getTrackeType() {
        return this.trackeType;
    }

    public void setTrackeType(Integer trackeType) {
        this.trackeType = trackeType;
    }

    public Integer getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDefeatReasonCode() {
        return this.defeatReasonCode;
    }

    public void setDefeatReasonCode(String defeatReasonCode) {
        this.defeatReasonCode = defeatReasonCode;
    }

    public List<String> getDefeatReason() {
        return this.defeatReason;
    }

    public void setDefeatReason(List<String> defeatReason) {
        this.defeatReason = defeatReason;
    }

    public String getConsultTime() {
        return this.consultTime;
    }

    public void setConsultTime(String consultTime) {
        this.consultTime = consultTime;
    }

    public String toString() {
        return "MemberListDto [id=" + this.id + ", memberName=" + this.memberName + ", levelName=" + this.levelName + ", levelCode=" + this.levelCode + ", employeeId=" + this.employeeId + ", mobile=" + this.mobile + ", tel=" + this.tel + ", visitStoreNum=" + this.visitStoreNum + ", trackeNum=" + this.trackeNum + ", plan_visit_time=" + this.plan_visit_time + ", createTime=" + this.createTime + ", failReason=" + this.defeatReasonCode + ", trackerTime=" + this.trackerTime + ", blackList=" + this.blackList + ", isCross=" + this.isCross + ", trackeType=" + this.trackeType + ", intelligence=" + this.intelligence + "]";
    }
}
