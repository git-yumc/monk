package com.baizhi.monk.aspect;

import com.baizhi.monk.annotation.Log;
import com.baizhi.monk.dao.LogDao;
import com.baizhi.monk.entity.Admin;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author yumc
 */
@Aspect
@Configuration
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogDao logDao;

    @Around("@annotation(com.baizhi.monk.annotation.Log)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint) {
        HttpSession session = request.getSession();
        String admin = ((Admin) session.getAttribute("admin")).getUsername();
        // 时间
        Date date = new Date();
        // 获取注解信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Log annotation = signature.getMethod().getAnnotation(Log.class);
        String value = annotation.value();
        String status = "失败!";
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            status = "success";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logDao.insert(new com.baizhi.monk.entity.Log(null, admin, date, value, status));
        return proceed;
    }
}
