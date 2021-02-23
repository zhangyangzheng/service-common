package com.yz.work.common.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Description: list集合分页
 * @Author YangZheng.Zhang
 * @Date 2020/9/16
 **/
public class ListPagingUtil {

    /**
     *
     * list分页轮询。大list分页轮询到结束。只需要在外部创建函数式接口，实现业务逻辑。
     *
     * @param list
     * @param pageSizes
     * @param function
     * @param <T>
     */
    public static <T> int listPolling(List<T> list, Integer pageSizes, Function function) {
        int count = 0;
        Integer pageNo = 1;
        while(true) {
            List<T> listPaging = listPaging(list, pageNo, pageSizes);
            if (CollectionUtils.isEmpty(listPaging)) {
                break;
            }
            Object num = function.apply(listPaging);
            count += (int)num;
            pageNo++;
        }
        return count;
    }

    /**
     *
     * list集合分页。返回的list为空，代表该页无数据，如：最后一页后，再下一页就是空，表示分页结束
     *
     * @param list 进行分页的list
     * @param pageNo 页码
     * @param pageSize 每页显示条数
     * @return 分页后数据
     */
    public static <T> List<T> listPaging(List<T> list, Integer pageNo, Integer pageSize) {
        if(list == null){
            list = new ArrayList<T>();
        }
        if(pageNo == null || pageNo <= 0){
            pageNo = 1;
        }
        if(pageSize == null || pageSize <= 0){
            pageSize = 10;
        }

        int totalItems = list.size();
        List<T> pagingList = new ArrayList<T>();

        int totalNum = Math.min(((pageNo - 1) * pageSize) + pageSize, totalItems);
        for(int i = (pageNo-1)*pageSize; i < totalNum; i++) {
            pagingList.add(list.get(i));
        }
        return pagingList;
    }

}
