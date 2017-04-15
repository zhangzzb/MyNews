package com.zzb.mynew.common.commonutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.zzb.mynew.common.baseapp.BaseApplication;
import com.zzb.mynew.util.StringUtils;
import com.zzb.mynew.util.TDevice;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 张智斌
 * @time 2017/3/27 14:53
 * @desc 文件操作类
 */

public class FileUtil {
    public static final String ROOT_DIR = "Android/data/"
            + TDevice.getPackageName();
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "icon";

    /** 判断SD卡是否挂载 */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /** 获取下载目录 */
    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    /** 获取缓存目录 */
    public static String getCacheDir() {
        return getDir(CACHE_DIR);
    }

    /** 获取icon目录 */
    public static String getIconDir() {
        return getDir(ICON_DIR);
    }

    /** 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录 */
    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /** 获取SD下的应用目录 */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }

    /** 获取应用的cache目录 */
    public static String getCachePath() {
        File f = BaseApplication.getAppContext().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    /** 创建文件夹 */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /** 复制文件，可以选择是否删除源文件 */
    public static boolean copyFile(String srcPath, String destPath,
                                   boolean deleteSrc) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        return copyFile(srcFile, destFile, deleteSrc);
    }

    /** 复制文件，可以选择是否删除源文件 */
    public static boolean copyFile(File srcFile, File destFile,
                                   boolean deleteSrc) {
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
                out.flush();
            }
            if (deleteSrc) {
                srcFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
           close(out);
           close(in);
        }
        return true;
    }

    /** 判断文件是否可写 */
    public static boolean isWriteable(String path) {
        try {
            if (StringUtils.isEmpty(path)) {
                return false;
            }
            File f = new File(path);
            return f.exists() && f.canWrite();
        } catch (Exception e) {
            return false;
        }
    }

    /** 修改文件的权限,例如"777"等 */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
        }
    }
    /** 关闭流 */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    /**
     * 把数据写入文件
     *
     * @param is
     *            数据流
     * @param path
     *            文件路径
     * @param recreate
     *            如果文件存在，是否需要删除重建
     * @return 是否写入成功
     */
    public static boolean writeFile(InputStream is, String path,
                                    boolean recreate) {
        boolean res = false;
        File f = new File(path);
        FileOutputStream fos = null;
        try {
            if (recreate && f.exists()) {
                f.delete();
            }
            if (!f.exists() && null != is) {
                File parentFile = new File(f.getParent());
                parentFile.mkdirs();
                int count = -1;
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(f);
                while ((count = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close(fos);
           close(is);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content
     *            需要写入的字符串
     * @param path
     *            文件路径名称
     * @param append
     *            是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(byte[] content, String path, boolean append) {
        boolean res = false;
        File f = new File(path);
        RandomAccessFile raf = null;
        try {
            if (f.exists()) {
                if (!append) {
                    f.delete();
                    f.createNewFile();
                }
            } else {
                f.createNewFile();
            }
            if (f.canWrite()) {
                raf = new RandomAccessFile(f, "rw");
                raf.seek(raf.length());
                raf.write(content);
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(raf);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content
     *            需要写入的字符串
     * @param path
     *            文件路径名称
     * @param append
     *            是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(String content, String path, boolean append) {
        return writeFile(content.getBytes(), path, append);
    }

    /**
     * 把键值对写入文件
     *
     * @param filePath
     *            文件路径
     * @param key
     *            键
     * @param value
     *            值
     * @param comment
     *            该键值对的注释
     */
    public static void writeProperties(String filePath, String key,
                                       String value, String comment) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);// 先读取文件，再把键值对追加到后面
            p.setProperty(key, value);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
            close(fos);
        }
    }

    /** 根据值读取 */
    public static String readProperties(String filePath, String key,
                                        String defaultValue) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return null;
        }
        String value = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            value = p.getProperty(key, defaultValue);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           close(fis);
        }
        return value;
    }

    /** 把字符串键值对的map写入文件 */
    public static void writeMap(String filePath, Map<String, String> map,
                                boolean append, String comment) {
        if (map == null || map.size() == 0 || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            Properties p = new Properties();
            if (append) {
                fis = new FileInputStream(f);
                p.load(fis);// 先读取文件，再把键值对追加到后面
            }
            p.putAll(map);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close(fis);
            close(fos);
        }
    }

    /** 把字符串键值对的文件读入map */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String, String> readMap(String filePath,
                                              String defaultValue) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        Map<String, String> map = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            map = new HashMap<String, String>((Map) p);// 因为properties继承了map，所以直接通过p来构造一个map
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close(fis);
        }
        return map;
    }

    /** 改名 */
    public static boolean copy(String src, String des, boolean delete) {
        File file = new File(src);
        if (!file.exists()) {
            return false;
        }
        File desFile = new File(des);
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
           close(in);
           close(out);
        }
        if (delete) {
            file.delete();
        }
        return true;
    }
    /**
     * 大图压缩
     */
    public static Bitmap getBitmap(String path){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //只请求图片宽高，不解析图片像素
        opts.inJustDecodeBounds = true;
        //返回null，获取图片宽高，保存在opts对象中
        Bitmap bitmap = BitmapFactory.decodeFile(path, opts);
        //获取图片宽高
        int imageWidth = opts.outWidth;
        int imageHeight = opts.outHeight;
        //获取屏幕宽高
        int screenWith= DisplayUtil.getScreenWidth(BaseApplication.getAppContext());
        int screenHeight=DisplayUtil.getScreenHeight(BaseApplication.getAppContext());
        //计算缩放比例
        int scale=1;
        int scaleWidth = imageWidth / screenWith;
        int scaleHeight = imageHeight / screenHeight;
        //判断取哪个比例
        if(scaleWidth >= scaleHeight && scaleWidth > 1){
            scale = scaleWidth;
        }
        else if(scaleWidth < scaleHeight && scaleHeight > 1){
            scale = scaleHeight;
        }
        //设置缩小比例
        opts.inSampleSize = scale;
        opts.inPreferredConfig= Bitmap.Config.RGB_565;
        opts.inJustDecodeBounds = false;
        //获取缩小后的图片的像素信息
        bitmap = BitmapFactory.decodeFile(path, opts);
        return bitmap;
    }
    /**
     *
     * @param bitmap 位图对象
     * @param folder 文件夹
     * @param fileName 文件名
     * @return
     */
    public static boolean compressFile(Bitmap bitmap,String folder,
                                       String fileName){
        boolean writeSucc = false;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        String folderPath = "";
        if (sdCardExist) {
            folderPath = Environment.getExternalStorageDirectory()
                    + File.separator + folder + File.separator;
        } else {
            writeSucc = false;
        }
        File fileDir = new File(folderPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(folderPath + fileName);
        if(!file.exists()){
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
                writeSucc=true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                writeSucc=false;
            }
        }else{
            writeSucc=true;
        }
        return writeSucc;
    }
}
