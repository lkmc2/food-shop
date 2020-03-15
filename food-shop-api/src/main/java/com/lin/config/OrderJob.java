package com.lin.config;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单定时任务
 * @author lkmc2
 * @date 2020/3/15 20:22
 */
@Component
public class OrderJob {

    /**
     * 自动关闭订单
     */
    @Scheduled(cron = "0/3 * * * * ?")
    public void autoCloseOrder() {
        System.out.println("执行定时任务，当前时间为：" + DateUtil.now());
    }

}
