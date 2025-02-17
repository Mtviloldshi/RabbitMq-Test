package com.rabbit.sch.common;

import com.rabbit.sch.receiver.FanoutReceiver;
import com.rabbit.sch.sender.OrderCancelSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: sch
 * @date: 2025/2/17/15:57
 * @description: rabbitmq-test
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService{
    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutReceiver.class);

    @Autowired
    private OrderCancelSender orderCancelSender;

    @Override
    public CommonResult<Boolean> generateOrder(OrderParam orderParam) {
        long orderId = 11L;
        //执行一系列的订单下单操作
        LOGGER.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单
        sendDelayMessageCancelOrder(orderId);
        return CommonResult.success(true,"下单成功");
    }

    private void sendDelayMessageCancelOrder(long orderId) {
        //获取订单超时时间，假设为10秒钟
        long delaysTimes = 10000;
        //发送延迟消息
        orderCancelSender.sendMessage(orderId,delaysTimes);
    }

    @Override
    public void cancelOrder(Long orderId) {
        //执行一系列取消订单的操作
        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }
}
