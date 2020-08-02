package dev.sample.framework.core.util;

import dev.sample.framework.core.exception.SystemException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import lombok.experimental.UtilityClass;

/**
 * ファイル・ユーティリティ.
 */
@UtilityClass
public class FileUtils {

  private static final int BUFFER_SIZE = 1024;

  /**
   * リソースファイルを読込み、文字列を返します.
   *
   * @param path リソースパス
   * @return 読み込んだ文字列
   */
  public static String readResourceToString(String path) {
    StringBuilder builder = new StringBuilder(BUFFER_SIZE);
    try (InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8));) {
      reader.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));

    } catch (IOException ioe) {
      throw new SystemException(ioe);
    }

    return builder.toString();
  }

}
