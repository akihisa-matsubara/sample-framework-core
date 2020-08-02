package dev.sample.framework.core.data.converter;

import dev.sample.common.code.CodeVo;
import dev.sample.common.util.CodeUtils;
import javax.persistence.Convert;

/**
 * 汎用コードコンバーター(Entity: CodeVo, Database: String).
 * NOTE: 未使用、Enum変換を汎用化したいが動作しない.Entity側で@{@link Convert}を指定すれば動作する.
 *
 * @param <CD> コードクラス
 */
public abstract class GenericCodeConverter<CD extends Enum<CD> & CodeVo> {

  private final Class<CD> clazz;

  /**
   * デフォルトコンストラクター.
   *
   * @param clazz コードクラス
   */
  public GenericCodeConverter(Class<CD> clazz) {
    this.clazz = clazz;
  }

  /**
   * Entityに格納されている値をDatabaseに格納されるデータ型に変換します.
   *
   * @param attribute 変換されるEntity属性値
   * @return Database列に格納される変換データ
   */
  public String convertToDatabaseColumn(CD attribute) {
    return attribute == null ? null : attribute.getCode();
  }

  /**
   * Databaseに格納されている値をEntityに格納されるデータ型に変換します.
   *
   * @param attribute 変換されるDatabase属性値
   * @return Entityに格納される変換データ
   */
  public CD convertToEntityAttribute(String attribute) {
    return attribute == null ? null : CodeUtils.decode(attribute, clazz);
  }

}
