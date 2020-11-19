package com.fei2e.getlost.util;

/**
 * @ClassName HSV
 * @DescripTion HSV颜色模型
 * @Author dell
 * @Date 2020/10/20 15:45
 * @Version 1.0
 **/
public class HSV {
    private double H;
    private double S;
    private double V;
    public HSV() {
    }
    public HSV(double h, double s, double v) {
        H = h;
        S = s;
        V = v;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }

    public double getV() {
        return V;
    }

    public void setV(double v) {
        V = v;
    }

    //self-defined
    private static final double R = 100;
    private static final double angle = 30;
    private static final double h = R * Math.cos(angle / 180 * Math.PI);
    private static final double r = R * Math.sin(angle / 180 * Math.PI);

    public static double distanceOf(HSV hsv1, HSV hsv2) {
        double x1 = r * hsv1.V * hsv1.S * Math.cos(hsv1.H / 180 * Math.PI);
        double y1 = r * hsv1.V * hsv1.S * Math.sin(hsv1.H / 180 * Math.PI);
        double z1 = h * (1 - hsv1.V);
        double x2 = r * hsv2.V * hsv2.S * Math.cos(hsv2.H / 180 * Math.PI);
        double y2 = r * hsv2.V * hsv2.S * Math.sin(hsv2.H / 180 * Math.PI);
        double z2 = h * (1 - hsv2.V);
        double dx = x1 - x2;
        double dy = y1 - y2;
        double dz = z1 - z2;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    /**
     * RGB转换HSV
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public static HSV RGB2HSV(int red, int green, int blue)
    {
        double r = ((double)red / 255.0);
        double g = ((double)green / 255.0);
        double b = ((double)blue / 255.0);
        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double hue = 0.0;
        if (max == r && g >= b)
        {
            if (max - min == 0) hue = 0.0;
            else hue = 60 * (g - b) / (max - min);
        }
        else if (max == r && g < b)
        {
            hue = 60 * (g - b) / (max - min) + 360;
        }
        else if (max == g)
        {
            hue = 60 * (b - r) / (max - min) + 120;
        }
        else if (max == b)
        {
            hue = 60 * (r - g) / (max - min) + 240;
        }
        double sat = (max == 0) ? 0.0 : (1.0 - (min / max));
        double bri = max;
        HSV hsv=new HSV(hue,sat,bri);
        return hsv;
    }
}
