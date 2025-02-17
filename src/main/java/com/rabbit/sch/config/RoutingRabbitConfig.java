package com.rabbit.sch.config;

import com.rabbit.sch.receiver.RoutingDirectReceiver;
import com.rabbit.sch.sender.RoutingDirectSender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: sch
 * @date: 2025/2/17/14:09
 * @description: rabbitmq-test
 */
@Configuration
public class RoutingRabbitConfig {

    @Bean
    public DirectExchange direct(){
        return new DirectExchange("test.exchange.direct");
    }
    @Bean
    public Queue directQueue1(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue directQueue2(){
        return new AnonymousQueue();
    }
    @Bean
    public Binding directBinging1a(DirectExchange direct,Queue directQueue1){
        return BindingBuilder.bind(directQueue1).to(direct).with("orange");
    }
    @Bean
    public Binding directBinging2a(DirectExchange direct,Queue directQueue2){
        return BindingBuilder.bind(directQueue2).to(direct).with("green");
    }
    @Bean
    public Binding directBinging2b(DirectExchange direct,Queue directQueue2){
        return BindingBuilder.bind(directQueue2).to(direct).with("black");
    }
    @Bean
    public RoutingDirectReceiver receiver(){
        return new RoutingDirectReceiver();
    }
    @Bean
    public RoutingDirectSender directSender(){
        return new RoutingDirectSender();
    }


}
