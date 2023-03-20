package cn.sincerity.common.util;

import com.alibaba.fastjson.JSON;

/**
 * JsonUtil
 *
 * @author Ht7_Sincerity
 * @since 2023/3/5
 */
public class JsonUtil {

    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}
