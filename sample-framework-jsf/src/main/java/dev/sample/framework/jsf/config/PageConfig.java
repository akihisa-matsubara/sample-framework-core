package dev.sample.framework.jsf.config;

import dev.sample.framework.core.config.ConfigUtils;
import lombok.experimental.UtilityClass;

/**
 * ページ設定.
 */
@UtilityClass
public class PageConfig {
  /** キー情報：セッションタイムアウトページ. */
  private static final String KEY_SESSION_TIMEOUT_PAGE = "framework.errorPage.sessionTimeout";
  /** キー情報：不正操作ページ. */
  private static final String KEY_ILLEGAL_OPERATION_PAGE = "framework.errorPage.illegalOperation";
  /** キー情報：内部サーバーエラーページ. */
  private static final String KEY_INTERNAL_SERVER_ERROR_PAGE = "framework.errorPage.internalServerError";

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

  /**
   * 内部サーバーエラーページを取得します.
   *
   * @return 内部サーバーエラーページ
   */
  public static String getInternalServerErrorPage() {
    return ConfigUtils.getAsString(KEY_INTERNAL_SERVER_ERROR_PAGE);
  }

}
