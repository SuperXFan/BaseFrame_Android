package cc.ewell.baseframe.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import cc.ewell.baseframe.R;

/**
 * Created by fan on 2017/4/6.
 */

public class ColorUtil {

//    public static int[] colors = new int[]{
//            R.color.blue_color_0057e6, R.color.orange_color_e37900, R.color.blue_color_0093e6, R.color.pink_color_ee6a6a, R.color.blue_color_00c5da,
//            R.color.blue_color_2f51a5,R.color.green_color_11cd9b, R.color.blue_color_414ad1, R.color.purple_color_c866e0, R.color.orange_color_a2d000,
//            R.color.pink_color_f368c5, R.color.purple_color_9657f1, R.color.orange_color_dc9000, R.color.blue_color_0077e6, R.color.orange_color_ff802c,
//            R.color.blue_color_00aee6, R.color.pink_color_f56b8f,  R.color.pink_color_ec4a78, R.color.orange_color_d0c900, R.color.blue_color_4167dd,
//            R.color.purple_color_d153bc, R.color.green_color_63c700, R.color.purple_color_a93ee2,  R.color.orange_color_d0ac00, R.color.purple_color_7e57f1,
//            R.color.blue_color_6a62fa, R.color.green_color_12c255, R.color.blue_color_4450fc, R.color.blue_color_00d1ce,  R.color.purple_color_cd68f3};
    public static int[] colors = new int[]{};

    private static int[] alphaColors = new int[]{};

    /**
     * 产生一个drawable
     */
    public static Drawable generateDrawable(Context context, int position) {
        // 设置字体颜色和背景
        int index = position % colors.length;
        int strokeWidth = 1; // 边框宽度
        int roundRadius = 8; // 圆角半径

        int strokeColor = context.getResources().getColor(colors[index]);
        int fillColor = context.getResources().getColor(R.color.white);

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }
    /**
     * 产生一个drawable
     */
    public static Drawable generateDrawable(Context context, int position,int roundRadius) {
        // 设置字体颜色和背景
        int index = position % colors.length;
        int strokeWidth = 1; // 边框宽度

        int strokeColor = context.getResources().getColor(colors[index]);
        int fillColor = context.getResources().getColor(R.color.white);

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }

    public static int generateColor(Context context, int position) {
        return context.getResources().getColor(colors[position % colors.length]);
    }

    /**
     * 产生一个drawable
     */
    public static Drawable generateRectAlphaBgDrawable(Context context, int position,int strokeWidth,int roundRadius) {
        // 设置字体颜色和背景
        int index = position % colors.length;

        int strokeColor = context.getResources().getColor(colors[index]);
        int fillColor = context.getResources().getColor(alphaColors[index]);

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }

    /**
     * 产生一个drawable
     */
    public static Drawable generateRectSolidDrawable(Context context, int position,int roundRadius) {
        // 设置字体颜色和背景
        int index = position % colors.length;

//        int strokeColor = context.getResources().getColor(colors[index]);
        int fillColor = context.getResources().getColor(colors[index]);

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
//        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }
    /**
     * 根据传递的颜色值，产生一个drawable
     */
    public static Drawable generateRectSolidDrawable(int colorResource,Context context,int roundRadius) {
        // 设置字体颜色和背景
//        int index = position % colors.length;
//
////        int strokeColor = context.getResources().getColor(colors[index]);
//        int fillColor = context.getResources().getColor(colors[index]);

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(colorResource);
        gd.setCornerRadius(roundRadius);
//        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }
}
