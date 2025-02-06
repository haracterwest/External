package com.account.external.service;

import com.account.external.model.ExternalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ExternalInfoProcess implements Process {
    private static final Logger logger = LoggerFactory.getLogger(ExternalInfoProcess.class);

    @Value("${id-not-process:3}")
    private Integer idNotProcess;

    @Override
    public boolean run(ExternalInfo externalInfo) {
        logger.info("Running process on ExternalInfo: " + externalInfo);
        return !externalInfo.getId().equals(idNotProcess);
    }
}
