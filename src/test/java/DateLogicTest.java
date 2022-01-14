import com.limengxiang.everlogic.FormatterConst;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.formatter.DateFormatter;
import com.limengxiang.everlogic.logic.DateLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class DateLogicTest {

    @Test
    public void testEqual() {
        DateLogic dateLogic = new DateLogic();
        Assert.assertTrue(dateLogic.process(OperatorConst.EQUAL, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 00:00:00")));
        Assert.assertTrue(dateLogic.process(OperatorConst.NOT_EQUAL, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 00:00:01")));
        Assert.assertTrue(dateLogic.process(OperatorConst.LESS_THAN, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 00:00:01")));
        Assert.assertTrue(dateLogic.process(OperatorConst.LESS_THAN_OR_EQUAL, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 00:00:01")));

        Assert.assertFalse(dateLogic.process(OperatorConst.EQUAL, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 00:00:01")));
        Assert.assertFalse(dateLogic.process(OperatorConst.GREATER_THAN, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 00:00:01")));
        Assert.assertFalse(dateLogic.process(OperatorConst.GREATER_THAN_OR_EQUAL, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 00:00:01")));

        Assert.assertTrue(dateLogic.process(OperatorConst.NIL, Arrays.asList("")));
        Assert.assertTrue(dateLogic.process(OperatorConst.NIL, Collections.singletonList(null)));
    }

    @Test
    public void testFormatter() {
        DateLogic dateLogic = new DateLogic();
        DateFormatter formatter = new DateFormatter();
        formatter.setType(FormatterConst.Dates.TYPE_FORMAT);
        formatter.setArgs(Collections.singletonList("yyMMdd"));
        dateLogic.setFormatter(formatter);
        Assert.assertTrue(dateLogic.process(OperatorConst.EQUAL, Arrays.asList("1997-07-01 00:00:00", "1997-07-01 01:00:00")));

        formatter.setArgs(Collections.singletonList("MMdd"));
        Assert.assertTrue(dateLogic.process(OperatorConst.EQUAL, Arrays.asList("1998-07-01 00:00:00", "1997-07-01 01:00:00")));

        formatter.setArgs(Collections.singletonList("HHmmss"));
        Assert.assertTrue(dateLogic.process(OperatorConst.EQUAL, Arrays.asList("1998-07-01 01:10:00", "1997-07-01 01:10:00")));
    }
}
