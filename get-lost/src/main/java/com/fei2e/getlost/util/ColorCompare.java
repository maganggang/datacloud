package com.fei2e.getlost.util;

import java.awt.*;

/**
 * @ClassName ColorCompare
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/16 11:56
 * @Version 1.0
 **/
public class ColorCompare {

    public static double getColorDistanceOf(Color color1, Color color2){
        HSV hsv1=HSV.RGB2HSV(color1.getRed(),color1.getGreen(),color1.getBlue());
        HSV hsv2=HSV.RGB2HSV(color2.getRed(),color2.getGreen(),color2.getBlue());
        return HSV.distanceOf(hsv1,hsv2);
    }

    public static String Color2String(Color color) {
        String R = Integer.toHexString(color.getRed());
        R = R.length() < 2 ? ('0' + R) : R;
        String B = Integer.toHexString(color.getBlue());
        B = B.length() < 2 ? ('0' + B) : B;
        String G = Integer.toHexString(color.getGreen());
        G = G.length() < 2 ? ('0' + G) : G;
        return '#' + R + B + G;
    }

    public static Color String2Color(String str) {
        int i = Integer.parseInt(str.substring(1), 16);
        return new Color(i);
    }

    public static void main(String[] args){
        System.out.println(String2Color("#000000"));
        System.out.println(String2Color("#fff"));
        double d=getColorDistanceOf(String2Color("#000000"),String2Color("#101010"));
        System.out.println(d);
    }
}
