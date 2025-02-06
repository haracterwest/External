package com.account.external;

import com.account.external.model.ExternalInfo;
import com.account.external.service.ExternalService;
import com.account.external.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExternalApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ExternalService externalService;

    @Autowired
    private TestService testService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCacheResult() {
        ExternalInfo info1 = externalService.getExternalInfo(1);
        ExternalInfo info2 = externalService.getExternalInfo(1);
        assertEquals(info1, info2, "The two calls should return the same cached result");
    }

    @Test
    void testPrototypeBeanWithCacheResult() {
        TestService testService1 = context.getBean(TestService.class);
        TestService testService2 = context.getBean(TestService.class);

        String result1 = testService1.testMethod();
        String result2 = testService2.testMethod();

        assertEquals(result1, result2, "Both calls should return the same result, but they are different instances.");

        String cachedResult = testService1.testMethod();

        assertEquals(result1, cachedResult, "Subsequent call should return the cached result.");
    }
}
