import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.group.AggregateLogicGroup;
import com.limengxiang.everlogic.group.LogicGroup;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AggregateLogicGroupTest {

    @SneakyThrows
    @Test
    public void testNumber() {
        // false
        LogicGroup g1 = new LogicGroup(
                LogicOperatorEnum.and,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.GREATER_THAN, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.LESS_THAN_OR_EQUAL, Arrays.asList(1000.0, 1000))
                )
        );
        // true
        LogicGroup g2 = new LogicGroup(
                LogicOperatorEnum.or,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.GREATER_THAN_OR_EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.LESS_THAN, Arrays.asList(1000.0, 1000))
                )
        );
        // false
        LogicGroup g3 = new LogicGroup(
                LogicOperatorEnum.xor,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.NOT_EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.LESS_THAN, Arrays.asList(1000.0, 1000))
                )
        );
        AggregateLogicGroup aggregateGroup = new AggregateLogicGroup(LogicOperatorEnum.and, Arrays.asList(g1, g2, g3));
        Assert.assertFalse(aggregateGroup.process());

        aggregateGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g2));
        aggregateGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g3));
        aggregateGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertFalse(aggregateGroup.process());
    }

    @SneakyThrows
    @Test
    public void testString() {
        // false
        LogicGroup g1 = new LogicGroup(
                LogicOperatorEnum.and,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.GREATER_THAN, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.LESS_THAN_OR_EQUAL, Arrays.asList("abc", "abc"))
                )
        );
        // true
        LogicGroup g2 = new LogicGroup(
                LogicOperatorEnum.or,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.GREATER_THAN_OR_EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.LESS_THAN, Arrays.asList("abcd", "abc"))
                )
        );
        // false
        LogicGroup g3 = new LogicGroup(
                LogicOperatorEnum.xor,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.NOT_EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.LESS_THAN, Arrays.asList("abc", "abc"))
                )
        );
        AggregateLogicGroup aggregateGroup = new AggregateLogicGroup(LogicOperatorEnum.and, Arrays.asList(g1, g2, g3));
        Assert.assertFalse(aggregateGroup.process());

        aggregateGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g2));
        aggregateGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g3));
        aggregateGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertFalse(aggregateGroup.process());
    }
}
