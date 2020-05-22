package dev.sample.framework.core.interceptor;

import java.io.Serializable;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;
import dev.sample.framework.core.message.CoreMessageId;
import dev.sample.framework.core.util.CdiUtils;
import dev.sample.framework.core.util.MessageUtils;

/**
 * デバッグ ロギングインターセプター.
 */
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
@Interceptor
@DebugLog
@Slf4j
public class DebugLoggingInterceptor implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = -5859736536223450434L;

  /**
   * デバッグログを出力します.
   *
   * @param context InvocationContext
   * @return Object
   * @throws Exception 例外
   */
  @AroundInvoke
  public Object log(InvocationContext context) throws Exception {
    DebugLog annotation = context.getMethod().getAnnotation(DebugLog.class);
    Class<?> dtoClass = annotation.dtoClass();
    Object dto = "";
    if (dtoClass != String.class) {
      dto = CdiUtils.getBean(dtoClass);
    }

    if (OutputTiming.BEGIN == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
      log.debug(MessageUtils.getMessage(CoreMessageId.F0013D, annotation.processName(), annotation.viewName(), dto.toString()));
    }

    try {
      Object obj = context.proceed();

      if (OutputTiming.COMPLETE == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
        log.debug(MessageUtils.getMessage(CoreMessageId.F0014D, annotation.processName(), annotation.viewName(), dto.toString()));
      }
      return obj;

    } catch (Exception e) {
      if (OutputTiming.COMPLETE == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
        log.error(MessageUtils.getMessage(CoreMessageId.F0015E, annotation.processName(), annotation.viewName(), dto.toString()));
      }
      throw e;

    }
  }

}
