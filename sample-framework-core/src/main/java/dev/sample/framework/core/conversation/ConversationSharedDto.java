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

  /** Entity Map. */
  private Map<String, Serializable> entityMap;

  /**
   * 初期化.
   */
  @PostConstruct
  public void init() {
    entityMap = new ConcurrentHashMap<>();
  }

  /**
   * 終了処理.
   */
  @PreDestroy
  public void destroy() {
    log.debug(MessageUtils.getMessage(CoreMessageId.F0012D, entityMap.toString()));
  }

  /**
   * 指定したEntityをConversation共有データから取得します.
   *
   * @param <T> Entity
   * @param entity Entity
   * @return 指定したEntity
   */
  @SuppressWarnings("unchecked")
  public <T extends Serializable> T get(Class<T> entity) {
    return (T) entityMap.get(entity.getSimpleName());
  }

  /**
   * EntityをConversation間共有データに設定します.
   *
   * @param <T> Entity
   * @param entity Entity
   */
  public <T extends Serializable> void put(T entity) {
    entityMap.put(entity.getClass().getSimpleName(), entity);
  }

  /**
   * Entity Mapをクリアします.
   */
  public void clear() {
    entityMap.clear();
  }

  /**
   * Conversation共有データに指定のEntityが存在するか確認します.
   *
   * @param <T> Entity
   * @param entity Entity
   * @return 既にConversation共有データに存在する場合はtrue
   */
  public <T extends Serializable> boolean contains(T entity) {
    return entityMap.containsKey(entity.getClass().getSimpleName());
  }

}
