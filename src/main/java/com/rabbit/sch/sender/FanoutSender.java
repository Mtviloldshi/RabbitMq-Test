package com.rabbit.sch.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sch
 * @date: 2025/2/17/13:20
 * @description: rabbitmq-test
 */
public class FanoutSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutSender.class);

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "test.exchange.fanout";

    public void send(int index){
        StringBuilder builder = new StringBuilder("Hello");
        int limitIndex = index % 3 +1;
        for (int i = 0; i < limitIndex; i++) {
            builder.append(".");
        }
        builder.append(index+1);
        String message = builder.toString();
        template.convertAndSend(exchangeName,"",message);
        LOGGER.info("[x] Sent  '{}'",message);



    }


}
