package com.lin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 服务日志切面
 * @author lkmc2
 * @date 2020/3/8 19:56
 */
@Aspect
@Component
public class ServiceLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /*
    AOP 通知
    1 前置通知：在方法调用之前执行
    2 后置通知：在方法正常调用之后执行
    3 环绕通知：在方法调用之前和之后，都分别可以执行的通知
    4 异常通知：如果在方法调用过程中发生异常，则通知
    5 最终通知：在方法调用之后执行
     */

    /**
     * 记录服务日志的环绕通知
     *
     * 切面表达式：
     * execution 代表索要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 *(**) *代表类中的方法名，(..)表示方法中的任何参数
     *
     * @param joinPoint 连接点
     * @return 方法执行结果
     */
    @Around("execution(* com.lin.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetClazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();

        logger.info("====== 开始执行 {}.{}() 方法 ======", targetClazz, methodName);

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标 Service 中的方法
        Object result = joinPoint.proceed();

        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 计算耗时
        long costTime = endTime - startTime;

        if (costTime > 3000) {
            logger.error("====== 结束执行 {}.{}() 方法，耗时：{} 毫秒 ======", targetClazz, methodName, costTime);
        } else if (costTime > 2000) {
            logger.warn("====== 结束执行 {}.{}() 方法，耗时：{} 毫秒 ======", targetClazz, methodName, costTime);
        } else {
            logger.info("====== 结束执行 {}.{}() 方法，耗时：{} 毫秒 ======", targetClazz, methodName, costTime);
        }

        return result;
    }

}
