

package takjxh.android.com.commlibrary.utils.encrypt;

import android.text.TextUtils;
import android.util.Base64;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import takjxh.android.com.commlibrary.utils.IOUtil;

/**
 * Base64 工具类
 */
public class Base64Util {

  /**
   * Base64加密
   */
  public static String encode(String string) {
    if (TextUtils.isEmpty(string)) {
      return "";
    }
    return Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
  }

  /**
   * Base64解密
   */
  public static String decode(String string) {
    if (TextUtils.isEmpty(string)) {
      return "";
    }
    return new String(Base64.decode(string, Base64.DEFAULT));
  }

  /**
   * Base64加密
   */
  public static String encode(File file) {
    if (null == file) {
      return "";
    }

    FileInputStream inputFile = null;
    try {
      inputFile = new FileInputStream(file);
      byte[] buffer = new byte[(int) file.length()];
      inputFile.read(buffer);
      return Base64.encodeToString(buffer, Base64.DEFAULT);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtil.close(inputFile);
    }
    return "";
  }

  /**
   * Base64解密
   */
  public static File decode(String filePath, String content) {
    if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(content)) {
      return null;
    }

    FileOutputStream fos = null;
    File desFile = new File(filePath);
    try {
      byte[] decodeBytes = Base64.decode(content.getBytes(), Base64.DEFAULT);
      fos = new FileOutputStream(desFile);
      fos.write(decodeBytes);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      IOUtil.close(fos);
    }
    return desFile;
  }
}
