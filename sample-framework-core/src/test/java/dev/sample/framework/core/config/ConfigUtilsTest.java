package dev.sample.framework.core.config;

import static org.assertj.core.api.Assertions.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import dev.sample.common.code.GenderVo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConfigUtilsTest {

  @DisplayName("init()のテスト")
  @Nested
  class Init {
    @DisplayName("正常系")
    @ParameterizedTest
    @CsvSource({
        "共通設定値から文字列を取得できていること,             application.conf,    test.common.ConfigUtilsTest.Init",
        "UT設定値から文字列を取得できていること,               application-ut.conf, test.utProfile.ConfigUtilsTest.Init",
        "同一キーの場合は環境依存の設定値を取得できていること, application-ut.conf, sameKey",
    })
    void test(String desc, String expected, String key) {
      // --- setup -----
      // --- execute ---
      String actual = ConfigUtils.getAsString(key);

      // --- verify ----
      assertThat(actual).as(desc).isEqualTo(expected);
    }
  }

  @DisplayName("getAsBoolean(String key)のテスト")
  @Nested
  class GetAsBoolean {
    @DisplayName("正常系")
    @ParameterizedTest
    @CsvSource({
        "booleanを取得できていること,                       true,  test.common.ConfigUtilsTest.GetAsBoolean",
        "キーが見つからない場合はfalseを取得できていること, false, hoge",
    })
    void test(String desc, boolean expected, String key) {
      // --- setup -----
      // --- execute ---
      boolean actual = ConfigUtils.getAsBoolean(key);

      // --- verify ----
      assertThat(actual).as(desc).isEqualTo(expected);
    }
  }

  @DisplayName("getAsInt(String key)のテスト")
  @Nested
  class GetAsInt {
    @DisplayName("正常系")
    @ParameterizedTest
    @CsvSource({
        "数値を取得できていること,                       99, test.common.ConfigUtilsTest.GetAsInt",
        "キーが見つからない場合は-1を取得できていること, -1, hoge",
    })
    void test(String desc, int expected, String key) {
      // --- setup -----
      // --- execute ---
      int actual = ConfigUtils.getAsInt(key);

      // --- verify ----
      assertThat(actual).as(desc).isEqualTo(expected);
    }
  }

  @DisplayName("getAsString(String key)のテスト")
  @Nested
  class GetAsString {
    @DisplayName("正常系")
    @ParameterizedTest
    @CsvSource({
        "文字列を取得できていること,                       hoge, test.common.ConfigUtilsTest.GetAsString",
        "キーが見つからない場合はnullを取得できていること, ,     hoge",
    })
    void test(String desc, String expected, String key) {
      // --- setup -----
      // --- execute ---
      String actual = ConfigUtils.getAsString(key);

      // --- verify ----
      assertThat(actual).as(desc).isEqualTo(expected);
    }
  }

  @DisplayName("getAsStringList(String key)のテスト")
  @Nested
  class GetAsStringList {
    @DisplayName("正常系 - 該当キーあり")
    @ParameterizedTest
    @CsvSource({
        "文字列のリストを取得できていること, hoge, fuga, piyo, test.common.ConfigUtilsTest.GetAsStringList",
    })
    void test(String desc, String expectedValue1, String expectedValue2, String expectedValue3, String key) {
      // --- setup -----
      // --- execute ---
      List<String> actual = ConfigUtils.getAsStringList(key);

      // --- verify ----
      assertThat(actual).as(desc).containsExactly(expectedValue1, expectedValue2, expectedValue3);
    }

    @DisplayName("正常系 - 該当キーなし")
    @ParameterizedTest
    @CsvSource({
        "キーが見つからない場合はnullを取得できていること, hoge",
    })
    void testNotFound(String desc, String key) {
      // --- setup -----
      // --- execute ---
      List<String> actual = ConfigUtils.getAsStringList(key);

      // --- verify ----
      assertThat(actual).as(desc).isNull();
    }
  }

  @DisplayName("getAsBean(String key, Class<T> clazz)のテスト")
  @Nested
  class GetAsBean {
    @DisplayName("正常系 - 該当キーあり")
    @ParameterizedTest
    @CsvSource({
        "特定のBeanを取得できていること, test.common.ConfigUtilsTest.GetAsBean",
    })
    void test(String desc, String key) {
      // --- setup -----
      // --- execute ---
      MultipleTypeConfig actual = ConfigUtils.getAsBean(key, MultipleTypeConfig.class);

      // --- verify ----
      String[] arrayValue = {"abc", "XYZ"};
      MultipleTypeConfig expected = MultipleTypeConfig.builder()
          .booleanValue(true)
          .intValue(99999)
          .longValue(9999999999L)
          .doubleValue(0.123456789D)
          .booleanWrapperValue(true)
          .intWrapperValue(99999)
          .longWrapperValue(9999999999L)
          .doubleWrapperValue(0.123456789D)
          .stringValue("xyz")
          .durationValue(Duration.ofSeconds(30))
          .listValue(Arrays.asList(arrayValue))
          .codeValue(GenderVo.FEMALE)
          .build();

      assertThat(actual).as(desc).isEqualTo(expected);
    }

    @DisplayName("正常系 - 該当キーなし")
    @ParameterizedTest
    @CsvSource({
        "キーが見つからない場合はnullを取得できていること, hoge",
    })
    void testNotFound(String desc, String key) {
      // --- setup -----
      // --- execute ---
      MultipleTypeConfig actual = ConfigUtils.getAsBean(key, MultipleTypeConfig.class);

      // --- verify ----
      assertThat(actual).as(desc).isNull();
    }
  }

  @DisplayName("getAsBeanList(String key, Class<T> clazz)のテスト")
  @Nested
  class GetAsBeanList {
    @DisplayName("正常系 - 該当キーあり")
    @ParameterizedTest
    @CsvSource({
        "特定のBeanを取得できていること, test.common.ConfigUtilsTest.GetAsBeanList",
    })
    void test(String desc, String key) {
      // --- setup -----
      // --- execute ---
      List<MultipleTypeConfig> actual = ConfigUtils.getAsBeanList(key, MultipleTypeConfig.class);

      // --- verify ----
      String[] arrayValue = {"abc", "XYZ"};
      MultipleTypeConfig expected1 = MultipleTypeConfig.builder()
          .booleanValue(true)
          .intValue(99999)
          .longValue(9999999999L)
          .doubleValue(0.123456789D)
          .booleanWrapperValue(true)
          .intWrapperValue(99999)
          .longWrapperValue(9999999999L)
          .doubleWrapperValue(0.123456789D)
          .stringValue("xyz")
          .durationValue(Duration.ofSeconds(30))
          .listValue(Arrays.asList(arrayValue))
          .codeValue(GenderVo.FEMALE)
          .build();
      MultipleTypeConfig expected2 = MultipleTypeConfig.builder()
          .booleanValue(false)
          .intValue(11111)
          .longValue(1111111111L)
          .doubleValue(0.111111111D)
          .booleanWrapperValue(false)
          .intWrapperValue(11111)
          .longWrapperValue(1111111111L)
          .doubleWrapperValue(0.111111111D)
          .stringValue("abc")
          .durationValue(Duration.ofSeconds(5))
          .listValue(Arrays.asList(arrayValue))
          .codeValue(GenderVo.MALE)
          .build();

      assertThat(actual).as(desc).containsExactly(expected1, expected2);
    }

    @DisplayName("正常系 - 該当キーなし")
    @ParameterizedTest
    @CsvSource({
        "キーが見つからない場合はnullを取得できていること, hoge",
    })
    void testNotFound(String desc, String key) {
      // --- setup -----
      // --- execute ---
      List<MultipleTypeConfig> actual = ConfigUtils.getAsBeanList(key, MultipleTypeConfig.class);

      // --- verify ----
      assertThat(actual).as(desc).isNull();
    }
  }
}
