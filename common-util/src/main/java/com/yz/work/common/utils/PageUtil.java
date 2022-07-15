package com.yz.work.common.utils;


import java.util.function.Supplier;

/**
 * @author yangzheng.zhang
 * @date 2020-01-13
 */
public class PageUtil {

    /**
     * @Description: 在函数式接口里实现分页查询逻辑。
     *               查询正常 return ture，继续下一页。
     *               查询异常 return false，结束查询（异常特指最后一页后，查询结果为空，表示查询结束）。
     *
     * @author YangZheng.Zhang
     * @date 2020-09-30
     * @param <T>
     * @param <E>
     */
//    @FunctionalInterface
//    public interface PagePredicate<T, E> {
//        boolean query(T pageNum, E pageSize);
//    }
//
//    public static void pageOperateHandle(Integer pageSize, PagePredicate pagePredicate) {
//        Integer limitStart = 0;
//        while(pagePredicate.query(limitStart, pageSize)) {
//            limitStart += pageSize;
//        }
//    }

    @FunctionalInterface
    public interface PagePredicate<T, E> {
        T test(T limitStart, E pageSize) throws Exception;
    }

    public static <T, E> void pageHandle(Supplier<T> limit, E pageSize, PagePredicate<T, E> pagePredicate) throws Exception {
        assert limit != null && limit.get() != null;
        T limitStart = limit.get();
        do{
            limitStart = pagePredicate.test(limitStart, pageSize);
        } while (limitStart != null);
    }

}
