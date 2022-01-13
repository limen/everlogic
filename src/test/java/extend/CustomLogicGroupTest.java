package extend;

import com.limengxiang.everlogic.LogicConditionEnum;
import com.limengxiang.everlogic.LogicRule;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.extend.CustomLogicUnitFactory;
import com.limengxiang.everlogic.extend.CustomParamTypeEnum;
import com.limengxiang.everlogic.LogicEvaluator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class CustomLogicGroupTest {

    @Test
    public void test() {

        LogicRule p1 = new LogicRule();
        p1.setParamType(CustomParamTypeEnum.string_ci);
        p1.setOperator(OperatorConst.EQUAL);
        p1.setOperands(Arrays.asList("abc", "ABC"));
        LogicRule p2 = new LogicRule();
        p2.setParamType(CustomParamTypeEnum.string_ci);
        p2.setOperator(OperatorConst.NOT_EQUAL);
        p2.setOperands(Arrays.asList("abc", "abcd"));

        LogicRule pp = new LogicRule();
        pp.setCondition(LogicConditionEnum.and);
        pp.setRules(Arrays.asList(p1, p2));

        LogicEvaluator evaluator = new LogicEvaluator();
        evaluator.addLogicUnitFactory(new CustomLogicUnitFactory());
        Assert.assertTrue(evaluator.eval(pp));
        p2.setOperator(OperatorConst.EQUAL);
        Assert.assertFalse(evaluator.eval(pp));

        pp.setCondition(LogicConditionEnum.or);
        Assert.assertTrue(evaluator.eval(pp));
    }
}
