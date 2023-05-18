package demo.dp.ch01_oop;

public class ApiRequest {
    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;
    
    public ApiRequest() {}
    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }
    public static ApiRequest createFromFullUrl(String url) {
        // TODO parse url
        System.out.println("parse url: " + url);
        return null;
    }
    public String getBaseUrl() {
        return this.baseUrl;
    }
    public String getToken() {
        return this.token;
    }
    public String getAppId() {
        return this.appId;
    }
    public long getTimestamp() {
        return this.timestamp;
    }
}
