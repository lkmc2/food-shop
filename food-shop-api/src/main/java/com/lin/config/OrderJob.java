package com.lin.config;

import cn.hutool.core.date.DateUtil;
import com.lin.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderJob.class);

    @Autowired
    private OrderService orderService;

    /*
    使用定时任务关闭超时未支付订单，会存在的弊端：
    1.会有时间差，程序不严谨
        10:39分下单，11:00检查不足1小时，12:00检查，超过1小时多39分钟

    2.不支持集群
        单机没问题，使用集群后，就会有多个定时任务
        解决方案：只使用一台计算机节点，单独用来运行所有的定时任务

    3.会对数据库全表搜索，极其影响数据库性能

    定时任务，仅仅只适用于小型轻量级项目，传统项目

    针对这种情况，可使用消息队列
        延时任务（队列）
        10:12分下单的，未付款（10）状态，11:12分检查，如果当前状态还是未付款，则直接关闭订单即可
     */

    /**
     * 自动关闭订单（每天 0 点）
     */
    @Scheduled(cron = "0 0 0 * * ? *")
    public void autoCloseOrder() {
        logger.info("执行定时任务，当前时间为：" + DateUtil.now());
        orderService.closeOrder();
    }

}
