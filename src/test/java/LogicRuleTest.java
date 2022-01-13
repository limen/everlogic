import com.limengxiang.everlogic.*;
import com.limengxiang.everlogic.util.JSONUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class LogicRuleTest {

    @Test
    public void testNumberGroup() {
        LogicRule p1 = new LogicRule();
        p1.setParamType(OperandTypeEnum.number);
        p1.setOperator(OperatorConst.EQUAL);
        p1.setOperands(Arrays.asList(1000, 1000.0));
        LogicRule p2 = new LogicRule();
        p2.setParamType(OperandTypeEnum.number);
        p2.setOperator(OperatorConst.GREATER_THAN);
        p2.setOperands(Arrays.asList(1000, 1000.0));
        LogicRule p3 = new LogicRule();
        p3.setParamType(OperandTypeEnum.number);
        p3.setOperator(OperatorConst.GREATER_THAN_OR_EQUAL);
        p3.setOperands(Arrays.asList("1000", "999.9"));
        LogicRule p4 = new LogicRule();
        p4.setParamType(OperandTypeEnum.number);
        p4.setOperator(OperatorConst.EQUAL);
        p4.setOperands(Arrays.asList("1000", "1000.0"));

        LogicRule pp = new LogicRule();
        pp.setCondition(LogicConditionEnum.and);
        pp.setRules(Arrays.asList(p1, p2, p3));

        LogicEvaluator logicEvaluator = new LogicEvaluator();
        Assert.assertFalse(logicEvaluator.eval(pp));

        pp.setRules(Arrays.asList(p1, p3));
        Assert.assertTrue(logicEvaluator.eval(pp));

        pp.setRules(Arrays.asList(p1, p3, p4));
        Assert.assertTrue(logicEvaluator.eval(pp));

        LogicRule ruleDepth3 = new LogicRule();
        ruleDepth3.setRules(Collections.singletonList(pp));
        Assert.assertTrue(logicEvaluator.eval(ruleDepth3));

        LogicRule ruleDepth4 = new LogicRule();
        ruleDepth4.setRules(Arrays.asList(ruleDepth3, ruleDepth3));
        Assert.assertTrue(logicEvaluator.eval(ruleDepth4));

        LogicRule ruleDepth5 = new LogicRule();
        ruleDepth5.setRules(Arrays.asList(ruleDepth3, ruleDepth3, ruleDepth4));
        Assert.assertTrue(logicEvaluator.eval(ruleDepth5));

        pp.setRules(Arrays.asList(p1, p2, p3));
        ruleDepth3.setRules(Collections.singletonList(pp));
        Assert.assertFalse(logicEvaluator.eval(ruleDepth3));
        ruleDepth4.setRules(Arrays.asList(ruleDepth3, ruleDepth3));
        Assert.assertFalse(logicEvaluator.eval(ruleDepth4));
        ruleDepth5.setRules(Arrays.asList(ruleDepth3, ruleDepth3, ruleDepth4));
        Assert.assertFalse(logicEvaluator.eval(ruleDepth5));
        System.out.println(JSONUtil.stringify(ruleDepth5));
    }
}
