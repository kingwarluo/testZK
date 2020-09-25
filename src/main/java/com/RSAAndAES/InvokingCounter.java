package com.RSAAndAES;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用计数器
 * @author jianhua.luo
 * @date 2020-09-25
 */
public class InvokingCounter {

    /** 日志记录 */
    private static final Logger LOGGER = LoggerFactory.getLogger(InvokingCounter.class);

    private static final String nameSpace = "redis_serial";

    /**
     * 记录调用，但是超出次数限制会返回false。
     * <br/> Created on 2014-7-7 上午10:42:50
     *
     * @param keyName     key名称
     * @param intervalSec 调用限制单位时长秒数
     * @param maxCount    单位时长秒数内最大调用次数
     * @return boolean
     * @author jianhua.luo
     * @since 3.0.0
     */
    public static boolean count(String keyName, int intervalSec, int maxCount) {
        if(intervalSec <= 0 || maxCount <= 0) {
            return true;
        }
        try {
            //计算调用限制
            if(StringUtils.isNotEmpty(nameSpace)) {
//                AtomicCommands ac = RedisFactory.getClusterAtomicCommands(nameSpace);
//                Long count = ac.increment(MAPIConstant.REDIS_CHECK, keyName, 1);
//                Long ttl = ac.getExpire(MAPIConstant.REDIS_CHECK, keyName);
//                if (count == 1L || (count > 1L && ttl != null && ttl < 0L)) {
//                    if(count >= 50L) {
//                        LOGGER.error("频率达到50次依然无法设置expire，强制删除：{}",keyName);
//                        ac.delete(MAPIConstant.REDIS_CHECK, keyName);
//                    } else {
//                        ac.expire(MAPIConstant.REDIS_CHECK, keyName, intervalSec, TimeUnit.SECONDS);
//                    }
//                }
//                return count <= maxCount;
                return true;//为不报错暂时添加
            } else {
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("InvokingCounter计数器异常", e);
            return true;
        }
    }
}
