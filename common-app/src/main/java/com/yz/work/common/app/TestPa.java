package com.yz.work.common.app;

import com.yz.work.common.utils.ParallelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-08-08 10:46
 */
@Component
public class TestPa {
    @Autowired
    @Qualifier("autoCloseBatchThreadPool")
    ThreadPoolTaskExecutor autoCloseBatchThreadPool;

    public void te() {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(i++);
            if (i > 30) break;
        }
        List<Integer> handle = ParallelUtil.paralleHandle(list,
                e -> e,
                autoCloseBatchThreadPool);
        System.out.println(handle);
    }
}
