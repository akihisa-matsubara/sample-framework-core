package jp.co.sample.framework.core.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.commons.lang3.StringUtils;

/**
 * 限定子 {@code @Config(key)} で指定されたキー値をインジェクションするプロデューサークラス.
 */
@ApplicationScoped
public class ConfigProducer {

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param ip インジェクションポイント
   * @return 設定値、設定値が存在しない場合は-1
   */
  @Produces
  @Config
  @Dependent
  public int getAsInteger(InjectionPoint ip) {
    return ConfigUtils.getAsInt(getKey(ip));
  }

  /**
   * 指定されたキーに対応した設定値を取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param ip インジェクションポイント
   * @return 設定値、設定値が存在しない場合はnull
   */
  @Produces
  @Config
  @Dependent
  public String getAsString(InjectionPoint ip) {
    return ConfigUtils.getAsString(getKey(ip));
  }

  /**
   * キーを取得します.
   * value属性に値が指定されていない場合、フィールド名をキーに設定します.
   *
   * @param ip インジェクションポイント
   * @return キー
   */
  private String getKey(InjectionPoint ip) {
    String key = ip.getAnnotated().getAnnotation(Config.class).value();
    if (StringUtils.isEmpty(key)) {
      key = ip.getMember().getName();
    }
    return key;
  }
}
