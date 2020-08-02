package dev.sample.framework.core.logger;

import dev.sample.framework.core.code.LoggerVo;
import dev.sample.framework.core.message.CoreMessageId;
import dev.sample.framework.core.util.MessageUtils;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用統計ロガー.
 */
@UtilityClass
public class UsageStatisticsLogger {

  /** 利用統計 Logger. */
  private static final Logger STATISTICS_LOGGER = LoggerFactory.getLogger(LoggerVo.STATISTICS_LOGGER.getCode());

  /**
   * 開始ログを出力します.
   *
   * @param params 埋込文字列
   */
  public static void begin(String... params) {
    STATISTICS_LOGGER.info(MessageUtils.getMessage(CoreMessageId.U0001I, params));
  }

  /**
   * 正常終了ログを出力します.
   *
   * @param params 埋込文字列
   */
  public static void complete(String... params) {
    STATISTICS_LOGGER.info(MessageUtils.getMessage(CoreMessageId.U0002I, params));
  }

  /**
   * 異常終了ログを出力します.
   *
   * @param params 埋込文字列
   */
  public static void error(String... params) {
    STATISTICS_LOGGER.error(MessageUtils.getMessage(CoreMessageId.U0003E, params));
  }

}
