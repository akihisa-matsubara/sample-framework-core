package dev.sample.framework.jsf.exception;

import dev.sample.framework.core.exception.ApplicationException;
import dev.sample.framework.core.exception.dto.ErrorMessage;
import java.util.List;

/**
 * 未定義ルール例外.
 */
public class UndefinedRuleException extends ApplicationException {

  /** serialVersionUID. */
  private static final long serialVersionUID = -3783009899660935057L;

  /**
   * デフォルトコンストラクター.
   *
   * @param cause Throwable
   * @param error エラーメッセージ
   */
  public UndefinedRuleException(Throwable cause, ErrorMessage error) {
    super(cause, error);
  }

  /**
   * デフォルトコンストラクター.
   *
   * @param cause Throwable
   * @param errorList エラーメッセージリスト
   */
  public UndefinedRuleException(Throwable cause, List<ErrorMessage> errorList) {
    super(cause, errorList);
  }

}
