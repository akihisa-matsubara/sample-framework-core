package jp.co.sample.framework.core.constant;

/**
 * 正規表現定数.
 */
public final class Regexp {

  /** 半角数字. */
  public static final String HALF_NUM = "^[0-9]+$";
  /** 半角英数字. */
  public static final String HALF_NUM_ALPHA = "^[0-9a-zA-Z]+$";
  /** 半角英大文字. */
  public static final String HALF_UPPER_ALPHA = "^[A-Z]+$";
  /** 半角英小文字. */
  public static final String HALF_LOWER_ALPHA = "^[a-z]+$";
  /** 半角記号. */
  public static final String HALF_SYMBOL = "^[ -/:-@\\[-\\`\\{-\\~]+$";
  /** 半角カタカナ. */
  public static final String HALF_KANA = "^[｡-ﾟ+]+$";
  /** 全角カタカナ. */
  public static final String FULL_KANA = "^[ァ-ヶー]+$";
  /** 全角文字. */
  public static final String FULL = "^[^ -~｡-ﾟ]+$";

  /** PW許容文字（英数字一部記号）. */
  public static final String PW = "^[0-9a-zA-Z./_-]+$";
  /** Email許容文字. */
  public static final String EMAIL = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";

  /**
   * デフォルトコンストラクター.
   */
  private Regexp() {
    // do nothing
  }
}
