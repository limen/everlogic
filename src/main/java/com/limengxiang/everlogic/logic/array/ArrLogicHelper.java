package com.limengxiang.everlogic.logic.array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class ArrLogicHelper {

    public static boolean contains(List leftList, List rightList) {
        if (leftList.size() < rightList.size()) {
            return false;
        }
        Map<Object, Integer> leftElemCount = ArrLogicHelper.getElemCount(leftList);
        Map<Object, Integer> rightElemCount = ArrLogicHelper.getElemCount(rightList);
        if (leftElemCount.size() < rightElemCount.size()) {
            return false;
        }
        for (Object k : rightElemCount.keySet()) {
            if (!leftElemCount.containsKey(k)) {
                return false;
            }
            if (leftElemCount.get(k) < rightElemCount.get(k)) {
                return false;
            }
        }
        return true;
    }

    public static Map<Object, Integer> getElemCount(List list) {
        Map<Object, Integer> elemCount = new HashMap<>();
        for (Object l : list) {
            if (!elemCount.containsKey(l)) {
                elemCount.put(l, 1);
            }
            elemCount.replace(l, elemCount.get(l) + 1);
        }
        return elemCount;
    }
}
