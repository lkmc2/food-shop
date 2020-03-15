package com.lin.config;

import cn.hutool.core.date.DateUtil;
import com.lin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单定时任务
 * @author lkmc2
 * @date 2020/3/15 20:22
 */
@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    /**
     * 自动关闭订单（每天 0 点）
     */
    @Scheduled(cron = "0 0 0 * * ? *")
    public void autoCloseOrder() {
        System.out.println("执行定时任务，当前时间为：" + DateUtil.now());
        orderService.closeOrder();
    }

}
