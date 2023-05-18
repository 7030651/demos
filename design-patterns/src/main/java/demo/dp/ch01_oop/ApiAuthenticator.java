package demo.dp.ch01_oop;

public interface ApiAuthenticator {
    /**
     * @param url FullUrl
     */
    void auth(String url);
    void auth(ApiRequest apiRequest);
}
