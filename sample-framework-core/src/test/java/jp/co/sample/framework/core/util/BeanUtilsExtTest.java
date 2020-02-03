package jp.co.sample.framework.core.util;

import static jp.co.sample.framework.test.dto.assertj.MulitpleTypeDtoAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import jp.co.sample.common.code.CodeVo;
import jp.co.sample.common.code.GenderVo;
import jp.co.sample.framework.test.dto.MulitpleTypeDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BeanUtilsExtTest {

  @DisplayName("copyProperties(Class<T> type, final Object orig)のテスト")
  @Nested
  class CopyPropertiesNewInstance {
    @Test
    @DisplayName("正常系 - 初期値コピー時の値／インスタンスの比較")
    void testInitialValue() {
      // --- setup -----
      MulitpleTypeDto origDto = new MulitpleTypeDto();

      // --- execute ---
      MulitpleTypeDto result = BeanUtilsExt.copyProperties(MulitpleTypeDto.class, origDto);

      // --- verify ----
      assertAll("コピー結果検証",
          // 値検証
          () -> assertThat(result).as("コピーした全ての属性（値）が一致すること").isEqualTo(origDto),
          // インスタンス検証
          () -> assertThat(result).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(origDto));
    }

    @Test
    @DisplayName("正常系 - 初期値以外コピー時の値／インスタンスの比較")
    void testNotInitilaValue() throws Exception {
      // --- setup -----
      boolean booleanValue = true;
      byte byteValue = (byte) 0xFF;
      char charValue = 'a';
      short shortValue = 9;
      int intValue = 99999;
      long longValue = 9999999999L;
      float floatValue = 0.1234F;
      double doubleValue = 0.123456789D;

      Boolean booleanWrapperValue = Boolean.valueOf(booleanValue);
      Byte byteWrapperValue = Byte.valueOf(byteValue);
      Character charWrapperValue = Character.valueOf(charValue);
      Short shortWrapperValue = Short.valueOf(shortValue);
      Integer intWrapperValue = Integer.valueOf(intValue);
      Long longWrapperValue = Long.valueOf(longValue);
      Float floatWrapperValue = Float.valueOf(floatValue);
      Double doubleWrapperValue = Double.valueOf(doubleValue);

      String stringValue = "xyz";
      Date dateValue = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2000/01/23 12:34:56");
      LocalDate localDateValue = LocalDate.of(2001, 1, 23);
      LocalTime localTimeValue = LocalTime.of(12, 34, 56, 123456789);
      LocalDateTime localDateTimeValue = LocalDateTime.of(2001, 1, 23, 12, 34, 56, 123456789);

      String[] arrayValue = {"abc", "XYZ"};
      List<String> listValue = new ArrayList<>();
      listValue.add("element");
      Map<String, String> mapValue = new HashMap<>();
      mapValue.put("Key", "Value");
      Set<String> setValue = new HashSet<>();
      setValue.add("set");

      Timestamp timestampValue = Timestamp.valueOf(localDateTimeValue);
      BigDecimal bigDecimalValue = BigDecimal.ONE.divide(new BigDecimal(3), 6, RoundingMode.HALF_UP);

      CodeVo codeValue = GenderVo.FEMALE;
      MulitpleTypeDto origDto = MulitpleTypeDto.builder()
          .booleanValue(booleanValue)
          .byteValue(byteValue)
          .charValue(charValue)
          .shortValue(shortValue)
          .intValue(intValue)
          .longValue(longValue)
          .floatValue(floatValue)
          .doubleValue(doubleValue)
          .booleanWrapperValue(booleanWrapperValue)
          .byteWrapperValue(byteWrapperValue)
          .charWrapperValue(charWrapperValue)
          .shortWrapperValue(shortWrapperValue)
          .intWrapperValue(intWrapperValue)
          .longWrapperValue(longWrapperValue)
          .floatWrapperValue(floatWrapperValue)
          .doubleWrapperValue(doubleWrapperValue)
          .stringValue(stringValue)
          .dateValue(dateValue)
          .localDateValue(localDateValue)
          .localTimeValue(localTimeValue)
          .localDateTimeValue(localDateTimeValue)
          .arrayValue(arrayValue)
          .listValue(listValue)
          .mapValue(mapValue)
          .setValue(setValue)
          .timestampValue(timestampValue)
          .bigDecimalValue(bigDecimalValue)
          .codeValue(codeValue)
          .build();

      // --- execute ---
      MulitpleTypeDto result = BeanUtilsExt.copyProperties(MulitpleTypeDto.class, origDto);

      // --- verify ----
      assertAll("コピー結果検証",
          // 値検証
          () -> assertThat(result).as("コピーした全ての属性（値）が一致すること").isEqualTo(origDto),

          // インスタンス検証
          () -> assertThat(result).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(origDto),
          () -> assertThat(result.getBooleanWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(booleanWrapperValue),
          () -> assertThat(result.getByteWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(byteWrapperValue),
          () -> assertThat(result.getCharWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(charWrapperValue),
          () -> assertThat(result.getShortWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(shortWrapperValue),
          () -> assertThat(result.getIntWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(intWrapperValue),
          () -> assertThat(result.getLongWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(longWrapperValue),
          () -> assertThat(result.getFloatWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(floatWrapperValue),
          () -> assertThat(result.getDoubleWrapperValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(doubleWrapperValue),

          () -> assertThat(result.getStringValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(stringValue),
          () -> assertThat(result.getDateValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(dateValue),
          () -> assertThat(result.getLocalDateValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(localDateValue),
          () -> assertThat(result.getLocalTimeValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(localTimeValue),
          () -> assertThat(result.getLocalDateTimeValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(localDateTimeValue),

          () -> assertThat(result.getArrayValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(arrayValue),
          () -> assertThat(result.getListValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(listValue),
          () -> assertThat(result.getMapValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(mapValue),
          () -> assertThat(result.getSetValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(setValue),

          () -> assertThat(result.getTimestampValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(timestampValue),
          () -> assertThat(result.getBigDecimalValue()).as("コピー元・先のインスタンスが一致すること").isSameAs(bigDecimalValue));
    }
  }


  @DisplayName("deepCopy(final T orig)のテスト")
  @Nested
  class DeepCopy {
    @Test
    @DisplayName("正常系 - 初期値コピー時の値／インスタンスの比較")
    void testInitialValue() {
      // --- setup -----
      MulitpleTypeDto origDto = new MulitpleTypeDto();

      // --- execute ---
      MulitpleTypeDto result = BeanUtilsExt.deepCopy(origDto);

      // --- verify ----
      assertAll("コピー結果検証",
          // 値検証
          () -> assertThat(result).as("コピーした全ての属性（値）が一致すること").isEqualTo(origDto),
          // インスタンス検証
          () -> assertThat(result).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(origDto));
    }

    @Test
    @DisplayName("正常系 - 初期値以外コピー時の値／インスタンスの比較")
    void testNotInitilaValue() throws Exception {
      // --- setup -----
      boolean booleanValue = true;
      byte byteValue = (byte) 0xFF;
      char charValue = 'a';
      short shortValue = 9;
      int intValue = 99999;
      long longValue = 9999999999L;
      float floatValue = 0.1234F;
      double doubleValue = 0.123456789D;

      Boolean booleanWrapperValue = Boolean.valueOf(booleanValue);
      Byte byteWrapperValue = Byte.valueOf(byteValue);
      Character charWrapperValue = Character.valueOf(charValue);
      Short shortWrapperValue = Short.valueOf(shortValue);
      Integer intWrapperValue = Integer.valueOf(intValue);
      Long longWrapperValue = Long.valueOf(longValue);
      Float floatWrapperValue = Float.valueOf(floatValue);
      Double doubleWrapperValue = Double.valueOf(doubleValue);

      String stringValue = "xyz";
      Date dateValue = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2000/01/23 12:34:56");
      LocalDate localDateValue = LocalDate.of(2001, 1, 23);
      LocalTime localTimeValue = LocalTime.of(12, 34, 56, 123456789);
      LocalDateTime localDateTimeValue = LocalDateTime.of(2001, 1, 23, 12, 34, 56, 123456789);

      String[] arrayValue = {"abc", "XYZ"};
      List<String> listValue = new ArrayList<>();
      listValue.add("element");
      Map<String, String> mapValue = new HashMap<>();
      mapValue.put("Key", "Value");
      Set<String> setValue = new HashSet<>();
      setValue.add("set");

      Timestamp timestampValue = Timestamp.valueOf(localDateTimeValue);
      BigDecimal bigDecimalValue = BigDecimal.ONE.divide(new BigDecimal(3), 6, RoundingMode.HALF_UP);

      CodeVo codeValue = GenderVo.FEMALE;
      MulitpleTypeDto origDto = MulitpleTypeDto.builder()
          .booleanValue(booleanValue)
          .byteValue(byteValue)
          .charValue(charValue)
          .shortValue(shortValue)
          .intValue(intValue)
          .longValue(longValue)
          .floatValue(floatValue)
          .doubleValue(doubleValue)
          .booleanWrapperValue(booleanWrapperValue)
          .byteWrapperValue(byteWrapperValue)
          .charWrapperValue(charWrapperValue)
          .shortWrapperValue(shortWrapperValue)
          .intWrapperValue(intWrapperValue)
          .longWrapperValue(longWrapperValue)
          .floatWrapperValue(floatWrapperValue)
          .doubleWrapperValue(doubleWrapperValue)
          .stringValue(stringValue)
          .dateValue(dateValue)
          .localDateValue(localDateValue)
          .localTimeValue(localTimeValue)
          .localDateTimeValue(localDateTimeValue)
          .arrayValue(arrayValue)
          .listValue(listValue)
          .mapValue(mapValue)
          .setValue(setValue)
          .timestampValue(timestampValue)
          .bigDecimalValue(bigDecimalValue)
          .codeValue(codeValue)
          .build();

      // --- execute ---
      MulitpleTypeDto result = BeanUtilsExt.deepCopy(origDto);

      // --- verify ----
      assertAll("コピー結果検証",
          // 値検証
          () -> assertThat(result).as("コピーした全ての属性（値）が一致すること").isEqualTo(origDto),

          // インスタンス検証
          () -> assertThat(result).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(origDto),
          () -> assertThat(booleanWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getBooleanWrapperValue()),
          () -> assertThat(byteWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getByteWrapperValue()),
          () -> assertThat(charWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getCharWrapperValue()),
          () -> assertThat(shortWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getShortWrapperValue()),
          () -> assertThat(intWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getIntWrapperValue()),
          () -> assertThat(longWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getLongWrapperValue()),
          () -> assertThat(floatWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getFloatWrapperValue()),
          () -> assertThat(doubleWrapperValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getDoubleWrapperValue()),

          () -> assertThat(stringValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getStringValue()),
          () -> assertThat(dateValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getDateValue()),
          () -> assertThat(localDateValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getLocalDateValue()),
          () -> assertThat(localTimeValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getLocalTimeValue()),
          () -> assertThat(localDateTimeValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getLocalDateTimeValue()),

          () -> assertThat(arrayValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getArrayValue()),
          () -> assertThat(listValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getListValue()),
          () -> assertThat(mapValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getMapValue()),
          () -> assertThat(setValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getSetValue()),

          () -> assertThat(timestampValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getTimestampValue()),
          () -> assertThat(bigDecimalValue).as("コピー元・先のインスタンスが不一致となること").isNotSameAs(result.getBigDecimalValue()));
    }
  }
}
