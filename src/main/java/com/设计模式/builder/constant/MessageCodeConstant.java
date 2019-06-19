package com.设计模式.builder.constant;

/**
 * 消息常量类
 * @author kingwarluo
 * @date 2019/1/11 16:27
 */
public class MessageCodeConstant {

    /** 成功 */
    public static final int SUCCESS = 1;

    /** 失败 */
    public static final int FAILURE = 0;

    /** 校验失败 */
    public static final int VALIDATE_FAILURE = 10001;

    /** 参数错误 */
    public static final int PARAMETER_ERROR = 10002;

    /** 发送MQ失败 */
    public static final int SEND_MQ_FAILURE = 10003;

    /** MQ数据源未创建 */
    public static final int MQ_DATA_SOURCE_NOT_FOUND = 10004;

    private MessageCodeConstant(){}

}
