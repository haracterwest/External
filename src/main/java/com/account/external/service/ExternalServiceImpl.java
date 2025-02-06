package com.account.external.service;

import com.account.external.model.ExternalInfo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class ExternalServiceImpl implements ExternalService {
    private static final Logger logger = LoggerFactory.getLogger(ExternalServiceImpl.class);

    private Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>();
    private Map<String, ExternalInfo> cacheMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        externalInfoMap.put(1, new ExternalInfo(1, null));
        externalInfoMap.put(2, new ExternalInfo(2, "hasInfo"));
        externalInfoMap.put(3, new ExternalInfo(3, "info"));
        externalInfoMap.put(4, new ExternalInfo(4, "information"));
    }

    @PreDestroy
    public void cleanUp() {
        logger.info("Cleaning up ExternalServiceImpl...");
        externalInfoMap.clear();
    }

    @CacheResult
    public ExternalInfo getExternalInfo(Integer id) {
        String cacheKey = "ExternalServiceImpl:getExternalInfo:" + id;
        if (cacheMap.containsKey(cacheKey)) {
            logger.info("Fetching from cache for id: " + id);
            return cacheMap.get(cacheKey);
        }
        logger.info("Called getExternalInfo with id: " + id);
        ExternalInfo info = externalInfoMap.get(id);
        cacheMap.put(cacheKey, info);
        return info;
    }
}
