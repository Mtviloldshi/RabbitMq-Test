package com.rabbit.sch.config;


import com.rabbit.sch.receiver.WorkReceiver;
import com.rabbit.sch.sender.WorkProducer;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: sch
 * @date: 2025/2/17/10:56
 * @description: 工作队列消息配置
 */
@Configuration
public class WorkRabbitConfig {
    @Bean
    public Queue workQueue() { return  new Queue("test.work.queue");}
    @Bean
    public WorkProducer WorkProducer(){return new WorkProducer();}
    @Bean
    public WorkReceiver workReceiver1(){
        return new WorkReceiver(1);
    }
    @Bean
    public WorkReceiver workReceiver2(){
        return new WorkReceiver(2);
    }
}
