package services;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class HitCounter {
    private static HitCounter ourInstance = new HitCounter();

    public static HitCounter getInstance() {
        return ourInstance;
    }


    private String key = "counter";
    private Jedis jedis;
    private Map<String, String> env = System.getenv();

    private HitCounter() {
        jedis = new Jedis(env.get("REDIS_PORT_6379_TCP_ADDR"));
        jedis.set(key, "0");
    }

    public Long incr() {
        return jedis.incr(key);
    }
}
