package com.limengxiang.everlogic;

import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public interface ILogicUnit {

    boolean process(String operator, List<Object> operands);

}
