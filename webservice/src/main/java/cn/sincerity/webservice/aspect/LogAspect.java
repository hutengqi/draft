package cn.sincerity.webservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * LogAspect
 *
 * @author Ht7_Sincerity
 * @date 2023/7/22
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object after(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before");
        Object proceed = joinPoint.proceed();
        System.out.println("after");
        return proceed;
    }
}
