package dev.sample.framework.core.data.converter;

import dev.sample.common.code.GenderVo;
import dev.sample.common.util.CodeUtils;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 相互型変換コンバーター(Entity: GenderVo, Database: String).
 */
@Converter(autoApply = true)
public class GenderVoConverter implements AttributeConverter<GenderVo, String> {

  /**
   * Entityに格納されている値をDatabaseに格納されるデータ型に変換します.
   *
   * @param attribute 変換されるEntity属性値
   * @return Database列に格納される変換データ
   */
  public String convertToDatabaseColumn(GenderVo attribute) {
    return attribute == null ? null : attribute.getCode();
  }

  /**
   * Databaseに格納されている値をEntityに格納されるデータ型に変換します.
   *
   * @param attribute 変換されるDatabase属性値
   * @return Entityに格納される変換データ
   */
  public GenderVo convertToEntityAttribute(String attribute) {
    return attribute == null ? null : CodeUtils.decode(attribute, GenderVo.class);
  }

}
