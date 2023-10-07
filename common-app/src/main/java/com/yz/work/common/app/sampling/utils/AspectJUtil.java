//package com.yz.work.common.app.sampling.utils;
//
//import com.alibaba.ttl.threadpool.agent.internal.javassist.bytecode.SignatureAttribute;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.CodeSignature;
//import org.aspectj.lang.reflect.MethodSignature;
//
///**
// * @author yangzhengzhang
// * @description UT不好mock的方法
// * @date 2021-09-09 15:11
// */
//public class AspectJUtil {
//
//  public static String[] getParamNames(ProceedingJoinPoint joinPoint) {
//    return ((CodeSignature) joinPoint.getSignature()).getParameterNames();
//  }
//
//  public static Object[] getArgs(ProceedingJoinPoint joinPoint) {
//    return joinPoint.getArgs();
//  }
//
//  public static Class<?> getParameterType(ProceedingJoinPoint joinPoint)
//      throws InstantiationException, IllegalAccessException {
//    return ((Class) (joinPoint).getArgs()[3]).newInstance().getClass();
//  }
//
//  public static Class<?> getReturnType(ProceedingJoinPoint joinPoint) {
//    SignatureAttribute.MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//    return methodSignature.getReturnType();
//  }
//}
