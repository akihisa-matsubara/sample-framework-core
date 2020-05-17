package jp.co.sample.framework.jsf.handler;

import jp.co.sample.framework.core.code.LoggerVo;
import java.util.Iterator;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ProtectedViewException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExceptionHandlerWrapper実装.
 */
@Slf4j
public class ExceptionHandlerWrpperImpl extends ExceptionHandlerWrapper {

  /** Error Logger. */
  private static final Logger ERROR_LOGGER = LoggerFactory.getLogger(LoggerVo.ERROR_LOGGER.getCode());
  /** Redirectパラメーター. */
  private static final String REDIRECT = "?faces-redirect=true";

  /** ExceptionHandler. */
  private final ExceptionHandler wrapped;

  /**
   * コンストラクタ.
   *
   * @param wrapped wrapped
   */
  public ExceptionHandlerWrpperImpl(ExceptionHandler wrapped) {
    this.wrapped = wrapped;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExceptionHandler getWrapped() {
    return wrapped;
  }

  /**
   * <P>
   * 例外制御.
   * </P>
   * <ul>
   * <li>発生した例外をエラーログに出力します.</li>
   * <li>ProtectedViewExceptionの場合、不正操作用画面へ遷移します.</li>
   * </ul>
   */
  @Override
  public void handle() {
    FacesContext fc = FacesContext.getCurrentInstance();
    Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator();
    while (it.hasNext()) {
      ExceptionQueuedEventContext ec = (ExceptionQueuedEventContext) it.next().getSource();
      Throwable th = ec.getException();
      ERROR_LOGGER.error(ExceptionUtils.getStackTrace(th));
      log.error("{}:{}", th.getClass().getSimpleName(), th.getMessage());

      if (th instanceof ProtectedViewException) {
        try {
          NavigationHandler navHandler = fc.getApplication().getNavigationHandler();
          navHandler.handleNavigation(fc, null, "/pages/error/illegalOperation.xhtml" + REDIRECT);
          fc.renderResponse();

        } finally {
          it.remove();

        }
      }
    }

    getWrapped().handle();

  }

}
