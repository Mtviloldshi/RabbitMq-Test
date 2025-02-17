package com.rabbit.sch.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sch
 * @date: 2025/2/17/14:14
 * @description: rabbitmq-test
 */
public class RoutingDirectSender {

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "test.exchange.direct";

    private final String[] keys = {"orange","black","green"};

    private static  final Logger LOGGER = LoggerFactory.getLogger(RoutingDirectSender.class);

    public void send(int index){
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index+1);
        for (int i = 0; i <= limitIndex; i++) {
            builder.append('.');
        }
        String message = builder.toString();
        template.convertAndSend(exchangeName,key,message);
        LOGGER.info("[x] Sent '{}'",message);
    }
}
