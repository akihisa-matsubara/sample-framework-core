package dev.sample.framework.core.config;

import java.util.Map;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import dev.sample.common.constant.Profile;

/**
 * 設定 ユーティリティー.
 */
@UtilityClass
@Slf4j
public class ConfigUtils {

  /** プロファイル置換文字列. */
  private static final String REPLACE_PROFILE_STR = "profile";

  /** プロファイル別ファイル名. */
  private static final String PROFILE_FILE_NAME = "application-" + REPLACE_PROFILE_STR + ".conf";

  /** キー情報：有効なプロファイル. */
  private static final String KEY_ACTIVE_PROFILE = "active.profile";

  /** 設定値が存在しない場合のint型デフォルト値. */
  private static final int UNDEFINED = -1;

  /** 設定ファイル. */
  private static Config config;

  static {
    init();
  }

  /**
   * 初期化.
   */
  private static void init() {
    config = ConfigFactory.load();
    Config envConfig = ConfigFactory.load(PROFILE_FILE_NAME.replaceAll(REPLACE_PROFILE_STR, getActiveProfile()));
    config = envConfig.withFallback(config);
    dump();
  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param key キー
   * @return 設定値、設定値が存在しない場合はfalse
   */
  public static boolean getAsBoolean(String key) {
    return config.hasPath(key) && config.getBoolean(key);
  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param key キー
   * @return 設定値、設定値が存在しない場合は-1
   */
  public static int getAsInt(String key) {
    return config.hasPath(key) ? config.getInt(key) : UNDEFINED;
  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param key キー
   * @return 設定値、設定値が存在しない場合はnull
   */
  public static String getAsString(String key) {
    return config.getString(key);
  }

  /**
   * 有効なプロファイルを取得します(初期値はut).
   *
   * @see ActiveProfile#PROFILE
   * @return 有効なプロファイル
   */
  static String getActiveProfile() {
    String activeProfile = getAsString(KEY_ACTIVE_PROFILE);
    return StringUtils.isEmpty(activeProfile) ? Profile.UT : activeProfile;
  }

  /**
   * 設定ファイルの情報をダンプします.
   */
  private static void dump() {
    config.entrySet().stream().sorted(Map.Entry.<String, ConfigValue>comparingByKey())
        .forEach(entry -> log.debug("dump - key:{}, value:{}", entry.getKey(), entry.getValue().render()));
  }
}
