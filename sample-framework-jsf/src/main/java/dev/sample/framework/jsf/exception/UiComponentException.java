package dev.sample.framework.jsf.exception;

import dev.sample.framework.core.exception.SystemException;
import dev.sample.framework.core.exception.dto.ErrorMessage;

/**
 * UI部品例外.
 */
public class UiComponentException extends SystemException {

  /** serialVersionUID. */
  private static final long serialVersionUID = -3783009899660935057L;

  /**
   * デフォルトコンストラクター.
   *
   * @param error エラーDto
   */
  public UiComponentException(ErrorMessage error) {
    super(error);
  }

  /**
   * デフォルトコンストラクター.
   *
   * @param cause 例外
   */
  public UiComponentException(Throwable cause) {
    this(cause, null);
  }

  /**
   * デフォルトコンストラクター.
   *
   * @param cause 例外
   * @param error エラーDto
   */
  public UiComponentException(Throwable cause, ErrorMessage error) {
    super(cause, error);
  }

}
