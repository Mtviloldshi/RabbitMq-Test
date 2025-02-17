package com.rabbit.sch.receiver;

import com.rabbit.sch.common.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: sch
 * @date: 2025/2/17/15:53
 * @description: rabbitmq-test
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class OrderCancelReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutReceiver.class);


    @Autowired
    private OmsPortalOrderService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("receive delay message orderId:{}",orderId);
        portalOrderService.cancelOrder(orderId);
    }
}
