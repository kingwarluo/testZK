package com.redis.op;

import com.random.RandomNum;
import com.redis.RedisUtil;
import org.redisson.api.RLock;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * lua脚本执行
 * @author kingwarluo
 * @date 2023/5/11 14:03
 */
public class LuaOp {

    public static void main(String[] args) throws InterruptedException {
        RedissonClient redisClient = RedisUtil.getRedisClient();
        RScript script = redisClient.getScript();
        String s = "redis.call('set',KEYS[1], ARGV[1])\n"
                +"return redis.call(\"get\", KEYS[1])";
        String s1 = script.scriptLoad(s);
        String o = script.evalSha(RScript.Mode.READ_WRITE, s1, RScript.ReturnType.VALUE, Collections.singletonList("key2"), "瓜田李下" + new Random().nextInt());
        System.out.println(o);
    }

}
