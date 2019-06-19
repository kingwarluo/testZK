package com.设计模式.builder;

import com.设计模式.builder.annotation.StringValidate;
import com.设计模式.builder.annotation.Validate;
import com.设计模式.builder.enums.MsgTypeEnum;

import java.io.Serializable;

/**
 * 消息父类
 * @author kingwarluo
 * @date 2019/1/11 16:27
 */
abstract class BaseMessage implements Message, Cloneable, Serializable {

    /** 消息UUID */
    private String uuid;
    /** 类别编码 */
    @StringValidate(blank = true)
    private String typeCode;
    /**  消息类别 {@link MsgTypeEnum#getCode()} */
    @StringValidate(blank = true)
    private String msgType;
    /** 标题*/
    @StringValidate(blank = true)
    private String title;
    /** 跳转链接 */
    private String url;
    /** 描述 */
    private String description;
    /** 图片url */
    private String imgUrl;
    /** 统跳内容 */
    private String jumpItem;
    /** 统跳编码 */
    private String jumpCode;
    /** 消息内容 */
    private String content;
    /** 创建人ID */
    @Validate(nullable = true)
    private Long creatorId;
    /** 修改人ID */
    @Validate(nullable = true)
    private Long modifierId;
    /** 业务穿透参数 */
    private String expansion;
    /** 消息业务类型 */
    @Validate(nullable = true)
    private Integer msgBizType;
    /** 是否推送 */
    private boolean push;

    @Override
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getJumpItem() {
        return jumpItem;
    }

    public void setJumpItem(String jumpItem) {
        this.jumpItem = jumpItem;
    }

    public String getJumpCode() {
        return jumpCode;
    }

    public void setJumpCode(String jumpCode) {
        this.jumpCode = jumpCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public Integer getMsgBizType() {
        return msgBizType;
    }

    public void setMsgBizType(Integer msgBizType) {
        this.msgBizType = msgBizType;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    @Override
    protected BaseMessage clone(){
        try {
            return (BaseMessage) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "uuid='" + uuid + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", msgType='" + msgType + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", jumpItem='" + jumpItem + '\'' +
                ", jumpCode='" + jumpCode + '\'' +
                ", content='" + content + '\'' +
                ", creatorId=" + creatorId +
                ", modifierId=" + modifierId +
                ", expansion='" + expansion + '\'' +
                ", msgBizType=" + msgBizType +
                ", push=" + push +
                '}';
    }
}
