package cn.ccu.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description TODO
 * @date 2019/10/25 15:04
 */
public class Hanzi {

    /**
     * 判断字符是否是汉字
     * @param str 字符
     * @return boolean
     */
    public static boolean checkChineseChar(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
