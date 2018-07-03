package com.htkapp.core.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChangeImageSize {

    /**
     * /** 缩放图像
     *
     * @param file
     *            源图像文件地址
     * @param widths
     *            图片宽度
     * @param heights
     *            图片高度
     * @return
     */
    public static String scale(String file, int widths, int heights,String imgStyle) {
        String newPath = ChangeImageSize.getPath(file);
        try {
            BufferedImage src = ImageIO.read(new File(file)); // 读入文件
            Image image = src.getScaledInstance(widths, heights, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(widths, heights, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
//            ImageIO.write(tag, "JPEG", new File(newPath));// 输出到文件流
            ImageIO.write(tag, imgStyle, new File(newPath));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return newPath;
        String name = newPath.substring(newPath.lastIndexOf("\\") + 1, newPath.lastIndexOf("."))+"."+imgStyle;
        return name;
    }

    /**
     * 根据路径生成新路径
     *            根据给定的路径生成一个同文件夹下的路径，区别是文件名称前加Copy
     * @return
     */
    public static String getPath(String file) {
        String name = file.substring(file.lastIndexOf("\\") + 1, file.lastIndexOf("."));
        file = file.replaceAll(name, "Copy" + name);
        return file;
    }
}