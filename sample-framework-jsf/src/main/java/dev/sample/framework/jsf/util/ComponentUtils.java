package dev.sample.framework.jsf.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import lombok.experimental.UtilityClass;
import dev.sample.common.code.CodeVo;

/**
 * コンポーネント・ユーティリティ.
 */
@UtilityClass
public class ComponentUtils {

  // NOTE: 現状利用無し

  /**
   * コンポーネント(SelectItem)のリストを返します.
   *
   * @param <CD> デコード先となるコードの型
   * @param codeType デコード先となるコードの型
   * @return コンポーネントのリスト.
   */
  @SuppressWarnings("unchecked")
  public static <CD extends CodeVo> List<SelectItem> items(Class<CD> codeType) {
    CD[] values;
    try {
      values = (CD[]) codeType.getMethod("values").invoke(null);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      throw new IllegalArgumentException(e);
    }

    List<SelectItem> items = new ArrayList<>();
    for (CD code : values) {
      SelectItem item = new SelectItem(code.getCode(), code.getDecode());
      items.add(item);
    }

    return items;
  }

}
