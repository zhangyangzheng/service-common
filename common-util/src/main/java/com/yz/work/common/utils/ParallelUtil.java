package com.yz.work.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-08-04 17:13
 */
public class ParallelUtil {

  public static <T, R> List<R> paralleHandle(
      Collection<T> collection,
      Function<T, R> function,
      ThreadPoolTaskExecutor executorService) {

    List<R> handleResults = new ArrayList<>();
    List<FutureTask<R>> tasksResults = new ArrayList<>();
    Iterator<T> iterator = collection.iterator();
    while (iterator.hasNext()) {
      try {
        T t = iterator.next();
        FutureTask<R> task =
            new FutureTask<>(
                () -> {
                  System.out.println(Thread.currentThread().getName() + "-" + JSON.toJSONString(t));
                  return function.apply(t);
                });
        tasksResults.add(task);
        executorService.submit(task);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    for (FutureTask<R> futureTask : tasksResults) {
      try {
        R taskResult = futureTask.get();
        if (Objects.nonNull(taskResult)) {
          handleResults.add(taskResult);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return handleResults;
  }
}
