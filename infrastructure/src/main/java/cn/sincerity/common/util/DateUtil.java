package cn.sincerity.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Description：日期时间工具类
 * Create by Ht7_Sincerity on 2021/10/11
 */
public class DateUtil {

    /**
     * 将 Date 类型转换为系统默认时区的 LocalDateTime 类型
     * @param date Date 类型
     * @return LocalDateTime 类型
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
