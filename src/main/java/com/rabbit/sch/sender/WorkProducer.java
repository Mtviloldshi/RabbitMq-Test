package com.rabbit.sch.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sch
 * @date: 2025/2/17/11:03
 * @description: rabbitmq-test
 */
public class WorkProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkProducer.class);

    @Autowired
    private RabbitTemplate template;

    private static  final String queueName = "test.work.queue";

    public void send(int index){
        StringBuilder builder = new StringBuilder("WorkProducerMessage: OrderGenerate Command");
        int limitIndex = index % 3+ 1;
        for (int i = 0; i < limitIndex; i++) {
            builder.append('.');
        }
        builder.append(index +1);
        String message = builder.toString();
        this.template.convertAndSend(queueName,message);
        LOGGER.info(" [x] Sent '{}'",message);
    }
}
