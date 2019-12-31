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

@Slf4j
public class ExceptionHandlerWrpperImpl extends ExceptionHandlerWrapper {

  /** Error Logger. */
  private static final Logger ERROR_LOGGER = LoggerFactory.getLogger(LoggerVo.ERROR_LOGGER.getCode());

  private static final String REDIRECT = "?faces-redirect=true";
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
   * {@inheritDoc}
   */
  @Override
  public void handle() {
    FacesContext fc = FacesContext.getCurrentInstance();
    Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator();
    while (it.hasNext()) {
      ExceptionQueuedEventContext ec = (ExceptionQueuedEventContext) it.next().getSource();
      Throwable th = ec.getException();
      ERROR_LOGGER.error(ExceptionUtils.getStackTrace(th));
      log.error(th.getClass().getSimpleName() + ":" + th.getMessage());

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
