package com.limengxiang.everlogic.logic.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.LogicFacade;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.util.Arrays;
import java.util.Iterator;

public class JSONLogic implements LogicUnit {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    private enum JSONOperator {
        equal,
        ne,
        contain,
        inside,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        JSONOperator operator;
        try {
            operator = JSONOperator.valueOf(paramBag.getOperator().toLowerCase());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        JsonNode leftJson = objectMapper.readValue((String) paramBag.getOperand(0), JsonNode.class);
        JsonNode rightJson = objectMapper.readValue((String) paramBag.getOperand(1), JsonNode.class);
        switch (operator) {
            case equal:
                return equal(leftJson, rightJson);
            case ne:
                return !equal(leftJson, rightJson);
            case contain:
                return contain(leftJson, rightJson);
            case inside:
                return contain(rightJson, leftJson);
            default:
                return false;
        }
    }

    private boolean contain(JsonNode json1, JsonNode json2) throws Exception {
        if (json1 == null) {
            return false;
        }
        if (json2 == null) {
            return true;
        }
        if (json1.size() < json2.size()) {
            return false;
        }
        Iterator<String> json2Fields = json2.fieldNames();
        while (json2Fields.hasNext()) {
            String field = json2Fields.next();
            if (!equals(json1.get(field), json2.get(field))) {
                return false;
            }
        }
        return true;
    }

    private boolean equal(JsonNode json1, JsonNode json2) throws Exception {
        if (json1 == null && json2 == null) {
            return true;
        }
        if (json1 == null || json2 == null) {
            return false;
        }
        if (json1.size() != json2.size()) {
            return false;
        }
        Iterator<String> json1Fields = json1.fieldNames();
        while (json1Fields.hasNext()) {
            String field = json1Fields.next();
            if (!equals(json1.get(field), json2.get(field))) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(Object var0, Object var1) throws Exception {
        if (var0 == null && var1 == null) {
            return true;
        }
        ParamTypeEnum type0 = inferParamType(var0);
        ParamTypeEnum type1 = inferParamType(var1);
        if (type0 == null || !type0.equals(type1)) {
            return false;
        }
        LogicParamBag paramBag = new LogicParamBag(type0, OperatorConst.EQUAL, Arrays.asList(getValue(var0), getValue(var1)));
        return LogicFacade.process(paramBag);
    }

    private ParamTypeEnum inferParamType(Object v) {
        if (v instanceof Number || v instanceof NumericNode) {
            return ParamTypeEnum.number;
        }
        if (v instanceof String || v instanceof StringNode || v instanceof TextNode) {
            return ParamTypeEnum.string;
        }
        if (v instanceof Boolean || v instanceof BooleanNode) {
            return ParamTypeEnum.bool;
        }
        return null;
    }

    private Object getValue(Object v) {
        if (v instanceof NumericNode) {
            return ((NumericNode) v).doubleValue();
        }
        if (v instanceof StringNode) {
            return ((StringNode) v).toString();
        }
        if (v instanceof TextNode) {
            return v.toString();
        }
        if (v instanceof BooleanNode) {
            return ((BooleanNode) v).booleanValue();
        }
        return null;
    }
}
