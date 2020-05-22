package dev.sample.framework.core.exception.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import dev.sample.framework.core.message.MessageId;

/**
 * エラーメッセージ.
 */
@Getter
@Setter
public class ErrorMessage implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = -5529239137876367074L;

  /** メッセージID. */
  private MessageId messageId;

  /** パラメーター. */
  private String[] params;

  /**
   * デフォルトコンストラクター.
   *
   * @param messageId メッセージID
   * @param params パラメーター
   */
  public ErrorMessage(MessageId messageId, String... params) {
    this.messageId = messageId;
    this.params = params;
  }
}
