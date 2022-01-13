import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.logic.BoolLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BoolLogicTest {
    @Test
    public void test() {
        BoolLogic boolLogic = new BoolLogic();
        Assert.assertTrue(boolLogic.process(OperatorConst.EQUAL, Arrays.asList(true, true)));
        Assert.assertTrue(boolLogic.process("equal", Arrays.asList("true", true)));
        Assert.assertFalse(boolLogic.process("equal", Arrays.asList("true", false)));
        Assert.assertFalse(boolLogic.process("equal", Arrays.asList("true", "false")));
    }
}
