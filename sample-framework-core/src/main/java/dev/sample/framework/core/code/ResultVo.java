package dev.sample.framework.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.sample.common.code.CodeVo;

/**
 * 結果VO.
 */
@AllArgsConstructor
@Getter
public enum ResultVo implements CodeVo {

  /** Success. */
  SUCCESS("0", "Success"),
  /** Failure. */
  FAILURE("1", "Failure"),
  ;

  /** コード. */
  private String code;

  /** デコード. */
  private String decode;

}
