package demo.dp.ch02_principle.metrics;

import org.apache.commons.lang3.StringUtils;

/**
 * 提供 API，采集并存储原始的接口请求数据
 */
public class MetricsCollector {
    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public boolean send(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return false;
        }
        metricsStorage.saveRequestInfo(requestInfo);
        return true;
    }
}
