package dev.sample.framework.jsf.rule;

import dev.sample.framework.core.exception.dto.ErrorMessage;
import dev.sample.framework.core.message.CoreMessageId;
import dev.sample.framework.jsf.exception.UndefinedRuleException;

/**
 * 制御内容.
 */
public enum ControlContent {

  /** 活性表示. */
  ENABLED,
  /** 非活性表示. */
  DISABLED,
  /** 非表示. */
  HIDDEN,
  /** 設定+制御. */
  SET,
  ;

  /**
   * 指定の値を制御内容にデコードします.
   *
   * @param value 値
   * @return 制御内容
   */
  public static ControlContent decode(String value) {
    switch (value) {
      case "ENABLED":
        return ENABLED;
      case "DISABLED":
        return DISABLED;
      case "HIDDEN":
        return HIDDEN;
      case "SET":
        return SET;
      default:
        throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F1002E, value));
    }
  }
}
