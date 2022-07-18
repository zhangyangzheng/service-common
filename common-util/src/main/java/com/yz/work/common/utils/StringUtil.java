package com.yz.work.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;

public class StringUtil {
    public static String join(String join, Iterable<?> strArray) {
        StringBuffer sb = new StringBuffer();

        if (strArray != null) {
            Iterator<?> iterator = strArray.iterator();
            Boolean first = true;

            while (iterator.hasNext()) {
                if (!first) {
                    sb.append(join);
                } else {
                    first = false;
                }
                sb.append(iterator.next());
            }
        }


        return new String(sb);
    }

    public static Collection<String> split(String input, String regex, boolean remveEmptyString) {
        String[] tempResult = input.split(regex);
        if (remveEmptyString) {
            Collection<String> result = LinqUtil.where(Arrays.asList(tempResult), new WhereFunction<String>() {
                @Override
                public boolean where(String o) {
                    return !StringUtil.isNullOrEmpty(o);
                }
            });
            return result;
        }
        return Arrays.asList(tempResult);
    }

    public static Boolean isNullOrEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static String trim(String input, String trimString) {
        return input.replaceFirst("^" + trimString, "").replaceFirst(trimString + "$", "");
    }

    public static <T> T parseObject(String json, Class<T> clzz) {
        return JSON.parseObject(json, clzz, Feature.AllowISO8601DateFormat);
    }

    public static <T> T parseObject(String json, TypeReference<T> reference) {
        return JSON.parseObject(json, reference, Feature.AllowISO8601DateFormat);
    }

    /**
     * 去除空格字符的 jsonString 转换
     * 参数是 String 类型将直接返回
     * 其他类型 将使用 fastjson 序列化返回
     *
     * @param object
     * @return
     */
    public static String toJSONStringTrimAllWhitespace(Object object) {
        if (object instanceof String) {
            return StringUtils.trimAllWhitespace(object.toString());
        } else {
            return StringUtils.trimAllWhitespace(JSON.toJSONString(object));
        }

    }

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }


    /**
     * base 64加密
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * base 64解密
     */
    public static byte[] base64Decode(String content) {
        return Base64.getDecoder().decode(content);
    }

    /**
     * md5，返回的是小写字符
     */
    public static String md5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes("UTF8"));
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        } catch (Exception e) {
            return null;
        }
    }

    private static String byteArrayToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将数字首位用 0 填充到指定长度的字符串
     *
     * @param number
     * @param length
     * @return
     */
    public static String digitZeroPadding(long number, Integer length) {
        if (String.valueOf(number).length() > length) {
            throw new RuntimeException(
                    MessageFormat.format("当前数值[{0}]长度已经大于预期长度[{1}]！", number, length));
        }
        return String.format("%0" + length + "d", number);
    }


    /**
     * 将字符串text中由openToken和closeToken组成的占位符依次替换为args数组中的值
     *
     * @param openToken
     * @param closeToken
     * @param text
     * @param args
     * @return
     */
    public static String parse(String openToken, String closeToken, String text, Object... args) {
        if (args != null && args.length > 0) {
            int argsIndex = 0;
            if (text != null && !text.isEmpty()) {
                char[] src = text.toCharArray();
                int offset = 0;
                int start = text.indexOf(openToken, offset);
                if (start == -1) {
                    return text;
                } else {
                    StringBuilder builder = new StringBuilder();

                    for (StringBuilder expression = null; start > -1; start = text.indexOf(openToken, offset)) {
                        if (start > 0 && src[start - 1] == '\\') {
                            builder.append(src, offset, start - offset - 1).append(openToken);
                            offset = start + openToken.length();
                        } else {
                            if (expression == null) {
                                expression = new StringBuilder();
                            } else {
                                expression.setLength(0);
                            }

                            builder.append(src, offset, start - offset);
                            offset = start + openToken.length();

                            int end;
                            for (end = text.indexOf(closeToken, offset); end > -1; end = text.indexOf(closeToken, offset)) {
                                if (end <= offset || src[end - 1] != '\\') {
                                    expression.append(src, offset, end - offset);
                                    break;
                                }

                                expression.append(src, offset, end - offset - 1).append(closeToken);
                                offset = end + closeToken.length();
                            }

                            if (end == -1) {
                                builder.append(src, start, src.length - start);
                                offset = src.length;
                            } else {
                                String value = argsIndex <= args.length - 1 ? (args[argsIndex] == null ? "" : args[argsIndex].toString()) : expression.toString();
                                builder.append(value);
                                offset = end + closeToken.length();
                                ++argsIndex;
                            }
                        }
                    }

                    if (offset < src.length) {
                        builder.append(src, offset, src.length - offset);
                    }
                    return builder.toString();
                }
            } else {
                return "";
            }
        } else {
            return text;
        }
    }

    public static String format(String text, Object... args) {
        return parse("{", "}", text, args);
    }


}
