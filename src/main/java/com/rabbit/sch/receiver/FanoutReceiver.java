package com.rabbit.sch.receiver;

import com.rabbit.sch.sender.FanoutSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

/**
 * @author: sch
 * @date: 2025/2/17/13:20
 * @description: rabbitmq-test
 */

public class FanoutReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutReceiver.class);

    //这些就是匿名队列的默认名称

    /**
     * #{fanoutQueue1.name} 这玩意是SPEL表达式，能够通过class来动态获取队列名称
     * @param in
     */
    @RabbitListener(queues = "#{fanoutQueue1.name}")
    public void receive1(String in){
        receive(in,1);
    }
    @RabbitListener(queues = "#{fanoutQueue2.name}")
    public void receive2(String in){
        receive(in,2);
    }
    private void receive(String in,int receiver){
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'",receiver,in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in '{}s'",receiver,watch.getTotalTimeSeconds());
    }

    private void doWork(String in){
        for (char ch : in.toCharArray()) {
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



