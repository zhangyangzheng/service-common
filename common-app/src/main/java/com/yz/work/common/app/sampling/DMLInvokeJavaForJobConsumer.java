package com.yz.work.common.app.sampling;

import com.alibaba.fastjson.JSON;
import com.yz.work.common.app.sampling.utils.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DMLInvokeJavaConsumer
 *
 * @author yangzhengzhang
 * @date 2021/8/20 18:46
 */
@Component
public class DMLInvokeJavaForJobConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(DMLInvokeJavaForJobConsumer.class);
  private final String JOB_CORE_PROCESS_METHOD = "process";

  private final Map<String, JobCoreProcess> jobCoreProcessInstanceMap = new ConcurrentHashMap();
  private final Map<String, Method> methodCacheMap = new ConcurrentHashMap();
  private final Map<String, Class<?>[]> parameterTypesMap = new ConcurrentHashMap();

  @Autowired
  public DMLInvokeJavaForJobConsumer(Map<String, JobCoreProcess> map) throws NoSuchMethodException {
    for (Map.Entry<String, JobCoreProcess> entry : map.entrySet()) {
      Class<?> clazz = entry.getValue().getClass();
      String clazzName = entry.getValue().getClass().getName();

      if (Objects.nonNull(entry.getValue().getProcessParameterType())) {
        Class<?>[] types = new Class<?>[] {entry.getValue().getProcessParameterType()};
        Method method = clazz.getMethod(JOB_CORE_PROCESS_METHOD, types);
        Class<?>[] parameterTypes = method.getParameterTypes();
        jobCoreProcessInstanceMap.put(clazzName, entry.getValue());
        methodCacheMap.put(clazzName, method);
        parameterTypesMap.put(clazzName, parameterTypes);
      }
    }
  }

//  @QmqConsumer(
//      prefix = "hotel.settlement.dmlinvokejob",
//      consumerGroup = "hotel.settlement.dmlcomparejob")
//  public void onMessage(Message message) {
  public void onMessage() {

//    String className = message.getStringProperty("className");
    String className = "";
    if (Objects.isNull(jobCoreProcessInstanceMap.get(className))) {
      return;
    }
//    String uniqueId = message.getStringProperty("uniqueId");
//    String data1 = message.getStringProperty("data1");
//    String data2 = message.getStringProperty("data2");
    String uniqueId = "";
    String data1 = "";
    String data2 = "";
    List<String> parameters = initParameter(data1, data2);

    try {
      // set TransThreadLocal
//      ThreadLocalHolder.setTransThreadLocal(String.format("Java-%s", uniqueId), className);

      Class<?>[] parameterTypes = parameterTypesMap.get(className);
      if (parameters.size() != parameterTypes.length) {
        return;
      }
      Object[] objects = new Object[parameterTypes.length];
      for (int i = 0; i < parameterTypes.length; i++) {
        EnumMessageConvertDTORoute route =
            EnumMessageConvertDTORoute.getMessageConvertDTO(parameterTypes[i]);
        if (Objects.isNull(route)) {
          return;
        }
        Object messageVO = JSON.parseObject(parameters.get(i), route.getMessageDTOClazz());
        Object targetMethodParameter =
            JSON.parseObject(JSON.toJSONString(messageVO), route.getTargetClazz());
        objects[i] = targetMethodParameter;
      }

      try {
        LOGGER.info(uniqueId);
        ReflectUtil.executeInvoke(
            methodCacheMap.get(className), jobCoreProcessInstanceMap.get(className), objects);
      } finally {
      }

      // save DB:methodName、uniqueId、MaxProcIndex(.net埋点是手工的，不方便统计，所以使用Java埋点的ProcIndex处理)
//      ThreadLocalContext threadLocalContext = ThreadLocalHolder.getTransThreadLocal().get();
      Integer maxProcIndex = 0;
//      Integer maxProcIndex = threadLocalContext.getProcIndex().get();
//      String serviceName = threadLocalContext.getServiceName();
      String serviceName = "";

//      ThreadLocalHolder.getTransThreadLocal().remove();
      // 写入比对记录
      insertCompareRecord(uniqueId, serviceName, maxProcIndex);
    } catch (Exception e) {
      // clear threadlocal
//      ThreadLocalHolder.getTransThreadLocal().remove();
    } finally{
//      ThreadLocalHolder.getTransThreadLocal().remove();
    }
  }

  private void insertCompareRecord(String uniqueId, String serviceName, Integer maxProcIndex) {
    try {

    } catch (Exception e) {
    }
  }

  private List<String> initParameter(String... data) {
    List<String> parameters = new ArrayList<>();
    for (String parameter : data) {
    }
    return parameters;
  }
}
