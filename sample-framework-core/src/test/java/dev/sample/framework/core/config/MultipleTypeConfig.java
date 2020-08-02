package dev.sample.framework.core.config;

import java.time.Duration;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import dev.sample.common.code.GenderVo;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MultipleTypeConfig implements Configurable {
  private static final long serialVersionUID = 1L;

  private boolean booleanValue;
  private int intValue;
  private long longValue;
  private double doubleValue;

  private Boolean booleanWrapperValue;
  private Integer intWrapperValue;
  private Long longWrapperValue;
  private Double doubleWrapperValue;

  private String stringValue;
  private Duration durationValue;
  private List<String> listValue;
  private GenderVo codeValue;
}
