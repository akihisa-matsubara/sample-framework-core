package dev.sample.framework.jsf.config;

import lombok.experimental.UtilityClass;
import dev.sample.framework.core.config.ConfigUtils;

/**
 * ページ設定.
 */
@UtilityClass
public class PageConfig {
  /** キー情報：セッションタイムアウトページ. */
  private static final String KEY_SESSION_TIMEOUT_PAGE = "framework.error-page.session-timeout";
  /** キー情報：不正操作ページ. */
  private static final String KEY_ILLEGAL_OPERATION_PAGE = "framework.error-page.illegal-operation";

  /**
   * セッションタイムアウトページを取得します.
   *
   * @return セッションタイムアウトページ
   */
  public static String getSessionTimeoutPage() {
    return ConfigUtils.getAsString(KEY_SESSION_TIMEOUT_PAGE);
  }

  /**
   * 不正操作ページを取得します.
   *
   * @return 不正操作ページ
   */
  public static String getIllegalOperationPage() {
    return ConfigUtils.getAsString(KEY_ILLEGAL_OPERATION_PAGE);
  }

}
