package com.rabbit.sch.receiver;

import com.rabbit.sch.sender.WorkProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @author: sch
 * @date: 2025/2/17/11:10
 * @description: rabbitmq-test
 */
@RabbitListener(queues = "test.work.queue")
public class WorkReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkReceiver.class);

    private final int instance;

    public WorkReceiver(int i){
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'",this.instance ,in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s",this.instance , watch.getTotalTimeSeconds());
    }


    private void doWork(String in) {
        char[] charArray = in.toCharArray();
        for (char ch : charArray) {
            if (ch == '.'){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
