import com.limengxiang.everlogic.*;
import com.limengxiang.everlogic.formatter.NumberFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LogicRuleTest {

    @Test
    public void testNumberGroup() {

        Map<String, Object> env = new HashMap<>();
        env.put("i1000", 1000);
        env.put("s1000", "1000");

        LogicRule p1 = new LogicRule();
        p1.setParamType(OperandTypeEnum.number);
        p1.setOperator(OperatorConst.EQUAL);
        p1.setOperands(Arrays.asList("#{i1000}", 1000.0));
        LogicRule p2 = new LogicRule();
        p2.setParamType(OperandTypeEnum.number);
        p2.setOperator(OperatorConst.GREATER_THAN);
        p2.setOperands(Arrays.asList(1000, 1000.0));
        LogicRule p3 = new LogicRule();
        p3.setParamType(OperandTypeEnum.number);
        p3.setOperator(OperatorConst.GREATER_THAN_OR_EQUAL);
        p3.setOperands(Arrays.asList("#{s1000}", "999.9"));
        LogicRule p4 = new LogicRule();
        p4.setParamType(OperandTypeEnum.number);
        p4.setOperator(OperatorConst.EQUAL);
        p4.setOperands(Arrays.asList("#{s1000}", "1000.0"));

        LogicRule pp = new LogicRule();
        pp.setCondition(LogicConditionEnum.and);
        pp.setRules(Arrays.asList(p1, p2, p3));

        LogicEvaluator logicEvaluator = new LogicEvaluator();
        Assert.assertFalse(logicEvaluator.eval(pp, env));

        pp.setRules(Arrays.asList(p1, p3));
        Assert.assertTrue(logicEvaluator.eval(pp, env));

        pp.setRules(Arrays.asList(p1, p3, p4));
        Assert.assertTrue(logicEvaluator.eval(pp, env));

        LogicRule ruleDepth3 = new LogicRule();
        ruleDepth3.setRules(Collections.singletonList(pp));
        Assert.assertTrue(logicEvaluator.eval(ruleDepth3, env));

        LogicRule ruleDepth4 = new LogicRule();
        ruleDepth4.setRules(Arrays.asList(ruleDepth3, ruleDepth3));
        Assert.assertTrue(logicEvaluator.eval(ruleDepth4, env));

        LogicRule ruleDepth5 = new LogicRule();
        ruleDepth5.setRules(Arrays.asList(ruleDepth3, ruleDepth3, ruleDepth4));
        Assert.assertTrue(logicEvaluator.eval(ruleDepth5, env));

        pp.setRules(Arrays.asList(p1, p2, p3));
        ruleDepth3.setRules(Collections.singletonList(pp));
        Assert.assertFalse(logicEvaluator.eval(ruleDepth3, env));
        ruleDepth4.setRules(Arrays.asList(ruleDepth3, ruleDepth3));
        Assert.assertFalse(logicEvaluator.eval(ruleDepth4, env));
        ruleDepth5.setRules(Arrays.asList(ruleDepth3, ruleDepth3, ruleDepth4));
        Assert.assertFalse(logicEvaluator.eval(ruleDepth5, env));
    }

    @Test
    public void testFormatter() {
        LogicEvaluator evaluator = new LogicEvaluator();

        LogicRule p1 = new LogicRule();
        p1.setParamType(OperandTypeEnum.number);
        p1.setOperator(OperatorConst.EQUAL);
        p1.setOperands(Arrays.asList(1000.4, 1000.3));

        Assert.assertFalse(evaluator.eval(p1));

        NumberFormatter formatter = new NumberFormatter();
        formatter.setType(FormatterConst.Numbers.TYPE_ROUND);
        p1.setFormatter(formatter);

        Assert.assertTrue(evaluator.eval(p1));
    }

    @Test
    public void testRe() {
        LogicEvaluator evaluator = new LogicEvaluator();

        LogicRule p1 = new LogicRule();
        p1.setParamType(OperandTypeEnum.string);
        p1.setOperator(OperatorConst.REG_EX);

        p1.setOperands(Arrays.asList("123", "^\\d+$"));
        Assert.assertTrue(evaluator.eval(p1));

        p1.setOperands(Arrays.asList("123abc", "^\\d+$"));
        Assert.assertFalse(evaluator.eval(p1));
    }
}
