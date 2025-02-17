package com.rabbit.sch.config;

import com.rabbit.sch.receiver.FanoutReceiver;
import com.rabbit.sch.sender.FanoutSender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: sch
 * @date: 2025/2/17/13:14
 * @description: rabbitmq-test
 */
@Configuration
public class FanoutRabbitConfig {

    @Bean
    public FanoutExchange fanout(){
        FanoutExchange fanoutExchange = new FanoutExchange("test.exchange.fanout");
        fanoutExchange.setDelayed(true);
        return fanoutExchange;
    }
    @Bean
    public Queue fanoutQueue1(){
        return new AnonymousQueue();
    }
    @Bean
    public Queue fanoutQueue2(){
        return new AnonymousQueue();
    }
    @Bean
    public Binding fanoutBinding1(FanoutExchange fanout,Queue fanoutQueue1){
        return BindingBuilder.bind(fanoutQueue1).to(fanout);
    }
    @Bean
    public Binding fanoutBinding2(FanoutExchange fanout,Queue fanoutQueue2){
        return BindingBuilder.bind(fanoutQueue2).to(fanout);
    }
    @Bean
    public FanoutReceiver fanoutReceiver(){
        return new FanoutReceiver();
    }
    @Bean
    public FanoutSender fanoutSender(){
        return new FanoutSender();
    }


}
