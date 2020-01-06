package jp.co.sample.framework.core.interceptor;

import jp.co.sample.framework.core.message.MessageId;
import jp.co.sample.framework.core.util.CdiUtils;
import jp.co.sample.framework.core.util.MessageUtils;
import java.io.Serializable;
import javax.annotation.Priority;
import javax.enterprise.context.Conversation;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Conversation実行インターセプター.
 */
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
@Interceptor
@InvokeConversation
@Slf4j
public class InvokeConversationInterceptor implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = -5859736536223450434L;

  /**
   * Conversationの開始／終了を実行します.
   *
   * @param context InvocationContext
   * @return Object
   * @throws Exception 例外
   */
  @AroundInvoke
  public Object invoke(InvocationContext context) throws Exception {
    InvokeConversation annotation = context.getMethod().getAnnotation(InvokeConversation.class);

    if (InvokeConversation.Type.START == annotation.type()) {
      startConversation();
    }

    try {
      return context.proceed();

    } finally {
      if (InvokeConversation.Type.END == annotation.type()) {
        endConversation();

      }
    }
  }

  /**
   * Conversationを開始します.
   */
  private void startConversation() {
    Conversation conv = CdiUtils.getBean(Conversation.class);
    if (conv.isTransient()) {
      conv.begin();
      log.info(MessageUtils.getMessage(MessageId.F0010I), conv.getId());
    }
  }

  /**
   * Conversationを終了します.
   */
  private void endConversation() {
    Conversation conv = CdiUtils.getBean(Conversation.class);
    if (!conv.isTransient()) {
      conv.end();
      log.info(MessageUtils.getMessage(MessageId.F0011I), conv.getId());
    }
  }

}
