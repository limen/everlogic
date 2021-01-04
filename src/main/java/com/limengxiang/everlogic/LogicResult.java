package com.limengxiang.everlogic;

import lombok.Data;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
@Data
public class LogicResult {
    private Boolean result;
    private Boolean continuable;

    public LogicResult() {}

    public LogicResult(Boolean result, Boolean continuable) {
        this.result = result;
        this.continuable = continuable;
    }
}
