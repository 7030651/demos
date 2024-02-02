package demo.dp.ch02_principle.metrics;

import java.util.List;
import java.util.Map;

public interface MetricsStorage {

    public boolean saveRequestInfo(RequestInfo requestInfo);
    public List<RequestInfo> getRequestInfos(String apiName, long startTimestamp, long endTimestamp);
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimestamp, long endTimestamp);
    
}
