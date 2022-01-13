package com.limengxiang.everlogic.formatter;

import java.util.List;

abstract public class AbstractFormatter<T> implements Formatter<T> {

    protected String type;

    protected List<Object> args;

    @Override
    abstract public T apply(T v);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
