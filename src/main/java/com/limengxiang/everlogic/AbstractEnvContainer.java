package com.limengxiang.everlogic;

import java.util.Map;

abstract public class AbstractEnvContainer implements IEnvContainer {

    protected Map<String, Object> env;

    @Override
    public void setEnv(Map<String, Object> env) {
        this.env = env;
    }

    @Override
    abstract public Object getValue(Object key);
}
