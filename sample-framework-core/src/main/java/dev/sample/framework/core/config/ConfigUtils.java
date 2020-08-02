package dev.sample.framework.core.config;

import dev.sample.common.constant.Profile;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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

  /** キーが存在しない場合のint型デフォルト値. */
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
    Config envConfig = ConfigFactory.load(PROFILE_FILE_NAME.replace(REPLACE_PROFILE_STR, getActiveProfile()));
    config = envConfig.withFallback(config);
    dump();
  }

  /**
   * 指定されたキーに対応したboolean型の設定値を取得します.
   *
   * @param key キー
   * @return 設定値、キーが存在しない場合はfalse
   */
  public static boolean getAsBoolean(String key) {
    return config.hasPath(key) && config.getBoolean(key);
  }

  /**
   * 指定されたキーに対応したint型の設定値を取得します.
   *
   * @param key キー
   * @return 設定値、キーが存在しない場合は-1
   */
  public static int getAsInt(String key) {
    return config.hasPath(key) ? config.getInt(key) : UNDEFINED;
  }

  /**
   * 指定されたキーに対応したString型の設定値を取得します.
   *
   * @param key キー
   * @return 設定値、キーが存在しない場合はnull
   */
  public static String getAsString(String key) {
    return config.hasPath(key) ? config.getString(key) : null;
  }

  /**
   * 指定されたキーに対応した{@code List<String>}型の設定値を取得します.
   *
   * @param key キー
   * @return 設定値、キーが存在しない場合はnull
   */
  public static List<String> getAsStringList(String key) {
    return config.hasPath(key) ? config.getStringList(key) : null;
  }

  /**
   * 指定されたキーに対応したJava Beanの設定値を取得します.
   *
   * @param <T> Java Beanの型
   * @param key キー
   * @param clazz Java Bean Class
   * @return 設定値、キーが存在しない場合はnull
   */
  public static <T extends Configurable> T getAsBean(String key, Class<T> clazz) {
    return config.hasPath(key) ? ConfigBeanFactory.create(config.getConfig(key), clazz) : null;
  }

  /**
   * 指定されたキーに対応したJava Beanのリストの設定値を取得します.
   *
   * @param <T> Java Beanの型
   * @param key キー
   * @param clazz Java Bean Class
   * @return 設定値、キーが存在しない場合はnull
   */
  public static <T extends Configurable> List<T> getAsBeanList(String key, Class<T> clazz) {
    return config.hasPath(key) ? config.getConfigList(key).stream()
        .map(config -> ConfigBeanFactory.create(config, clazz))
        .collect(Collectors.toList()) : null;
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
    config.entrySet().stream()
        .sorted(Map.Entry.<String, ConfigValue>comparingByKey())
        .forEach(entry -> log.debug("dump - key:{}, value:{}", entry.getKey(), entry.getValue().render()));
  }
}
