package dev.sample.framework.core.conversation;

import dev.sample.framework.core.message.CoreMessageId;
import dev.sample.framework.core.util.MessageUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ConversationScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * Conversation共有データ.
 */
@ConversationScoped
@Slf4j
public class ConversationSharedDto implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = -2676517725635603871L;

  /** Data Model Map. */
  private Map<String, Serializable> dataModelMap;

  /**
   * 初期化.
   */
  @PostConstruct
  public void init() {
    dataModelMap = new ConcurrentHashMap<>();
  }

  /**
   * 終了処理.
   */
  @PreDestroy
  public void destroy() {
    log.debug(MessageUtils.getMessage(CoreMessageId.F0012D, dataModelMap.toString()));
  }

  /**
   * 指定したModelをConversation共有データから取得します.
   *
   * @param <T> Model
   * @param model Model
   * @return 指定したModel
   */
  @SuppressWarnings("unchecked")
  public <T extends Serializable> T get(Class<T> model) {
    return (T) dataModelMap.get(model.getSimpleName());
  }

  /**
   * ModelをConversation間共有データに設定します.
   *
   * @param <T> Model
   * @param model Model
   */
  public <T extends Serializable> void put(T model) {
    dataModelMap.put(model.getClass().getSimpleName(), model);
  }

  /**
   * Model Mapをクリアします.
   */
  public void clear() {
    dataModelMap.clear();
  }

  /**
   * Conversation共有データに指定のModelが存在するか確認します.
   *
   * @param <T> Model
   * @param model Model
   * @return 既にConversation共有データに存在する場合はtrue
   */
  public <T extends Serializable> boolean contains(T model) {
    return dataModelMap.containsKey(model.getClass().getSimpleName());
  }

}
