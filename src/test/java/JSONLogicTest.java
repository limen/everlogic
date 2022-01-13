import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.logic.JSONLogic;
import com.limengxiang.everlogic.LogicUnitFactoryContainer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JSONLogicTest {

    @Test
    public void testEqual() {
        JSONLogic logic = new JSONLogic();
        logic.setLogicUnitFactoryContainer(new LogicUnitFactoryContainer());

        String json1 = "{\"num\":123, \"name\":\"alice\"}";
        String json2 = "{\"num\":123, \"name\":\"alice\"}";
        Assert.assertTrue(logic.process(OperatorConst.EQUAL, Arrays.asList(json1, json2)));
        json2 = "{\"num\":123}";
        Assert.assertFalse(logic.process(OperatorConst.EQUAL, Arrays.asList(json1, json2)));
        json2 = "{\"num\":123, \"name\":\"Alice\"}";
        Assert.assertFalse(logic.process(OperatorConst.EQUAL, Arrays.asList(json1, json2)));
        json2 = "{\"num\":123, \"name\":\"alice\", \"age\":18}";
        Assert.assertFalse(logic.process(OperatorConst.EQUAL, Arrays.asList(json1, json2)));
        Assert.assertTrue(logic.process(OperatorConst.INSIDE, Arrays.asList(json1, json2)));
        Assert.assertTrue(logic.process(OperatorConst.CONTAIN, Arrays.asList(json2, json1)));
        json2 = "{\"name\":\"alice\",\"num\":123}";
        Assert.assertTrue(logic.process(OperatorConst.EQUAL, Arrays.asList(json1, json2)));
    }
}
