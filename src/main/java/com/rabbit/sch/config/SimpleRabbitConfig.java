package com.rabbit.sch.config;

import com.rabbit.sch.receiver.SimpleReceiver;
import com.rabbit.sch.sender.SimpleSender;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @auther macrozheng
 * @description 简单模式消息队列配置
 * @date 2020/5/19
 * @github https://github.com/macrozheng
 */
@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue hello() {
        return new Queue("simple.hello");
    }

    @Bean
    public SimpleSender simpleSender(){
        return new SimpleSender();
    }

    @Bean
    public SimpleReceiver simpleReceiver(){
        return new SimpleReceiver();
    }

}