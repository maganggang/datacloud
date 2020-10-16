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
    public static double getColorSemblance(Color color1, Color color2){
        // 此处Color为javafx.scene.paint.Color，getRed()为红色通道的程度，getRed() * 255为红色通道的值
        double semblance = (255 - (Math.abs(color1.getRed() - color2.getRed()) * 255 * 0.297 + Math.abs(color1.getGreen() - color2.getGreen()) * 255 * 0.593 + Math.abs(color1.getBlue() - color2.getBlue()) * 255 * 11.0 / 100)) / 255;
        return semblance;
    }
}
