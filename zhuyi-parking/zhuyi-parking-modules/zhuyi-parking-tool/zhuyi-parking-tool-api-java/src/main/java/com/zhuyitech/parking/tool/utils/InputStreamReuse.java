package com.zhuyitech.parking.tool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author AkeemSuper
 * @date 2018/11/3 0003
 */
public class InputStreamReuse {

    private static final Logger LOG = LoggerFactory.getLogger(InputStreamReuse.class);

    /**
     * 将InputStream中的字节保存到ByteArrayOutputStream中。
     */
    private ByteArrayOutputStream byteArrayOutputStream = null;

    public InputStreamReuse(InputStream inputStream) {
        if (null == inputStream) {
            return;
        }
        byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
                // TODO Auto-generated catch block
            }
        }
    }

    public InputStream getInputStream() {
        if (null == byteArrayOutputStream) {
            return null;
        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
