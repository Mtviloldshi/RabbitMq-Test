package com.rabbit.sch;

import com.rabbit.sch.sender.SimpleSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RabbitmqTestApplicationTests {

    @Autowired
    private SimpleSender simpleSender;

    @Test
    void contextLoads() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            simpleSender.send();
            Thread.sleep(1000);
        }
    }

}
