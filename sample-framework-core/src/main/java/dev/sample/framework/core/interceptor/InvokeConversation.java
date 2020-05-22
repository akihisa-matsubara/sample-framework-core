package dev.sample.framework.core.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * Conversation実行.
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface InvokeConversation {

  /**
   * 制御種別.
   *
   * @return 制御種別
   */
  @Nonbinding
  Type type() default Type.DO_NOTHING;

  /**
   * 制御種別.
   */
  public enum Type {
    /** 処理なし. */
    DO_NOTHING,
    /** 処理前にConversationを開始. */
    START,
    /** 処理後にConversationを終了. */
    END,
    ;
  }
}
