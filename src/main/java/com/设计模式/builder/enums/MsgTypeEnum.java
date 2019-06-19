package com.设计模式.builder.enums;

/**
 * description: 消息类别
 * @author dongdongliu
 * @version 1.0
 * @date 2018-08-01 17:46:11
 */
public enum MsgTypeEnum {
    /**
     * 文本
     */
    TEXT("TEXT", "文本"),

    /**
     * 图文
     */
    IMGTEXT("IMGTEXT", "图文"),

    /**
     * 链接
     */
    URL("URL", "链接"),
    /**
     * native链接
     */
    NATIVEURL("NATIVEURL", "native链接"),

    /**
     * 图片
     */
    IMAGE("IMAGE", "图片");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    private MsgTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * description:<通过code获取枚举对象>
     * @param code
     * @return <com.uc.base.enums.mams.MsgTypeEnum>
     * @author dongdongliu
     * @date 2018/8/1 20:17
     */
    public static MsgTypeEnum getEnumByCode(String code) {
        for (MsgTypeEnum msgTypeEnum : MsgTypeEnum.values()) {
            if (msgTypeEnum.code.equals(code)) {
                return msgTypeEnum;
            }
        }
        return null;
    }
}
