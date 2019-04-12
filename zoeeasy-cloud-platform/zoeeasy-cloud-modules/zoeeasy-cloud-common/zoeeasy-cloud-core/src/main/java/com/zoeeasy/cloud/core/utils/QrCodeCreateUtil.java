package com.zoeeasy.cloud.core.utils;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

/**
 * @author walkman
 * 二维码生成和读的工具类
 */
@Slf4j
public class QrCodeCreateUtil {
    /**
     * 生成包含字符串信息的二维码图片返回流
     *
     * @param content     二维码携带信息
     * @param qrCodeSize  二维码图片大小
     * @param imageFormat 二维码的格式
     * @throws WriterException WriterException
     * @throws IOException IOException
     */
    public static ByteArrayOutputStream createQrCode(String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
        final BufferedImage qrCodeImage = createQrCodeImage(content, qrCodeSize);
        return imageToByteArray(qrCodeImage, imageFormat);
    }

    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param content    二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @throws WriterException WriterException
     * @throws IOException IOException
     */
    public static BufferedImage createQrCodeImage(String content, int qrCodeSize) throws WriterException, IOException {
        //设置二维码纠错级别ＭＡＰ
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        // 矫错级别
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(qrCodeSize, qrCodeSize, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, qrCodeSize, qrCodeSize);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        graphics.dispose();
        return image;
    }

    /**
     * 增加中间小图标
     *
     * @param image 原图
     * @param icon  小图标
     */
    private static void addIconToCenter(BufferedImage image, BufferedImage icon) {
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        //原图的七分之一不会影响扫码识别，六分之一就会影响
        reSize(icon, image.getWidth() / 7);
        graphics.drawImage(icon, image.getWidth() / 2 - icon.getWidth() / 2,
                image.getHeight() / 2 - icon.getHeight() / 2, null);
        graphics.dispose();
    }

    /**
     * 重置宽高
     *
     * @param src    原图
     * @param reSize 大小
     * @return 新图
     */
    private static BufferedImage reSize(BufferedImage src, int reSize) {
        Image image = src.getScaledInstance(reSize, reSize, Image.SCALE_DEFAULT);
        BufferedImage reSizeImage = new BufferedImage(reSize, reSize, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = reSizeImage.getGraphics();
        graphics.drawImage(image, 0, 0, null); // 绘制缩小后的图
        graphics.dispose();
        return reSizeImage;
    }

    /**
     * BufferedImage 转流
     *
     * @param src         BufferedImage
     * @param imageFormat 格式
     * @return ByteArrayOutputStream
     * @throws IOException IOException
     */
    public static ByteArrayOutputStream imageToByteArray(BufferedImage src, String imageFormat) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(src, imageFormat, imOut);
        return bs;
    }


    /**
     * 流转BufferedImage
     *
     * @param bytes 流
     * @return BufferedImage
     * @throws IOException IOException
     */
    public static BufferedImage byteArrayToImage(byte[] bytes) throws IOException {
        //从流中获取图片
        return ImageIO.read(new ByteArrayInputStream(bytes));
    }

}