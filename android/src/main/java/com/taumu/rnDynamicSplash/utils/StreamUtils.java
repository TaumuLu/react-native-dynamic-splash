package com.taumu.rnDynamicSplash.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {

    /**
     * 把流对象转换成字符串对象
     *
     * @param is
     * @return
     */
    public static String streamToStr(InputStream is) {
        try {
            // 定义字节数组输出流对象
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            // 定义读取的长度
            int len = 0;
            // 定义读取的缓冲区
            byte buffer[] = new byte[1024];
            // 按照定义的缓冲区进行循环读取，直到读取完毕为止
            while ((len = is.read(buffer)) != -1) {
                // 根据读取的长度写入到字节数组输出流对象中
                os.write(buffer, 0, len);
            }
            // 关闭流
            is.close();
            os.close();
            // 把读取的字节数组输出流对象转换成字节数组
            byte data[] = os.toByteArray();
            // 按照指定的编码进行转换成字符串(此编码要与服务端的编码一致就不会出现乱码问题了，android默认的编码为UTF-8)
            return new String(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String streamToString(InputStream is)
    {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));

            StringBuilder sb = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
