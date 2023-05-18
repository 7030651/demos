package demo.dp.ch01_oop;

public class AuthToken {
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken create(String baseUrl, String appId, String password, long timestamp) {
        // TODO Auto-generated method stub
        return null;
    }

    public static AuthToken create(String baseUrl, String appId, long timestamp, long expiredTimeInterval) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getToken() {
        return token;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - createTime > expiredTimeInterval;
    }

    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }
}
