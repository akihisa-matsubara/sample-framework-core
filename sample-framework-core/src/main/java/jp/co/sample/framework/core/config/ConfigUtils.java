package jp.co.sample.framework.core.config;

import jp.co.sample.framework.core.message.CoreMessageId;
import jp.co.sample.framework.core.util.MessageUtils;
import jp.co.sample.framework.core.util.PropertiesUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 設定 ユーティリティー.
 */
@UtilityClass
public class ConfigUtils {

  /** デフォルトファイル名. */
  private static final String DEFAULT_FILE_NAME = "/application.properties";

  /** プロファイル置換文字列. */
  private static final String REPLACE_PROFILE_STR = "profile";

  /** プロファイル別ファイル名. */
  private static final String PROFILE_FILE_NAME = "/application-" + REPLACE_PROFILE_STR + ".properties";

  /** 設定値が存在しない場合のint型デフォルト値. */
  private static final int UNDEFINED = -1;

  /** プロファイル共通設定ファイル. */
  private static Properties commonConfiguration;

  /** プロファイル別設定ファイル. */
  private static Properties profileConfiguration;

  static {
    commonConfiguration = PropertiesUtils.get(DEFAULT_FILE_NAME);
    profileConfiguration = PropertiesUtils.get(PROFILE_FILE_NAME.replaceAll(REPLACE_PROFILE_STR, ActiveProfile.PROFILE));
  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param key キー
   * @return 設定値、設定値が存在しない場合は-1
   */
  public static int getAsInteger(String key) {
    return NumberUtils.toInt(getValue(key), UNDEFINED);

  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param key キー
   * @return 設定値、設定値が存在しない場合はnull
   */
  public static String getAsString(String key) {
    return getValue(key);

  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param key キー
   * @return 設定値、設定値が存在しない場合はnull
   */
  public static List<String> getAsList(String key) {
    return Arrays.asList(getValue(key).split("\\s*,\\s*"));

  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * プロファイル別設定ファイルに設定値が存在しない場合は、プロファイル共通設定ファイルから取得します.
   *
   * @param key キー
   * @return 設定値
   * @throws IllegalArgumentException 設定値がいずれも存在しない場合
   */
  private static String getValue(String key) {
    String value = null;
    if (profileConfiguration != null && profileConfiguration.containsKey(key)) {
      value = profileConfiguration.getProperty(key);
    } else if (commonConfiguration != null && commonConfiguration.containsKey(key)) {
      value = commonConfiguration.getProperty(key);
    }

    if (value == null) {
      throw new IllegalArgumentException(MessageUtils.getMessage(CoreMessageId.F0007E, key));
    }

    return value;

  }

}
