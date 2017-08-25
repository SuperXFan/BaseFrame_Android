package cc.ewell.common.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDCardUtil {

    public static final String PROJECT_NAME = "Baseframe";
    public static final String COMPRESS_PIC_DIR = "compressPicDir/";
    public static final String APP_UPDATE_DIR = "appUpdateDir";

    // 根目录下的PROJECT_NAME
    public static final String FILE_STORE_DIR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + PROJECT_NAME;

    public static final String CACHE = FILE_STORE_DIR_PATH + File.separator + "cache"; // cache目录

    // 产品说明书
    public static final String PRODUCT_MANUAL_DIR = "manual";
    public static final String PRODUCT_MANUAL_FILE_NAME = "manual.pdf";

    /**
     * cache目录 /Baseframe/cache
     */
    public static String getCacheDir() {
        return CACHE;
    }

    /**
     * video 的大缩略图目录
     */
    public static String getVideoBigThumb(Context context) {
        return CACHE + File.separator + CommonAccountUtil.getUserId(context) + File.separator + "thumb" + File.separator + "video" + File.separator + "big";
    }

    /**
     * video 的大缩略图目录
     */
    public static String getVideoSmallThumb(Context context) {
        return CACHE + File.separator + CommonAccountUtil.getUserId(context) + File.separator + "thumb" + File.separator + "video" + File.separator + "small";
    }

    /**
     * 图片大缩略图目录
     */
    public static String getPictBigThumb(Context context) {
        return CACHE + File.separator + CommonAccountUtil.getUserId(context) + File.separator + "thumb" + File.separator + "pict" + File.separator + "big";
    }

    /**
     * 图片小缩略图目录
     */
    public static String getPictSmallThumb(Context context) {
        return CACHE + File.separator + CommonAccountUtil.getUserId(context) + File.separator + "thumb" + File.separator + "pict" + File.separator + "small";
    }

    /**
     * video缓存目录
     */
    public static String getVideoDir(Context context) {
        return CACHE + File.separator + CommonAccountUtil.getUserId(context) + File.separator + "video";
    }

    /**
     * SD卡是否挂�?
     *
     * @return
     */
    public static boolean isSDcardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD�?内部存储的路�?
     *
     * @return
     */
    public static String getSDcardPath() {
        return isSDcardMounted() ? Environment.getExternalStorageDirectory().getAbsolutePath() : null;
    }

    // MB
    public static long getSDcardSize() {
        if (isSDcardMounted()) {
            StatFs fs = new StatFs(getSDcardPath());
            long size = fs.getBlockSize();
            long count = fs.getBlockCount();
            return size * count / 1024 / 1024;
        }
        return 0;
    }

    // MB
    public static long getSDCardFreeSize() {
        if (isSDcardMounted()) {
            StatFs fs = new StatFs(getSDcardPath());
            long size = fs.getBlockSize();
            long count = fs.getAvailableBlocks();
            return size * count / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 存储到外部设备
     */
    public static boolean saveFileToExternalStorage(byte[] b, String dir, String filename, Context context) {
        if (isSDcardMounted()) {
            FileOutputStream openFileOutput = null;
            try {
                File dirFile = new File(FILE_STORE_DIR_PATH + File.separator + dir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                File storeFile = new File(FILE_STORE_DIR_PATH + File.separator + dir, filename);
                if (storeFile.exists()) {
                    storeFile.delete();
                }
                openFileOutput = new FileOutputStream(storeFile);
                openFileOutput.write(b, 0, b.length);
                return true;
            } catch (Exception e) {
                if (e != null)
                    e.printStackTrace();

            } finally {
                if (openFileOutput != null) {
                    try {
                        openFileOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 从外部设备读取
     */
    public static byte[] loadFileFromExternalStorage(String dir, String filename, Context context) {
        ByteArrayOutputStream baos = null;
        BufferedInputStream bis = null;
        if (isSDcardMounted()) {
            File file = new File(FILE_STORE_DIR_PATH + File.separator + dir, filename);
            if (file.exists()) {
                try {
                    bis = new BufferedInputStream(new FileInputStream(file));
                    baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024 * 8];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    return baos.toByteArray();
                } catch (Exception e) {
                    if (e != null)
                        e.printStackTrace();
                } finally {
                    try {
                        if (bis != null) {
                            bis.close();
                        }
                        if (baos != null) {
                            baos.close();
                        }
                    } catch (Exception e) {
                        if (e != null)
                            e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 从内部存储读
     *
     * @param context
     * @param dir      仅文件夹路径
     * @param fileName 文件名
     * @return
     */
    public static byte[] loadFileFromInnerStorage(Context context, String dir, String fileName) {

        ByteArrayOutputStream baos = null;
        BufferedInputStream bis = null;
        File file = new File(context.getFilesDir() + File.separator + dir, fileName);
        if (file.exists()) {
            try {
                bis = new BufferedInputStream(new FileInputStream(file));
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return baos.toByteArray();
            } catch (Exception e) {
                if (e != null)
                    e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (baos != null) {
                        baos.close();
                    }
                } catch (Exception e) {
                    if (e != null)
                        e.printStackTrace();
                }
            }
        }
        return null;

    }

    /**
     * 写到内部存储
     *
     * @param context
     * @param dir      仅文件夹路径
     * @param fileName 文件名
     * @return
     */
    public static boolean saveFileToInnerStorage(Context context, byte[] b, String dir, String fileName) {

        FileOutputStream fos = null;

        try {
            File fileDir = new File(context.getFilesDir() + File.separator + dir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            File file = new File(context.getFilesDir() + File.separator + dir, fileName);
            if (file.exists()) {
                file.delete();
            }

            fos = new FileOutputStream(file);
            fos.write(b);
            return true;
        } catch (Exception e) {
            if (e != null)
                e.printStackTrace();
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }

    /**
     * 判断一个文件是否存在
     *
     * @param dir      目录
     * @param fileName 文件名
     * @return 存在, 返回true
     */
    public static boolean isFileExist(String dir, String fileName) {
        if (isSDcardMounted()) {
            File file = new File(FILE_STORE_DIR_PATH + File.separator + dir, fileName);
            return file.exists();
        } else {
            return false;
        }
    }

    public static boolean isFileExistInCache(String dir, String fileName) {
        File file = new File(dir, fileName);
        return file.exists();
    }

    /**
     * 压缩图片的路径
     *
     * @return
     */
    public static String getCompressImageDir() {
        String root = FILE_STORE_DIR_PATH + File.separator + COMPRESS_PIC_DIR;
        File file = new File(root);
        if (!file.exists()) file.mkdir();
        return root;
    }
}
