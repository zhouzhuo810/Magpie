package me.zhouzhuo810.magpie.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtil {

    /**
     * List是否为空或size为0
     *
     * @param list List对象
     * @param <T>  List元素的类型
     * @return 是／否
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    /**
     * 排序(默认顺序)
     *
     * @param list 集合
     */
    public static void sort(List<String> list) {
        sort(list, false);
    }

    /**
     * 排序
     *
     * @param list    集合
     * @param reverse 是否倒序
     */
    public static void sort(List<String> list, boolean reverse) {
        if (list != null) {
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1 == null) {
                        return -1;
                    }
                    if (o2 == null) {
                        return 1;
                    }
                    if (o1.length() == o2.length()) {
                        return o1.compareTo(o2);
                    } else if (o1.length() > o2.length()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            if (reverse) {
                Collections.reverse(list);
            }
        }
    }
}
