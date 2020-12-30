import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.json.JSONLogic;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JSONLogicTest {

    @SneakyThrows
    @Test
    public void testEqual() {
        String json1 = "{\"num\":123, \"name\":\"alice\"}";
        String json2 = "{\"num\":123, \"name\":\"alice\"}";
        JSONLogic logic = new JSONLogic();
        LogicParamBag paramBag = new LogicParamBag(ParamTypeEnum.json, OperatorConst.EQUAL, Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"Alice\"}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"alice\", \"age\":18}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"name\":\"alice\",\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testNotEqual() {
        String json1 = "{\"num\":123, \"name\":\"alice\"}";
        String json2 = "{\"num\":123, \"name\":\"alice\"}";
        JSONLogic logic = new JSONLogic();
        LogicParamBag paramBag = new LogicParamBag(ParamTypeEnum.json, OperatorConst.NOT_EQUAL, Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"Alice\"}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"alice\", \"age\":18}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"name\":\"alice\",\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testContain() {
        String json1 = "{\"num\":123, \"name\":\"alice\"}";
        String json2 = "{\"num\":123, \"name\":\"alice\"}";
        JSONLogic logic = new JSONLogic();
        LogicParamBag paramBag = new LogicParamBag(ParamTypeEnum.json, OperatorConst.CONTAIN, Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"Alice\"}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"alice\", \"age\":18}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"name\":\"alice\",\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testInside() {
        String json1 = "{\"num\":123, \"name\":\"alice\"}";
        String json2 = "{\"num\":123, \"name\":\"alice\"}";
        JSONLogic logic = new JSONLogic();
        LogicParamBag paramBag = new LogicParamBag(ParamTypeEnum.json, OperatorConst.INSIDE, Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"Alice\"}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertFalse(logic.process(paramBag));
        json2 = "{\"num\":123, \"name\":\"alice\", \"age\":18}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
        json2 = "{\"name\":\"alice\",\"num\":123}";
        paramBag.setOperands(Arrays.asList(json1, json2));
        Assert.assertTrue(logic.process(paramBag));
    }
}
