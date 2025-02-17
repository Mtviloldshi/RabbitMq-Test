package com.rabbit.sch.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sch
 * @date: 2025/2/17/14:46
 * @description: rabbitmq-test
 */
public class TopicProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSender.class);

    @Autowired
    private RabbitTemplate template;

    public final static String exchangeName = "test.exchange.topic";

    private final String[] keys = {"quick.orange.rabbit","lazy.orange.elephant","quick.orange.fox",
    "lazy.brown.fox","lazy.pink.rabbit","quick.brown.fox"};

    public void send(int index){
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index%keys.length;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index+1);
        for (int i = 0; i < limitIndex; i++) {
            builder.append('.');
        }
        String message = builder.toString();
        template.convertAndSend(exchangeName,key,message);
        LOGGER.info(" [x] Sent '{}'",message);

    }


}
