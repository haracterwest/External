package com.account.external.service;

import com.account.external.service.annotation.CacheResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@CacheResult
public class TestService {

    public String testMethod() {
        return "Hello from TestService!";
    }
}
