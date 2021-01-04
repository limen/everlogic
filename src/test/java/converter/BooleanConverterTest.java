package converter;

import com.limengxiang.everlogic.converter.BooleanConverter;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class BooleanConverterTest {

    @SneakyThrows
    @Test
    public void testApply() {
        BooleanConverter converter = new BooleanConverter();
        Assert.assertFalse(converter.apply("false"));
        Assert.assertTrue(converter.apply("true"));
        Assert.assertFalse(converter.apply(false));
        Assert.assertTrue(converter.apply(true));
        Assert.assertFalse(converter.apply(null));

        Boolean bool = true;
        Assert.assertTrue(converter.apply(bool));
        bool = false;
        Assert.assertFalse(converter.apply(bool));
    }
}
