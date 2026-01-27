package com.springboot.springboot2.utils;

public class StringUtil {

    /**
     * 从字符串中提取所有数字，并拼接成整数返回
     * 例如: "B003" -> 3
     */
    public static int getNumberFromString(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        char[] chars = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                builder.append(c);
            }
        }
        if (builder.length() == 0) {
            return 0;
        }
        return Integer.parseInt(builder.toString());
    }

    /**
     * 将 0-99 的数字转换成两位字符串，不足部分前补零
     * 例如: 3 -> "03", 12 -> "12"
     */
    public static String numberToLen2String(int i) {
        if (i >= 0 && i < 10) {
            return "0" + i;
        } else {
            return String.valueOf(i);
        }
    }


}
