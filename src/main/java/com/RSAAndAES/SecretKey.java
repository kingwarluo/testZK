package com.RSAAndAES;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * 秘钥管理类,统一的秘钥存储位置
 * <p>
 *
 * @author jianhua.luo
 * @since 17/2/18
 */
public class SecretKey {


    /** 静态秘钥,应安全组要求,所有秘钥存入数据库时需要进行二次加密 */
    public static final String STATIC_KEY_KEY = "fa@324*lk8=%94d6";

    /** 动态秘钥握手阶段加密秘钥使用的RSA公钥 */
    public static final String DYNAMIC_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQUi8ycUr+p1rlRucHmuDaa6QcCY/gEN" +
    "qHxHt3QcqRWoMHj63ZjVEpXcIRG9Nu5fdknIsoxzAG1gQQsNZh0sfCBxn1VfAtYiU6OLXHWNR/485jzinfOWADEcVNk8W+U17SFyKcoWyO38Ry0PkTvHiU0h" +
    "A3sbIwbn5C1BRwrX/7JwIDAQAB";


    /** 动态秘钥握手阶段解密秘钥使用的RSA私钥 */
    public static final String DYNAMIC_PRIVATE_KEY =  "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANBSLzJxSv6nWuVG5wea4NprpBwJj+AQ2ofEe3dBypFagwePrdmNUSldw"
    + "hEb027l92SciyjHMAbWBBCw1mHSx8IHGfVV8C1iJTo4tcdY1H/jzmPOKd85YAMRxU2Txb5TXtIXIpyhbI7fxHLQ+RO8eJTSEDexsjBufkLUFHCtf/snAgMBAAECgYBiXTv95HLV1W"
    + "3c7uKtu5rKZ6Mgvf8afLrC+qqVwBIHGibejiA3pjuZfbTh4TKvnZR4BFbNFWh/AdT/kJBZtm4tPE69wtqUULdb5dMn21aFn2mfblk5qsdfyBYhnvHCznpQxYaouySQK7F4sUvUEL+dU"
    + "pQog7lyY+OO55vnAr/oOQJBAPMtEjJ0byiyXRFuPuRlyuB9S6jlqRfgFu6toz+Gta5XcNpAW5d1HeNFqGG8KdQPiGEk/GCi01nSq1gJ8Mgh4n0CQQDbTpAd4UGoxxVl9igdWbYQaz"
    + "RSoPT/4QG1ZYfjl9f9nMI4cjB6gasCWvdyzJd5Nfa9dmt5JmkXCWXnlHm8U8FzAkAK1LwVwTfeQSuCBj4I4DvMCeifyjLg3FtJIDTANJjM/hoJrool0dKV99lLGR1n05VIdmRDTZ1KR"
    + "+bX0gGGeHPlAkAGDNTM/jnhfhyJA5F3vdHYAh0fanFX3c0+Q97L2JSM2pZBGGVd+9XL6taIxDwpi+OStoGghf7AM/IrwVzaZTUhAkEA5WCeg19WEspVtYnOGr45jePE93p854zv"
    + "dbtGhqkM5x7JYLE+tcAJwzpaRMWT0TdXzGdiyEbWgVm0t+vv/3pEQA==";

}
