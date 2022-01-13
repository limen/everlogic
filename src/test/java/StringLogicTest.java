import com.limengxiang.everlogic.FormatterConst;
import com.limengxiang.everlogic.LogicRule;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.OperandTypeEnum;
import com.limengxiang.everlogic.formatter.StringFormatter;
import com.limengxiang.everlogic.logic.StringLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class StringLogicTest {

    @Test
    public void testEqual() {
        StringLogic stringLogic = new StringLogic();
        LogicRule paramBag = new LogicRule();
        paramBag.setParamType(OperandTypeEnum.string);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList("hello", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList("Hello1", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList("", null));
        Assert.assertFalse(stringLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList(null, null));
        Assert.assertTrue(stringLogic.process(paramBag.getOperator(), paramBag.getOperands()));

        Assert.assertTrue(stringLogic.process(OperatorConst.NIL, Collections.singletonList(null)));
        Assert.assertTrue(stringLogic.process(OperatorConst.BLANK, Collections.singletonList(null)));
        Assert.assertTrue(stringLogic.process(OperatorConst.NIL, Arrays.asList("")));
        Assert.assertTrue(stringLogic.process(OperatorConst.BLANK, Arrays.asList("")));
        Assert.assertTrue(stringLogic.process(OperatorConst.BLANK, Arrays.asList("   ")));
        Assert.assertTrue(stringLogic.process(OperatorConst.NOT_NIL, Arrays.asList("   ")));

        Assert.assertTrue(stringLogic.process(OperatorConst.REG_EX, Arrays.asList("123","^\\d+$")));
        Assert.assertTrue(stringLogic.process(OperatorConst.START_WITH, Arrays.asList("123","12")));
        Assert.assertTrue(stringLogic.process(OperatorConst.END_WITH, Arrays.asList("123","23")));
        Assert.assertTrue(stringLogic.process(OperatorConst.CONTAIN, Arrays.asList("123","23")));
        Assert.assertTrue(stringLogic.process(OperatorConst.INSIDE, Arrays.asList("123","123")));
        Assert.assertTrue(stringLogic.process(OperatorConst.INSIDE, Arrays.asList("123","1234")));
    }

    @Test
    public void testFormatter() {
        StringLogic stringLogic = new StringLogic();
        StringFormatter formatter = new StringFormatter();
        formatter.setType(FormatterConst.Strings.TYPE_LOWER);
        stringLogic.setFormatter(formatter);
        Assert.assertTrue(stringLogic.process(OperatorConst.EQUAL, Arrays.asList("hello", "HEllo")));

        formatter.setType(FormatterConst.Strings.TYPE_SUB);
        formatter.setArgs(Arrays.asList(0, 3));
        Assert.assertTrue(stringLogic.process(OperatorConst.EQUAL, Arrays.asList("hello", "helLO")));
        Assert.assertFalse(stringLogic.process(OperatorConst.EQUAL, Arrays.asList("hello", "heLLO")));
    }

}
