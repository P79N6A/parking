package com.zhuyitech.parking.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.apache.commons.codec.binary.Base64;


/**
 * 将图片转换为Base64<br>
 * 将base64编码字符串解码成img图片
 */
public class ImageBase64 {

//    public static void main(String[] args) {
//        String imgFile = "d:\\3.jpg";//待处理的图片
//        String imgbese = getImgStr(imgFile);
//        System.out.println(imgbese.length());
//        System.out.println(imgbese);
//        String imgFilePath = "d:\\332.jpg";//新生成的图片
//        generateImage(imgbese, imgFilePath);
//    }

    /**
     * 将图片转换成Base64编码
     *
     * @param imgFile 待处理图片
     */
    public static String getImgStr(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
        }
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 将网络图片转为base64编码的字符串
     *
     * @param inputStream 图片输入流
     * @return String
     */
    public static String getImageStrFromUrl(InputStream inputStream) throws Exception {
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inputStream);
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr      图片数据
     * @param imgFilePath 保存图片全路径地址
     */
    public static boolean generateImage(String imgStr, String imgFilePath) {
        //图像数据为空
        if (imgStr == null) {
            return false;
        }
        try {
            File f = new File(imgFilePath);
            if (!f.getParentFile().exists()) {
                f.setWritable(true, false);
                f.getParentFile().mkdirs();
            }
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    //调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
