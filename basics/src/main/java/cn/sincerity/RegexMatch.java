package cn.sincerity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexMatch
 *
 * @author Ht7_Sincerity
 * @date 2023/6/5
 */
public class RegexMatch {

    private static final Pattern CHINA_NAME_REGEX = Pattern.compile("^[\u4e00-\u9fa5]{2,4}$");

    // 少数民族姓名
    private static final Pattern MINORITY_NAME_REGEX = Pattern.compile("^[\u4e00-\u9fa5]{2,8}·[\u4e00-\u9fa5]{2,8}$");

    // 英语姓名
    private static final Pattern ENGLISH_NAME_REGEX = Pattern.compile("^[a-zA-Z]{2,20}.[a-zA-z]{2,20}$");


    private static final Pattern ID_CARD_REGEX = Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$");

    // 港澳居民来往大陆通行证
    private static final Pattern HONG_KONG_ACCESS = Pattern.compile("^H*[0-9]{8,10}");


    public static void main(String[] args) {
        String idNo = "H123456789";
        System.out.println(HONG_KONG_ACCESS.matcher(idNo).matches());
    }
}
