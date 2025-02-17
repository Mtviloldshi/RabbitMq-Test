package com.rabbit.sch.common;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author: sch
 * @date: 2025/2/17/15:55
 * @description: rabbitmq-test
 */

public interface OmsPortalOrderService {

    @Transactional
    CommonResult<Boolean> generateOrder(OrderParam orderParam);

    @Transactional
    void cancelOrder(Long orderId);
}
