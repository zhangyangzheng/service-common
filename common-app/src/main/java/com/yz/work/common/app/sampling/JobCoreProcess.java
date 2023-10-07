package com.yz.work.common.app.sampling;

import org.springframework.util.CollectionUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangzhengzhang
 * @description dml compare method invoke
 * @date 2021-09-13 14:02
 */
public interface JobCoreProcess<R, P> {

  R process(P data) throws Exception;

  default Class<?> getProcessParameterType() {
    List<Type> interfaces =
        Arrays.stream(getClass().getGenericInterfaces())
            .filter(
                e -> e.getTypeName().contains("hotel.settlement.domain.dmlcompare.JobCoreProcess"))
            .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(interfaces)) {
      return null;
    }
    return (Class<?>) ((ParameterizedTypeImpl) interfaces.get(0)).getActualTypeArguments()[1];
  }
}
