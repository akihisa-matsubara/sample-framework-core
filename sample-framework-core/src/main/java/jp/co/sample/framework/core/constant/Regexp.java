package jp.co.sample.framework.core.constant;

/**
 * 正規表現定数.
 */
public final class Regexp {

  /** 数字. */
  public static final String NUM = "^[0-9]+$";
  /** 英数字. */
  public static final String NUM_ALPHA = "^[0-9a-zA-Z]+$";
  /** 英大文字. */
  public static final String UPPER_ALPHA = "^[A-Z]+$";
  /** 英小文字. */
  public static final String LOWER_ALPHA = "^[a-z]+$";
  /** PW許容文字（英数字一部記号）. */
  public static final String PW = "^[0-9a-zA-Z./_-]+$";

  /**
   * デフォルトコンストラクター.
   */
  private Regexp() {
    // do nothing
  }
}
