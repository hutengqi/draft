package cn.sincerity.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Description：逻辑判断工具类
 * Create by htq on 2021/9/30
 */
public class LogicUtil {

    /**
     * 判断指定对象是否不为空
     *
     * @param t   待检查对象
     * @param <T> 对象类型
     * @return 是否不为空
     */
    public static <T> boolean isNotEmpty(T t) {
        if (t == null) {
            return false;
        } else if (t instanceof Collection) {
            return CollectionUtil.isNotEmpty((Collection<?>) t);
        } else if (t instanceof Map) {
            return CollectionUtil.isNotEmpty((Map<?, ?>) t);
        } else if (t instanceof CharSequence) {
            return StrUtil.isNotEmpty((CharSequence) t);
        } else if (t instanceof Array) {
            return ArrayUtil.isNotEmpty(t);
        } else {
            return !t.toString().equals("");
        }
    }

    /**
     * 验证是否包含在指定的元素中
     *
     * @param t    待验证元素
     * @param args 元素集合
     * @param <T>  元素类型
     * @return 是否包含在指定集合中
     */
    @SafeVarargs
    public static <T> boolean isContains(T t, T... args) {
        List<T> ts = Arrays.asList(args);
        return ts.contains(t);
    }
}
