package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.StringComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.StringConverter;
import com.limengxiang.everlogic.util.StrUtil;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 * - null -> nil,blank
 * - "" -> nil,blank
 * - "  " -> not_nil, blank
 *
 */
public class StringLogic extends AbstractLogicUnit {

    @Override
    public Converter getDefaultConverter() {
        return new StringConverter();
    }

    @Override
    public Comparator getDefaultComparator() {
        return new StringComparator();
    }

    private enum OpEnum {
        gt,
        gte,
        lt,
        lte,
        equal,
        ne,
        contain,
        inside,
        start_with,
        end_with,
        blank,
        regex,
        nil,
        not_nil,
    }

    @Override
    public boolean process(String op, List<Object> operands) {
        OpEnum opEnum;
        try {
            opEnum = OpEnum.valueOf(op);
        } catch (Exception ex) {
            throw new RuntimeException("Unsupported operator:" + op);
        }

        String leftOperand = (String) getConverter().apply(operands.get(0));
        if (OpEnum.nil.equals(opEnum)) {
            return leftOperand == null || StrUtil.isEmpty(leftOperand);
        }
        if (OpEnum.not_nil.equals(opEnum)) {
            return StrUtil.isNotEmpty(leftOperand);
        }
        if (OpEnum.blank.equals(opEnum)) {
            return leftOperand == null || StrUtil.isBlank(leftOperand);
        }

        String rightOperand = (String) getConverter().apply(operands.get(1));
        int compare = getComparator().apply(leftOperand, rightOperand);
        switch (opEnum) {
            case gt:
                return compare > 0;
            case gte:
                return compare >= 0;
            case lt:
                return compare < 0;
            case lte:
                return compare <= 0;
            case equal:
                return compare == 0;
            case ne:
                return compare != 0;
            case start_with:
                return leftOperand != null && rightOperand != null && leftOperand.startsWith(rightOperand);
            case end_with:
                return leftOperand != null && rightOperand != null && leftOperand.endsWith(rightOperand);
            case contain:
                return leftOperand != null && rightOperand != null && leftOperand.contains(rightOperand);
            case inside:
                return leftOperand != null && rightOperand != null && rightOperand.contains(leftOperand);
            case regex:
                if (getEvaludator() != null) {
                    return getEvaludator().getRegExCache().compile(rightOperand).matcher(leftOperand).matches();
                }
                return Pattern.compile(rightOperand).matcher(leftOperand).matches();
            default:
                return false;
        }
    }
}
