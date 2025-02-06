package com.account.external.model;

import com.account.external.service.ExternalService;
import com.account.external.service.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class Flow {
    private static final Logger logger = LoggerFactory.getLogger(Flow.class);

    private final ExternalService externalService;
    private final Process externalInfoProcess;

    @Autowired
    public Flow(ExternalService externalService, @Lazy Process externalInfoProcess) {
        this.externalService = externalService;
        this.externalInfoProcess = externalInfoProcess;
    }

    public void run(Integer id) {
        ExternalInfo externalInfo = externalService.getExternalInfo(id);
        if (externalInfo.getInfo() != null) {
            boolean result = externalInfoProcess.run(externalInfo);
            logger.info("Process result: " + result);
        } else {
            logger.info("Info is null for id: " + id + ", class: " + externalInfoProcess.getClass());
        }
    }
}
