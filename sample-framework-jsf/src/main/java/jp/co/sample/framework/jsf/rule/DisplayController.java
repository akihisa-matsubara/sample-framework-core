package jp.co.sample.framework.jsf.rule;

import jp.co.sample.framework.core.util.CdiUtils;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 表示制御.
 * 設定ファイルと制御仕様に従い表示制御を実施し、制御状態を管理する.
 */
@ViewScoped
@Named
@Slf4j
public class DisplayController implements Serializable {
  /** serialVersionUID. */
  private static final long serialVersionUID = -8315191336035099075L;
  /** 設定ファイル名. */
  private static final String RULE_CONFIG_NAME = "displayRule";

  /** ルール・条件クラス. */
  private Class<? extends RuleConditions> ruleClass;
  /** 制御内容Map（Key:項目ID、Value:制御内容）. */
  private Map<String, ControlContent> controlContents = new ConcurrentHashMap<>();

  /**
   * 初期化.
   *
   * @param ruleClass ルール・条件クラス
   */
  public void init(Class<? extends RuleConditions> ruleClass) {
    this.ruleClass = ruleClass;
    doControl();
  }

  /**
   * 表示判定.
   *
   * @param itemId 項目ID
   * @return 表示する場合はtrue
   */
  public boolean isRendered(String itemId) {
    return ControlContent.HIDDEN != controlContents.get(itemId);
  }

  /**
   * 非活性判定.
   *
   * @param itemId 項目ID
   * @return 非活性にする場合はtrue
   */
  public boolean isDisabled(String itemId) {
    return ControlContent.DISABLED == controlContents.get(itemId);
  }

  /**
   * 制御実行.
   */
  private void doControl() {
    doControl("init");
  }

  /**
   * 制御実行.
   *
   * @param eventName イベント名
   */
  public void doControl(String eventName) {
    Map<String, ControlSpecifications> specMap = new ConcurrentHashMap<>();
    Set<String> specParentNoSet = build(eventName, specMap);
    log.debug("The control specification to execute is " + specParentNoSet);

    RuleConditions rule = CdiUtils.getBean(ruleClass);
    Set<String> matchedSpecNoSet = new HashSet<>();
    String matchedSpecNo = null;
    for (String specParentNo : specParentNoSet) {
      matchedSpecNo = doExecute(rule, specParentNo);
      if (matchedSpecNo != null) {
        matchedSpecNoSet.add(matchedSpecNo);
      }
    }
    log.debug("Matched control specification is " + matchedSpecNoSet);

    organizeControlContents(rule, specMap, matchedSpecNoSet);
    log.debug("Control contents is " + controlContents);
  }

  /**
   * 設定ファイルの制御仕様を元に制御仕様Mapを更新し、実行すべき制御仕様番号（親）のセット返します.
   *
   * @param eventName イベント名
   * @param specMap 制御仕様Map
   * @return specParentNoSet 制御仕様番号（親）のセット
   */
  private Set<String> build(final String eventName, Map<String, ControlSpecifications> specMap) {
    Set<String> specParentNoSet = new HashSet<>();
    for (String controlItem : ConfigFactory.load(RULE_CONFIG_NAME).getStringList(eventName + ".controlItems")) {
      ControlSpecifications ruleContents =
          ConfigBeanFactory.create(ConfigFactory.load(RULE_CONFIG_NAME).getConfig(eventName + "." + controlItem), ControlSpecifications.class);
      specMap.put(controlItem, ruleContents);

      for (String controlSpecNo : ruleContents.getControlSpecNos()) {
        specParentNoSet.add(controlSpecNo.split("-")[0]);
      }
    }

    return specParentNoSet;
  }


  /**
   * 制御仕様を実行し、条件に該当した制御仕様番号を返します.
   *
   * @param conditions ルール・条件
   * @param specParentNo 制御仕様番号（親）
   * @return 条件に該当した制御仕様番号
   */
  private String doExecute(RuleConditions conditions, final String specParentNo) {
    String matchedSpecNo = null;
    switch (specParentNo) {
      case SpecNo.NO_001:
        matchedSpecNo = conditions.matchesNo001();
        break;
      case SpecNo.NO_002:
        matchedSpecNo = conditions.matchesNo002();
        break;
      case SpecNo.NO_003:
        matchedSpecNo = conditions.matchesNo003();
        break;
      case SpecNo.NO_004:
        matchedSpecNo = conditions.matchesNo004();
        break;
      case SpecNo.NO_005:
        matchedSpecNo = conditions.matchesNo005();
        break;
      case SpecNo.NO_006:
        matchedSpecNo = conditions.matchesNo006();
        break;
      case SpecNo.NO_007:
        matchedSpecNo = conditions.matchesNo007();
        break;
      case SpecNo.NO_008:
        matchedSpecNo = conditions.matchesNo008();
        break;
      case SpecNo.NO_009:
        matchedSpecNo = conditions.matchesNo009();
        break;
      case SpecNo.NO_010:
        matchedSpecNo = conditions.matchesNo010();
        break;
      default:
        // do nothing.
        break;
    }

    return matchedSpecNo;
  }

  /**
   * 最終的な制御内容を制御内容Mapに設定します.
   * 設定処理を行います.
   *
   * @param rule ルール
   * @param specMap 制御仕様Map
   * @param matchedSpecNoSet 条件に該当した制御仕様番号セット
   */
  private void organizeControlContents(RuleConditions rule, Map<String, ControlSpecifications> specMap, Set<String> matchedSpecNoSet) {
    for (Entry<String, ControlSpecifications> specEntry : specMap.entrySet()) {
      for (int i = 0; i < specEntry.getValue().getControlSpecNos().size(); i++) {
        ControlSpecifications specs = specEntry.getValue();
        if (matchedSpecNoSet.contains(specs.getControlSpecNos().get(i))) {
          ControlContent controlContent = ControlContent.decode(specs.getControlContents().get(i));
          if (ControlContent.SET == controlContent) {
            controlContent = rule.updateValue(specEntry.getKey(), specs.getControlSpecNos().get(i));
          }
          controlContents.put(specEntry.getKey(), controlContent);
          break;
        }
      }
    }
  }
}
