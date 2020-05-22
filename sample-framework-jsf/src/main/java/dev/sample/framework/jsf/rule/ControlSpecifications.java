package dev.sample.framework.jsf.rule;

import java.util.List;
import lombok.Data;

/**
 * 制御仕様.
 */
@Data
public class ControlSpecifications {

  /** 制御仕様番号. */
  private List<String> controlSpecNos;

  /** 制御内容. */
  private List<String> controlContents;

}
