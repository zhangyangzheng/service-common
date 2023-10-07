package com.yz.work.common.app.sampling.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yangzhengzhang
 * @description
 * @date 2021-09-14 18:29
 */
public class ReflectUtil {
  public static Method getMethod(Class<?> clazz, String methodName) {
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        // 将此对象的 accessible 标志设置为指示的布尔值。
        // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
        // 值为 false 则指示反射的对象应该实施 Java 语言访问检查。
        method.setAccessible(true);
        return method;
      }
    }
    return null;
  }

  public static Object executeInvoke(Method mappingMethod, Object bean, Object... messageConvertVO)
      throws InvocationTargetException, IllegalAccessException {
    return mappingMethod.invoke(bean, messageConvertVO);
  }
}
