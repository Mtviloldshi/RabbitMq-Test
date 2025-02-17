package com.rabbit.sch.config;

import com.rabbit.sch.receiver.TopicConsumer;
import com.rabbit.sch.sender.TopicProducer;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: sch
 * @date: 2025/2/17/14:42
 * @description: rabbitmq-test
 */
@Configuration
public class TopicRabbitConfig {

    @Bean
    public TopicExchange topicExchange() { return new TopicExchange("test.exchange.topic"); }

    @Bean
    public TopicProducer topicProducer() {
        return  new TopicProducer();
    }
    @Bean
    public TopicConsumer topicConsumer() {
        return  new TopicConsumer();
    }
    @Bean
    public Queue topicQueue1(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue topicQueue2(){
        return new AnonymousQueue();
    }
    @Bean
    public Binding topicBinding1a(TopicExchange topic,Queue topicQueue1){
        return BindingBuilder.bind(topicQueue1).to(topic).with("*.orange.*");
    }
    @Bean
    public Binding topicBinding2a(TopicExchange topic,Queue topicQueue2){
        return BindingBuilder.bind(topicQueue2).to(topic).with("*.*.rabbit");
    }
    @Bean
    public Binding topicBinding2b(TopicExchange topic,Queue topicQueue2){
        return BindingBuilder.bind(topicQueue2).to(topic).with("lazy.#");
    }

}
