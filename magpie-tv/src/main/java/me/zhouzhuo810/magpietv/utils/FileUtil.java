package me.zhouzhuo810.magpietv.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
     *
     * @param filePath 文件路径
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
     *
     * @param file 文件
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
    
    
    public static void saveFile(InputStream is, String dir, String fileName) {
        saveFile(is, dir + File.separator + fileName);
    }
    
    public static void saveFile(InputStream is, String filePath) {
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                Log.e("saveFile", e.getMessage());
            }
        }
    }
    
    /**
     * 清除 App 所有数据
     *
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public static boolean cleanAppData(final File... dirs) {
        boolean isSuccess = FileUtil.cleanInternalCache();
        isSuccess &= FileUtil.cleanInternalDbs();
        isSuccess &= FileUtil.cleanInternalSp();
        isSuccess &= FileUtil.cleanInternalFiles();
        isSuccess &= FileUtil.cleanExternalCache();
        for (File dir : dirs) {
            isSuccess &= FileUtil.cleanCustomCache(dir);
        }
        return isSuccess;
    }
    
    /**
     * 清除内部缓存
     * <p>/data/data/com.xxx.xxx/cache</p>
     *
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalCache() {
        return deleteFilesInDir(BaseUtil.getApp().getCacheDir());
    }
    
    
    /**
     * 清除外部缓存
     * <p>/storage/emulated/0/android/data/com.xxx.xxx/cache</p>
     *
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanExternalCache() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
            && deleteFilesInDir(BaseUtil.getApp().getExternalCacheDir());
    }
    
    
    /**
     * 清除自定义目录下的文件
     *
     * @param dir 目录
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanCustomCache(final File dir) {
        return deleteFilesInDir(dir);
    }
    
    
    /**
     * 清除内部 SP
     * <p>/data/data/com.xxx.xxx/shared_prefs</p>
     *
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalSp() {
        return deleteFilesInDir(new File(BaseUtil.getApp().getFilesDir().getParent(), "shared_prefs"));
    }
    
    
    /**
     * 清除内部文件
     * <p>/data/data/com.xxx.xxx/files</p>
     *
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalFiles() {
        return deleteFilesInDir(BaseUtil.getApp().getFilesDir());
    }
    
    /**
     * 清除内部数据库
     * <p>/data/data/com.xxx.xxx/databases</p>
     *
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalDbs() {
        return deleteFilesInDir(new File(BaseUtil.getApp().getFilesDir().getParent(), "databases"));
    }
    
    
    private static boolean deleteFilesInDir(final File dir) {
        if (dir == null)
            return false;
        // 目录不存在返回 true
        if (!dir.exists())
            return true;
        // 不是目录返回 false
        if (!dir.isDirectory())
            return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete())
                        return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file))
                        return false;
                }
            }
        }
        return true;
    }
    
    private static boolean deleteDir(final File dir) {
        if (dir == null)
            return false;
        // 目录不存在返回 true
        if (!dir.exists())
            return true;
        // 不是目录返回 false
        if (!dir.isDirectory())
            return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete())
                        return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file))
                        return false;
                }
            }
        }
        return dir.delete();
    }
    
    
}
