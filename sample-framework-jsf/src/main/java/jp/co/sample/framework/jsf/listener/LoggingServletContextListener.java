package jp.co.sample.framework.jsf.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

/**
 * サーブレット・コンテキスト ログ出力リスナー.
 * ・ライフサイクル（起動／停止）
 * ・サーブレット・コンテキスト属性の変更（追加、削除、置換）
 */
@WebListener
@Slf4j
public class LoggingServletContextListener implements ServletContextListener, ServletContextAttributeListener {

  /**
   * サーブレット起動時処理.
   *
   * @param sce {@code ServletContextEvent}
   */
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.info("{} has been initialized.", sce.getServletContext().getServletContextName());
  }

  /**
   * サーブレット停止時処理.
   *
   * @param sce {@code ServletContextEvent}
   */
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    log.info("{} has been destroyed.", sce.getServletContext().getServletContextName());
  }

  /**
   * サーブレット・コンテキスト属性 追加時処理.
   *
   * @param event {@code ServletContextAttributeEvent}
   */
  @Override
  public void attributeAdded(ServletContextAttributeEvent event) {
    log.trace("Servlet context attribute of {} has been added [attribute:{}, value:{}]", event.getServletContext().getServletContextName(), event.getName(),
        event.getValue());
  }

  /**
   * サーブレット・コンテキスト属性 削除時処理.
   *
   * @param event {@code ServletContextAttributeEvent}
   */
  @Override
  public void attributeRemoved(ServletContextAttributeEvent event) {
    log.trace("Servlet context attribute of {} has been removed [attribute:{}, value:{}]", event.getServletContext().getServletContextName(), event.getName(),
        event.getValue());
  }

  /**
   * サーブレット・コンテキスト属性 置換時処理.
   *
   * @param event {@code ServletContextAttributeEvent}
   */
  @Override
  public void attributeReplaced(ServletContextAttributeEvent event) {
    log.trace("Servlet context attribute of {} has been replaced [attribute:{}, value:{}]", event.getServletContext().getServletContextName(), event.getName(),
        event.getValue());
  }

}
