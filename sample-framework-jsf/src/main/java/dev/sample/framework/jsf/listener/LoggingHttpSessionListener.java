package dev.sample.framework.jsf.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

/**
 * セッション ログ出力リスナー.
 * ・ライフサイクル（作成／破棄・タイムアウト）
 * ・セッション属性の変更（追加、削除、置換）
 * ・セッションの有効化／無効化
 */
@WebListener
@Slf4j
public class LoggingHttpSessionListener
    implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {

  /**
   * セッション作成時処理.
   *
   * @param se {@code HttpSessionEvent}
   */
  @Override
  public void sessionCreated(HttpSessionEvent se) {
    log.debug("A session has been created. session id={}", se.getSession().getId());
  }

  /**
   * セッション削除時処理.
   *
   * @param se {@code HttpSessionEvent}
   */
  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    log.debug("A session has been destroyed. [session id={}]", se.getSession().getId());
  }

  /**
   * セッション属性 追加時処理.
   *
   * @param event {@code HttpSessionBindingEvent}
   */
  @Override
  public void attributeAdded(HttpSessionBindingEvent event) {
    log.debug("Session attributes has been added. [attribute:{}, value:{}]", event.getName(), event.getValue());
  }

  /**
   * セッション属性 削除時処理.
   *
   * @param event {@code HttpSessionBindingEvent}
   */
  @Override
  public void attributeRemoved(HttpSessionBindingEvent event) {
    log.debug("Session attributes has been removed. [attribute:{}, value:{}]", event.getName(), event.getValue());
  }

  /**
   * セッション属性 置換時処理.
   *
   * @param event {@code HttpSessionBindingEvent}
   */
  @Override
  public void attributeReplaced(HttpSessionBindingEvent event) {
    log.debug("Session attributes has been replaced. [attribute:{}, value:{}]", event.getName(), event.getValue());
  }

  /**
   * セッションの無効化時処理.
   *
   * @param se {@code HttpSessionEvent}
   */
  @Override
  public void sessionWillPassivate(HttpSessionEvent se) {
    log.debug("The session will be passive.");
  }

  /**
   * セッションの有効化時処理.
   *
   * @param se {@code HttpSessionEvent}
   */
  @Override
  public void sessionDidActivate(HttpSessionEvent se) {
    log.debug("The session has been activated.");
  }

  /**
   * セッションへのオブジェクト バインド時処理.
   *
   * @param event {@code HttpSessionBindingEvent}
   */
  @Override
  public void valueBound(HttpSessionBindingEvent event) {
    log.debug("The object has been bound to the session. [name:{}, value:{}]", event.getName(), event.getValue());
  }

  /**
   * セッションへのオブジェクト アンバインド時処理.
   *
   * @param event {@code HttpSessionBindingEvent}
   */
  @Override
  public void valueUnbound(HttpSessionBindingEvent event) {
    log.debug("The object has been unbound to the session. [name:{}, value:{}]", event.getName(), event.getValue());
  }

}
