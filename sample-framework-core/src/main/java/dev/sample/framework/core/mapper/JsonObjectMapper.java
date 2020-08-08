package dev.sample.framework.core.mapper;

import dev.sample.common.util.CodeUtils;
import dev.sample.common.util.DateFormat.DateFormatVo;
import dev.sample.framework.core.config.ConfigUtils;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.experimental.UtilityClass;

/**
 * JSON Object Mapper.
 */
@UtilityClass
public class JsonObjectMapper {

  /** キー情報：日付フォーマット. */
  private static final String KEY_DATE_FORMAT = "framework.formatter.date";
  /** キー情報：日時フォーマット. */
  private static final String KEY_DATE_TIME_FORMAT = "framework.formatter.dateTime";

  /**
   * ObjectMapperを作成します.
   *
   * @return mapper ObjectMapper
   */
  public static ObjectMapper create() {
    ObjectMapper mapper = new ObjectMapper();
    DateFormatVo dateFormat = CodeUtils.decode(ConfigUtils.getAsString(KEY_DATE_FORMAT), DateFormatVo.class);
    mapper.setDateFormat(new SimpleDateFormat(dateFormat.getCode()));

    // DeserializationFeature
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);

    // SerializationFeature
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    mapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);

    // for Java 8 new API
    mapper.registerModule(new Jdk8Module());
    JavaTimeModule jtm = new JavaTimeModule();
    DateFormatVo dateTimeFormat = CodeUtils.decode(ConfigUtils.getAsString(KEY_DATE_TIME_FORMAT), DateFormatVo.class);
    jtm.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat.getApiCode())));
    jtm.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat.getApiCode())));
    jtm.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat.getApiCode())));
    jtm.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat.getApiCode())));
    mapper.registerModule(jtm);

    return mapper;
  }

}
