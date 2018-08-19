package me.zhouzhuo810.magpie.utils;

import java.io.File;

/**
 * 文件操作工具类
 */
public class FileUtil {

    private FileUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 创建文件夹
     *
     * @param dirPath 文件夹路径
     * @return 是否成功
     */
    public static boolean mkDirs(String dirPath) {
        if (dirPath == null) {
            return false;
        }
        File dir = new File(dirPath);
        return dir.exists() || dir.mkdirs();
    }

    /**
     * 文件是否存在
     *
     * @param filePath 文件路径
     * @return 是否存在
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 删除文件
     * @param filePath 文件路径
     * @return
     */
    public static boolean deleteFile(String filePath) {
        if (filePath == null) {
            return false;
        }
        File file = new File(filePath);
        return deleteFile(file);
    }
    /**
     * 删除文件
     * @param file 文件
     * @return
     */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File childFile : files) {
                    deleteFile(childFile);
                }
            }
            return false;
        } else {
            return file.exists() && file.delete();
        }
    }



}
