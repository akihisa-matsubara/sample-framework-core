package dev.sample.framework.core.interceptor;

import dev.sample.framework.core.logger.UsageStatisticsLogger;
import java.io.Serializable;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * 利用統計 ロギングインターセプター.
 */
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
@Interceptor
@UsageStatistics
public class UsageStatisticsLoggingInterceptor implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = -7985776564208590378L;

  /**
   * 利用統計ログを出力します.
   *
   * @param context InvocationContext
   * @return Object
   * @throws Exception 例外
   */
  @AroundInvoke
  public Object log(InvocationContext context) throws Exception {
    UsageStatistics annotation = context.getMethod().getAnnotation(UsageStatistics.class);

    if (OutputTiming.BEGIN == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
      UsageStatisticsLogger.begin(annotation.processName(), annotation.param());
    }

    try {
      Object obj = context.proceed();

      if (OutputTiming.COMPLETE == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
        UsageStatisticsLogger.complete(annotation.processName(), annotation.param());
      }
      return obj;

    } catch (Exception e) {
      if (OutputTiming.COMPLETE == annotation.outputTiming() || OutputTiming.BOTH == annotation.outputTiming()) {
        UsageStatisticsLogger.error(annotation.processName(), annotation.param());
      }
      throw e;

    }
  }

}
