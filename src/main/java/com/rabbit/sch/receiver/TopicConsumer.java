package com.rabbit.sch.receiver;

import com.rabbit.sch.sender.SimpleSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @author: sch
 * @date: 2025/2/17/14:47
 * @description: rabbitmq-test
 */
public class TopicConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSender.class);

    @RabbitListener(queues = "#{topicQueue1}")
    public void receiver1(String in){
        receive(in,1);
    }
    @RabbitListener(queues = "#{topicQueue2}")
    public void receiver2(String in){
        receive(in,2);
    }

    private void receive(String in, int receiver) {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'",receiver,in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in'{}s'",receiver,watch.getTotalTimeSeconds());
    }

    private void doWork(String in) {
        for (char c : in.toCharArray()) {
            if (c == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
