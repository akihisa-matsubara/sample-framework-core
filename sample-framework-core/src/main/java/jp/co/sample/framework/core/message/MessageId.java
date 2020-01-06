package jp.co.sample.framework.core.message;

/**
 * メッセージID.
 */
public enum MessageId implements MessageIdIf {

  /** 未定義. */
  UNDEFINED,
  // F～ FRAMEWORK
  /** Beanの変換({0} -> {1})に失敗しました。. */
  F0001E,
  /** 設定ファイル[{0}]を読み込みました。. */
  F0002I,
  /** 設定ファイル[{0}]の読み込みに失敗しました。読み込みをSkipします。. */
  F0003W,
  /** active profile is [{0}]. */
  F0004I,
  /** システム日付（みなし日付）を[{0}]に設定しました。. */
  F0005I,
  /** メッセージプロパティ[language:{0}]を読み込みました。. */
  F0006I,
  /** キー[{0}]が設定ファイルに存在しません。. */
  F0007E,
  /** 対象のBean[{0}]はコンテナ管理されていません。. */
  F0008E,
  /** 対象のBean[{0}]が複数見つかりました。. */
  F0009E,
  /** Conversationを開始しました。coonversationId={0}. */
  F0010I,
  /** Conversationを終了しました。coonversationId={0}. */
  F0011I,
  /** Conversation共有データを破棄します。[{0}]. */
  F0012D,
  // C～ COMMON
  /** {0} start {1} {2}. */
  C0001D,
  /** {0} normal-end {1} {2}. */
  C0002D,
  /** {0} abnormal-end {1} {2}. */
  C0003E,
  // B～ BUSINESS LOGIC
  // P～ PRESENTATION
  // U～ 利用統計ログ
  /** {0} start {1}. */
  U0001I,
  /** {0} normal-end {1}. */
  U0002I,
  /** {0} abnormal-end {1}. */
  U0003E,
  ;

}
