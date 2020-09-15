package com.li.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

@SpringBootTest(classes = GatewayMain9527Test.class)
@RunWith(SpringRunner.class)
public class GatewayMain9527Test {

    @Test
    public void test() {
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println(dateTime);
    }
}
