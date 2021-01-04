import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.comparator.CalenderComparator;
import com.limengxiang.everlogic.logic.DateLogic;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class DateLogicTest {

    @SneakyThrows
    @Test
    public void testEqual() {
        DateLogic dateLogic = new DateLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.date);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:00", "1997-10-01 12:00:00"));
        Assert.assertTrue(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:00"));
        Assert.assertFalse(dateLogic.process(paramBag));

        ParseException ex1 = null;
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "19971001 12:00:00"));
        try {
            dateLogic.process(paramBag);
        } catch (ParseException e) {
            ex1 = e;
        }
        Assert.assertNotNull(ex1);

        ParseException ex2 = null;
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", null));
        try {
            dateLogic.process(paramBag);
        } catch (ParseException e) {
            ex2 = e;
        }
        Assert.assertNotNull(ex2);
    }

    @SneakyThrows
    @Test
    public void testNotEqual() {
        DateLogic dateLogic = new DateLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.date);
        paramBag.setOperator(OperatorConst.NOT_EQUAL);
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:00", "1997-10-01 12:00:00"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:00"));
        Assert.assertTrue(dateLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testGT() {
        DateLogic dateLogic = new DateLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.date);
        paramBag.setOperator(OperatorConst.GREATER_THAN);
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:00", "1997-10-01 12:00:00"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:00"));
        Assert.assertTrue(dateLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testGTE() {
        DateLogic dateLogic = new DateLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.date);
        paramBag.setOperator(OperatorConst.GREATER_THAN_OR_EQUAL);
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:00", "1997-10-01 12:00:00"));
        Assert.assertTrue(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:00"));
        Assert.assertTrue(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:02"));
        Assert.assertFalse(dateLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testLT() {
        DateLogic dateLogic = new DateLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.date);
        paramBag.setOperator(OperatorConst.LESS_THAN);
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:00", "1997-10-01 12:00:00"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:02"));
        Assert.assertTrue(dateLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testLTE() {
        DateLogic dateLogic = new DateLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.date);
        paramBag.setOperator(OperatorConst.LESS_THAN_OR_EQUAL);
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:00", "1997-10-01 12:00:00"));
        Assert.assertTrue(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:00"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:00:02"));
        Assert.assertTrue(dateLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testCalenderComparator() {
        CalenderComparator comparator = new CalenderComparator();
        comparator.setFields(Arrays.asList(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH));
        DateLogic dateLogic = new DateLogic();
        dateLogic.setComparator(comparator);
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.date);
        paramBag.setOperator(OperatorConst.LESS_THAN);
        paramBag.setOperands(Arrays.asList("1997-10-01 13:00:00", "1997-10-01 12:00:00"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 10:00:01", "1997-10-01 11:00:00"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-02 12:00:02"));
        Assert.assertTrue(dateLogic.process(paramBag));

        comparator.setFields(Arrays.asList(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:10:02"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 13:00:01", "1997-10-01 12:10:02"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 11:00:01", "1997-10-01 12:10:02"));
        Assert.assertTrue(dateLogic.process(paramBag));

        comparator.setFields(Arrays.asList(Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE));
        paramBag.setOperands(Arrays.asList("1997-10-01 12:00:01", "1997-10-01 12:10:02"));
        Assert.assertTrue(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 13:00:01", "1997-10-01 12:10:02"));
        Assert.assertFalse(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1997-10-01 11:00:01", "1997-10-01 12:10:02"));
        Assert.assertTrue(dateLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("2000-10-10 11:10:01", "1997-10-12 12:10:02"));
        Assert.assertTrue(dateLogic.process(paramBag));
    }
}
