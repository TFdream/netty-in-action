package io.dreamstudio.ordering.util;

import java.math.BigDecimal;

/**
 * @author Ricky Fung
 */
public abstract class DecimalUtils {
    private static final int DEFAULT_SCALE = 6;

    //------------基本类型

    public static BigDecimal valueOf(byte num) {
        return new BigDecimal(Byte.toString(num));
    }

    public static BigDecimal valueOf(short num) {
        return new BigDecimal(Short.toString(num));
    }

    public static BigDecimal valueOf(int num) {
        return new BigDecimal(Integer.toString(num));
    }

    public static BigDecimal valueOf(long num) {
        return new BigDecimal(Long.toString(num));
    }

    public static BigDecimal valueOf(float num) {
        return new BigDecimal(Float.toString(num));
    }

    public static BigDecimal valueOf(double num) {
        return new BigDecimal(Double.toString(num));
    }

    //-------------包装类型
    public static BigDecimal valueOf(Byte num) {
        return new BigDecimal(num.toString());
    }

    public static BigDecimal valueOf(Short num) {
        return new BigDecimal(num.toString());
    }

    public static BigDecimal valueOf(Integer num) {
        return new BigDecimal(num.toString());
    }

    public static BigDecimal valueOf(Long num) {
        return new BigDecimal(num.toString());
    }

    public static BigDecimal valueOf(Float num) {
        return new BigDecimal(num.toString());
    }

    public static BigDecimal valueOf(Double num) {
        return new BigDecimal(num.toString());
    }

    public static BigDecimal valueOf(String num) {
        return new BigDecimal(num);
    }

    //-------------
    public static String format(BigDecimal bd) {
        return bd.setScale(DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String format(BigDecimal bd, int scale) {
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String format(BigDecimal bd, int scale, int roundingMode) {
        return bd.setScale(scale, roundingMode).toString();
    }

    //---------
    public static BigDecimal setScale(BigDecimal bd, int scale) {
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal setScale(BigDecimal bd, int scale, int roundingMode) {
        return bd.setScale(scale, roundingMode);
    }

    //------求最小值
    public static BigDecimal min(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) < 0 ? b1 : b2;
    }

    public static BigDecimal min(BigDecimal b1, BigDecimal b2, BigDecimal b3) {
        BigDecimal min = b1.compareTo(b2) < 0 ? b1 : b2;
        return min.compareTo(b3) < 0 ? min : b3;
    }

    //-----求最大值
    public static BigDecimal max(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) > 0 ? b1 : b2;
    }

    public static BigDecimal max(BigDecimal b1, BigDecimal b2, BigDecimal b3) {
        BigDecimal max = b1.compareTo(b2) > 0 ? b1 : b2;
        return max.compareTo(b3) > 0 ? max : b3;
    }

    //-----int
    public static BigDecimal add(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入
    }

    public static BigDecimal div(int v1, int v2, int scale) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);//四舍五入
    }

    public static BigDecimal div(int v1, int v2, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, roundingMode);
    }

    //-----long
    public static BigDecimal add(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }

    public static BigDecimal div(long v1, long v2, int scale) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);//四舍五入
    }

    public static BigDecimal div(long v1, long v2, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.divide(b2, scale, roundingMode);
    }

    //-----double
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入
    }

    /**
     * 除法
     * @param v1
     * @param v2
     * @param scale
     * @param roundingMode 参考 BigDecimal.ROUND_HALF_UP
     * @return
     */
    public static BigDecimal div(double v1, double v2, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, roundingMode);
    }

    //--------
    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        return b1.add(b2);
    }

    public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
        return b1.subtract(b2);
    }

    public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
        return b1.multiply(b2);
    }

    public static BigDecimal div(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入
    }

    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale) {
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);//四舍五入
    }

    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale, int roundingMode) {
        return b1.divide(b2, scale, roundingMode);
    }
}
