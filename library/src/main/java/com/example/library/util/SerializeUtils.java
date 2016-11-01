package com.example.library.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serialize Utils
 * 描述：序列化工具类
 * 时间：2016年9月14日
 * 
 */
public abstract class SerializeUtils {

    private SerializeUtils() {
        throw new AssertionError();
    }

    /**
     * 从文件反序列化对象
     * Deserialization object from file.
     * 
     * @param filePath 路径 file path
     * @return de-serialized object
     * @throws RuntimeException if an error occurs
     */
    public static Object deserialization(String filePath) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filePath));
            Object o = in.readObject();
            in.close();
            return o;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtils.close(in);
        }
    }

    /**
     *将对象序列化到文件。
     * Serialize object to file.
     * 
     * @param filePath 路径 file path
     * @param obj object
     * @throws RuntimeException if an error occurs
     */
    public static void serialization(String filePath, Object obj) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filePath));
            out.writeObject(obj);
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtils.close(out);
        }
    }

    /**
     * 序列化
     * */
    public String Serialize() {
        String json = null;
        try {
            json = JsonUtils.SerializeJSON(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
