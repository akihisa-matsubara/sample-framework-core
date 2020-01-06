package jp.co.sample.framework.core.interceptor;

import jp.co.sample.framework.core.message.MessageId;
import jp.co.sample.framework.core.util.CdiUtils;
import jp.co.sample.framework.core.util.MessageUtils;
import java.io.Serializable;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

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
      log.debug(MessageUtils.getMessage(MessageId.C0001D, annotation.processName(), annotation.viewName(), dto.toString()));
    }

    try {
      Object obj = context.proceed();

      if (OutputTiming.COMPLETE == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
        log.debug(MessageUtils.getMessage(MessageId.C0002D, annotation.processName(), annotation.viewName(), dto.toString()));
      }
      return obj;

    } catch (Exception e) {
      if (OutputTiming.COMPLETE == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
        log.error(MessageUtils.getMessage(MessageId.C0003E, annotation.processName(), annotation.viewName(), dto.toString()));
      }
      throw e;

    }
  }

}
