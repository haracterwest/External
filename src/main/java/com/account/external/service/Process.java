package com.account.external.service;

import com.account.external.model.ExternalInfo;
import org.springframework.stereotype.Service;

@Service
public interface Process {
    boolean run(ExternalInfo externalInfo);
}
