import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicTypeEnum;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.condition.AggregateGroup;
import com.limengxiang.everlogic.condition.Group;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AggregateGroupTest {

    @SneakyThrows
    @Test
    public void testNumber() {
        // false
        Group g1 = new Group(
                LogicTypeEnum.and,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.GREATER_THAN, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.LESS_THAN_OR_EQUAL, Arrays.asList(1000.0, 1000))
                )
        );
        // true
        Group g2 = new Group(
                LogicTypeEnum.or,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.GREATER_THAN_OR_EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.LESS_THAN, Arrays.asList(1000.0, 1000))
                )
        );
        // false
        Group g3 = new Group(
                LogicTypeEnum.xor,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.NOT_EQUAL, Arrays.asList(1000.0, 1000)),
                        new LogicParamBag(ParamTypeEnum.number, OperatorConst.LESS_THAN, Arrays.asList(1000.0, 1000))
                )
        );
        AggregateGroup aggregateGroup = new AggregateGroup(LogicTypeEnum.and, Arrays.asList(g1, g2, g3));
        Assert.assertFalse(aggregateGroup.process());

        aggregateGroup.setLogicType(LogicTypeEnum.or);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g2));
        aggregateGroup.setLogicType(LogicTypeEnum.or);
        Assert.assertTrue(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.xor);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g3));
        aggregateGroup.setLogicType(LogicTypeEnum.or);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.xor);
        Assert.assertFalse(aggregateGroup.process());
    }

    @SneakyThrows
    @Test
    public void testString() {
        // false
        Group g1 = new Group(
                LogicTypeEnum.and,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.GREATER_THAN, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.LESS_THAN_OR_EQUAL, Arrays.asList("abc", "abc"))
                )
        );
        // true
        Group g2 = new Group(
                LogicTypeEnum.or,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.GREATER_THAN_OR_EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.LESS_THAN, Arrays.asList("abcd", "abc"))
                )
        );
        // false
        Group g3 = new Group(
                LogicTypeEnum.xor,
                Arrays.asList(
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.NOT_EQUAL, Arrays.asList("abc", "abc")),
                        new LogicParamBag(ParamTypeEnum.string, OperatorConst.LESS_THAN, Arrays.asList("abc", "abc"))
                )
        );
        AggregateGroup aggregateGroup = new AggregateGroup(LogicTypeEnum.and, Arrays.asList(g1, g2, g3));
        Assert.assertFalse(aggregateGroup.process());

        aggregateGroup.setLogicType(LogicTypeEnum.or);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g2));
        aggregateGroup.setLogicType(LogicTypeEnum.or);
        Assert.assertTrue(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.xor);
        Assert.assertTrue(aggregateGroup.process());

        aggregateGroup.setGroups(Arrays.asList(g1, g3));
        aggregateGroup.setLogicType(LogicTypeEnum.or);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.and);
        Assert.assertFalse(aggregateGroup.process());
        aggregateGroup.setLogicType(LogicTypeEnum.xor);
        Assert.assertFalse(aggregateGroup.process());
    }
}
