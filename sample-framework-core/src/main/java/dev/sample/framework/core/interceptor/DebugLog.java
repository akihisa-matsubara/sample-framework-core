package dev.sample.framework.core.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * デバッグログ.
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DebugLog {

  /**
   * ログ出力タイミング.
   *
   * @return ログ出力タイミング
   */
  @Nonbinding
  OutputTiming outputTiming() default OutputTiming.BOTH;

  /**
   * 処理名.
   *
   * @return 処理名
   */
  @Nonbinding
  String processName() default "";

  /**
   * View名.
   *
   * @return View名
   */
  @Nonbinding
  String viewName() default "";

  /**
   * DTOクラス.
   *
   * @return DTOクラス
   */
  @Nonbinding
  Class<?> dtoClass() default String.class;

}
