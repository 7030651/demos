package demo.dp.ch02_principle.metrics;

/**
 * 接口请求信息
 */
public class RequestInfo {
    private String apiName;
    private double responseTime;
    private long timestamp;
    
    public RequestInfo(String apiName, double responseTime, long timestamp) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }
    public String getApiName() {
        return apiName;
    }
    public double getResponseTime() {
        return responseTime;
    }
    public long getTimestamp() {
        return timestamp;
    }
    
}
