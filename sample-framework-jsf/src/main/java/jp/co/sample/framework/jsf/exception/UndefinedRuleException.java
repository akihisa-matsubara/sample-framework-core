package jp.co.sample.framework.jsf.exception;

import jp.co.sample.framework.core.exception.ApplicationException;
import jp.co.sample.framework.core.exception.dto.ErrorMessage;
import java.util.List;
import lombok.Getter;

/**
 * 未定義ルール例外.
 */
@Getter
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
