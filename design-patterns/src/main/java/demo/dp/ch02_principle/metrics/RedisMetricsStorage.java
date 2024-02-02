package demo.dp.ch02_principle.metrics;

import java.util.List;
import java.util.Map;

/**
 * 使用 Redis 存储统计数据
 */
public class RedisMetricsStorage implements MetricsStorage {

    @Override
    public boolean saveRequestInfo(RequestInfo requestInfo) {
        // TODO save to redis.
        return false;
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimestamp, long endTimestamp) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimestamp, long endTimestamp) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
