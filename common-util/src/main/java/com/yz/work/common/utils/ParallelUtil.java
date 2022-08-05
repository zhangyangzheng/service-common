package com.yz.work.common.utils;

import com.alibaba.fastjson.JSON;
import hotel.settlement.common.LogHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.CountDownLatch;
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
      ThreadPoolTaskExecutor executorService,
      Class<?> clazz)
      throws Exception {

    List<R> handleResults = new ArrayList<>();
    List<FutureTask<R>> tasksResults = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(collection.size());
    Iterator<T> iterator = collection.iterator();
    while (iterator.hasNext()) {
      try {
        FutureTask<R> task =
            new FutureTask<>(
                () -> {
                  LogHelper.logInfo(
                      clazz.getSimpleName() + " CountDownLatch",
                      Thread.currentThread().getName() + "-" + JSON.toJSONString(iterator.next()));
                  R result = function.apply(iterator.next());
                  latch.countDown();
                  return result;
                });
        tasksResults.add(task);
        executorService.submit(task);
      } catch (Exception ex) {
        LogHelper.logError(clazz.getSimpleName(), ex);
      }
    }
    try {
      latch.await();
    } catch (Exception e) {
      LogHelper.logError(clazz.getSimpleName(), e);
    }
    for (FutureTask<R> futureTask : tasksResults) {
      try {
        R taskResult = futureTask.get();
        if (Objects.nonNull(taskResult)) {
          handleResults.add(taskResult);
        }
      } catch (Exception e) {
        LogHelper.logError(clazz.getSimpleName(), e);
      }
    }
    return handleResults;
  }
}
