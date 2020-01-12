package jp.co.sample.framework.core.message;

import java.io.Serializable;

/**
 * メッセージID.
 */
public interface MessageId extends Serializable {
  /**
   * メッセージIDを取得します.
   *
   * @return メッセージID
   */
  String name();
}
