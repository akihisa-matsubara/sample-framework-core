package jp.co.sample.framework.jsf.rule;

import jp.co.sample.framework.core.exception.dto.ErrorMessage;
import jp.co.sample.framework.core.message.CoreMessageId;
import jp.co.sample.framework.jsf.exception.UndefinedRuleException;

/**
 * ルール・条件.
 */
public interface RuleConditions {

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo001() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.001"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo002() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.002"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo003() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.003"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo004() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.004"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo005() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.005"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo006() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.006"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo007() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.007"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo008() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.008"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo009() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.009"));
  }

  /**
   * 条件にマッチした制御仕様番号を返します.
   *
   * @return 制御仕様番号
   */
  default String matchesNo010() {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, "No.010"));
  }

  /**
   * 指定した制御仕様を実施し、項目に対する制御内容を返します.
   *
   * @param itemId 項目ID
   * @param controlSpecNo 制御仕様番号
   * @return 制御内容
   */
  default ControlContent updateValue(String itemId, String controlSpecNo) {
    throw new UndefinedRuleException(null, new ErrorMessage(CoreMessageId.F0016E, controlSpecNo));
  }

}
