package com.example.rmsofttask.common.redis;

import com.example.rmsofttask.common.response.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributeLockAop {
    private static final String REDISSON_KEY_PREFIX = "RLOCK_";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;


    /**
     * DistributeLock 어노테이션 실행 전후 lock 기능 실행
     * */
    @Around("@annotation(com.example.rmsofttask.common.redis.DistributeLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);
        String key = REDISSON_KEY_PREFIX + createKey(signature.getParameterNames(), joinPoint.getArgs(), distributeLock.key());

        RLock rLock = redissonClient.getLock(key);

        log.info(key);
        try {
            boolean available = rLock.tryLock(distributeLock.waitTime(), distributeLock.leaseTime(), distributeLock.timeUnit());
            if (!available) {
                return false;
            }
            log.info("get lock success {}" , key);
            return aopForTransaction.proceed(joinPoint);
        }
        finally {
            rLock.unlock();
        }
    }


    private String createKey(String[] parameterNames, Object[] args, String key) {
        String resultKey = key;
        for(int i = 0; i < parameterNames.length; i++){
            if(parameterNames[i].equals(key)){
                resultKey += args[i];
                break;
            }
        }
        return resultKey;
    }
}