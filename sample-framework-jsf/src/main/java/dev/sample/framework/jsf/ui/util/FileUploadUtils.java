package dev.sample.framework.jsf.ui.util;

import dev.sample.framework.jsf.exception.UiComponentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.Part;
import lombok.experimental.UtilityClass;

/**
 * ファイル アップロード・ユーティリティ.
 */
@UtilityClass
public class FileUploadUtils {

  /**
   * Part内のInputStreamをStringに変換します.
   *
   * @param file upload file
   * @return 文字列
   */
  public static String toString(Part file) {
    try (
        InputStream is = file.getInputStream();
        ByteArrayOutputStream result = new ByteArrayOutputStream()) {
      byte[] buffer = new byte[1024];
      int length;
      while ((length = is.read(buffer)) != -1) {
        result.write(buffer, 0, length);
      }
      return result.toString(StandardCharsets.UTF_8.name());

    } catch (IOException ioe) {
      throw new UiComponentException(ioe);

    }
  }

}
