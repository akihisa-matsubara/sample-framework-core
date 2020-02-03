package jp.co.sample.framework.test.dto;

import jp.co.sample.common.code.CodeVo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MulitpleTypeDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private boolean booleanValue;
  private byte byteValue;
  private char charValue;
  private short shortValue;
  private int intValue;
  private long longValue;
  private float floatValue;
  private double doubleValue;

  private Boolean booleanWrapperValue;
  private Byte byteWrapperValue;
  private Character charWrapperValue;
  private Short shortWrapperValue;
  private Integer intWrapperValue;
  private Long longWrapperValue;
  private Float floatWrapperValue;
  private Double doubleWrapperValue;

  private String stringValue;
  private Date dateValue;
  private LocalDate localDateValue;
  private LocalTime localTimeValue;
  private LocalDateTime localDateTimeValue;

  private String[] arrayValue;
  private List<String> listValue;
  private Map<String, String> mapValue;
  private Set<String> setValue;

  private Timestamp timestampValue;
  private BigDecimal bigDecimalValue;

  private CodeVo codeValue;
}
