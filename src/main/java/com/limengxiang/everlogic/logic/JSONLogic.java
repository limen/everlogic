package com.limengxiang.everlogic.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.OperandTypeEnum;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.util.JSONUtil;
import com.limengxiang.everlogic.util.StrUtil;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class JSONLogic extends AbstractLogicUnit {

    private static final ObjectMapper objectMapper;

    @Override
    public Converter getDefaultConverter() {
        return null;
    }

    @Override
    public Comparator getDefaultComparator() {
        return null;
    }

    private enum OpEnum {
        equal,
        ne,
        contain,
        inside,
        nil,
        not_nil,
    }

    static {
        objectMapper = new ObjectMapper();
    }

    @Override
    public boolean process(String op, List<Object> operands) {
        OpEnum opEnum;
        try {
            opEnum = OpEnum.valueOf(op.toLowerCase());
        } catch (Exception ex) {
            throw new RuntimeException("Unsupported operator:" + op);
        }

        String content0 = (String) operands.get(0);
        if (OpEnum.nil.equals(opEnum)) {
            return StrUtil.isBlank(content0);
        }

        JsonNode leftJson = JSONUtil.parse(content0);
        if (OpEnum.not_nil.equals(opEnum)) {
            return leftJson != null;
        }
        JsonNode rightJson = JSONUtil.parse((String) operands.get(1));
        switch (opEnum) {
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

    private boolean contain(JsonNode json1, JsonNode json2) {
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

    private boolean equal(JsonNode json1, JsonNode json2) {
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

    private boolean equals(Object var0, Object var1) {
        if (var0 == null && var1 == null) {
            return true;
        }
        OperandTypeEnum type0 = inferParamType(var0);
        OperandTypeEnum type1 = inferParamType(var1);
        if (type0 == null || !type0.equals(type1)) {
            return false;
        }
        return logicUnitFactoryContainer.
                getLogicUnit(type0).
                process(OperatorConst.EQUAL, Arrays.asList(getValue(var0), getValue(var1)));
    }

    private OperandTypeEnum inferParamType(Object v) {
        if (v instanceof Number || v instanceof NumericNode) {
            return OperandTypeEnum.number;
        }
        if (v instanceof String || v instanceof StringNode || v instanceof TextNode) {
            return OperandTypeEnum.string;
        }
        if (v instanceof Boolean || v instanceof BooleanNode) {
            return OperandTypeEnum.bool;
        }
        if (v instanceof ArrayNode) {
            return OperandTypeEnum.str_arr;
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
        if (v instanceof ArrayNode) {
            Iterator<JsonNode> elements = ((ArrayNode) v).elements();
            List<String> strings = new ArrayList<>();
            while (elements.hasNext()) {
                strings.add(elements.next().asText());
            }
            return strings;
        }
        return null;
    }
}
