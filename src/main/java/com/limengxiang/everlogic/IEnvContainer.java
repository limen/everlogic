package com.limengxiang.everlogic;

import java.util.Map;

public interface IEnvContainer {

    void setEnv(Map<String, Object> env);

    Object getValue(Object key);

}
