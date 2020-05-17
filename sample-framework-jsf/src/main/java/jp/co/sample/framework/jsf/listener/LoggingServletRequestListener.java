package jp.co.sample.framework.jsf.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

/**
 * リクエスト ログ出力リスナー.
 * ・ライフサイクル（開始／終了）
 * ・リクエスト属性の変更（追加、削除、置換）
 */
@WebListener
@Slf4j
public class LoggingServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener {

  /**
   * リクエスト開始時処理.
   *
   * @param sre {@code ServletRequestEvent}
   */
  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    log.trace("Request received.");
  }

  /**
   * リクエスト終了時処理.
   *
   * @param sre {@code ServletRequestEvent}
   */
  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    log.trace("Respond to the request.");
  }

  /**
   * リクエスト属性 追加時処理.
   *
   * @param srae {@code ServletRequestAttributeEvent}
   */
  @Override
  public void attributeAdded(ServletRequestAttributeEvent srae) {
    log.trace("Request attribute has been added [attribute:{}, value:{}]", srae.getName(), srae.getValue());
  }

  /**
   * リクエスト属性 削除時処理.
   *
   * @param srae {@code ServletRequestAttributeEvent}
   */
  @Override
  public void attributeRemoved(ServletRequestAttributeEvent srae) {
    log.trace("Request attribute has been removed [attribute:{}, value:{}]", srae.getName(), srae.getValue());
  }

  /**
   * リクエスト属性 置換時処理.
   *
   * @param srae {@code ServletRequestAttributeEvent}
   */
  @Override
  public void attributeReplaced(ServletRequestAttributeEvent srae) {
    log.trace("Request attribute has been replaced [attribute:{}, value:{}]", srae.getName(), srae.getValue());
  }

}
