package com.limengxiang.everlogic;

import java.util.Map;

public class SimpleEnvContainer extends AbstractEnvContainer {

    private Map<String, Object> env;

    @Override
    public void setEnv(Map<String, Object> env) {
        this.env = env;
    }

    @Override
    public Object getValue(Object key) {
        if (env == null || !(key instanceof String)) {
            return key;
        }
        String strKey = (String) key;
        if (strKey.startsWith("#{") && strKey.endsWith("}")) {
            return env.get(strKey.substring(2, strKey.length()-1));
        }
        return strKey;
    }
}
