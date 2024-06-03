package util;

/**
 * 字符串工具类
 */
public class StrUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 如果字符串为空或仅包含空白字符，则返回 true；否则返回 false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return 如果字符串不为空且不只包含空白字符，则返回 true；否则返回 false
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
