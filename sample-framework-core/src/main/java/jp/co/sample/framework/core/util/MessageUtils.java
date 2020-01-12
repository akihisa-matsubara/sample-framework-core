package jp.co.sample.framework.core.util;

import jp.co.sample.common.constant.SystemProperty;
import jp.co.sample.framework.core.exception.dto.ErrorMessage;
import jp.co.sample.framework.core.message.CoreMessageId;
import jp.co.sample.framework.core.message.MessageId;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * メッセージ・ユーティリティー.
 */
@UtilityClass
@Slf4j
public class MessageUtils {

  /** メッセージ基底名. */
  private static final String MESSAGE_BASE_NAME = "messages";
  /** FWメッセージ基底名. */
  private static final String FW_MESSAGE_BASE_NAME = "core-messages";

  /** FWメッセージプロパティ. */
  private static ResourceBundle coreMessages = null;
  /** メッセージプロパティ. */
  private static ResourceBundle messages = null;

  static {
    // JVMにキャッシュされるのでクリア
    ResourceBundle.clearCache(MessageUtils.class.getClassLoader());

    try {
      coreMessages = ResourceBundle.getBundle(FW_MESSAGE_BASE_NAME);
      log.info(MessageUtils.getMessage(CoreMessageId.F0006I, coreMessages.getBaseBundleName(), SystemProperty.LANGUAGE));
    } catch (MissingResourceException mre) {
      throw new ExceptionInInitializerError("メッセージプロパティファイルの読み込みに失敗しました。");
    }

    try {
      // Localeは環境依存
      messages = ResourceBundle.getBundle(MESSAGE_BASE_NAME);
      log.info(MessageUtils.getMessage(CoreMessageId.F0006I, messages.getBaseBundleName(), SystemProperty.LANGUAGE));

    } catch (MissingResourceException mre) {
      // do nothing.
    }

  }

  /**
   * メッセージを取得します.
   *
   * @param messageId メッセージID
   * @param params パラメーター
   * @return メッセージ
   */
  public static String getMessage(MessageId messageId, String... params) {
    return MessageFormat.format(MessageUtils.getMessageTemplate(messageId), (Object[] )params);
  }

  /**
   * エラーメッセージを取得します.
   *
   * @param errorDto エラーDto
   * @return メッセージ
   */
  public static String getErrorMessage(ErrorMessage errorDto) {
    return getMessage(errorDto.getMessageId(), errorDto.getParams());
  }

  /**
   * メッセージテンプレートを取得します.
   *
   * @param messageId メッセージID
   * @return メッセージテンプレート
   */
  private static String getMessageTemplate(MessageId messageId) {
    String messageTemplate = "";
    if (messages != null && messages.containsKey(messageId.name())) {
      messageTemplate = messages.getString(messageId.name());

    } else if (coreMessages.containsKey(messageId.name())) {
      messageTemplate = coreMessages.getString(messageId.name());

    }

    return messageTemplate;
  }
}
