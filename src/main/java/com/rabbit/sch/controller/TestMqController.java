package com.rabbit.sch.controller;

import com.rabbit.sch.common.CommonResult;
import com.rabbit.sch.common.OmsPortalOrderService;
import com.rabbit.sch.common.OrderParam;
import com.rabbit.sch.sender.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author: sch
 * @date: 2025/2/17/11:14
 * @description: rabbitmq-test
 */
@RestController
@RequestMapping("/rabbit")
public class TestMqController {


    @Autowired
    private WorkProducer workProducer;
    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private TopicProducer topicProducer;
    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @Autowired
    private RoutingDirectSender routingDirectSender;

    @GetMapping("/simple/{num}")
    public CommonResult<Boolean> simple(@PathVariable int num){
        for (int i = 0; i < num; i++) {
            try {
                simpleSender.send();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return CommonResult.success(true);
    }

    /**
     * 首先我们按照对象来划分
     * 生产者WorkProducer 简称WP
     * 消费者WorkReceiver 简称WR
     *  RabbitMQ 简称MQ
     * 好了那么现在开始。。
     * 消息通过controller层进入，假设生产者从控制层会收到10条消息，
     * 收到消息后，生产者在业务逻辑中通过取3的余数，给消息增加‘。‘，也就是每一条消息会按照1-3个句号输出的循环，经过字符的拼接后，消息成功发送
     * 在config中我们一共定义了两个消费者也就是WR，WR根据工作模式轮询得到消息去处理，得到消息后为了模拟处理的时间，在内部进行了sleep，按照。的多少来决定程序停止的时间
     * 最终出现了结果，按照1-3个。的消息顺序，两个消费者按照轮询的方式去消费生产者发送的消息，当然由于这些操作在一个服务中进行所以展现出来的是同步进行的
     * 在消费者中sleep导致了整个服务的停滞运行，实际情况下并不会出现这种情况
     * num作为Producer发送数量的参数，P在接受到参数后
     * @param num
     * @return
     */
    @GetMapping("/work/{num}")
    public CommonResult<Boolean> work(@PathVariable int num){
        for (int i = 0; i < num; i++) {
            workProducer.send(i);
        }
        return CommonResult.success(true);
    }

    /**
     * 消息发送归发送，我发送到队列里面，处理是后面的事情
     * 为什么结果，输出的数量翻倍了，是因为扇形交换机给两个队列都发送了同样数量的消息
     * @param num
     * @return
     */
    @GetMapping("/fanout/{num}")
    public CommonResult<Boolean> fanout(@PathVariable int num){
        for (int i = 0; i < num; i++) {
                fanoutSender.send(i);
        }
        return CommonResult.success(true);
    }

    /**
     * 其实关键点应该在于路由键的作用范围 作用范围仅在交换机和队列之间
     * @param num
     * @return
     */
    @GetMapping("/routing/{num}")
    public CommonResult<Boolean> routing(@PathVariable int num){
        for (int i = 0; i < num; i++) {
        try{
            routingDirectSender.send(i);

            Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return CommonResult.success(true);
    }

    /**
     * 生产者通过发送不同的路由键通过交换机来根据路由规则通配符来匹配到对应的队列
     * @param num
     * @return
     */
    @GetMapping("/topic/{num}")
    public CommonResult<Boolean> topic(@PathVariable int num){
        for (int i = 0; i < num; i++) {
        try{
            topicProducer.send(i);

            Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return CommonResult.success(true);
    }

    /**
     * 根据购物车信息生成订单
     * @param stock
     * @return
     */
    @GetMapping("/order/{stock}")
    public CommonResult<Boolean> generateOrder(@PathVariable int stock){
        OrderParam orderParam = new OrderParam();
        orderParam.setStock(stock);

        return omsPortalOrderService.generateOrder(orderParam);
    }



}
