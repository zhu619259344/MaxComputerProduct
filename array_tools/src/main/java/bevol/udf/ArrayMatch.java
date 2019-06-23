package bevol.udf;

import com.aliyun.odps.udf.UDF;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ArrayMatch extends UDF {
    public ArrayMatch(){}

    public String evaluate(String str1,String str2){
        String[] arr1 = str1.split(",");
        String[] arr2 = str2.split(",");
        String[] result_insect = intersect(arr1, arr2);
        Double a = (double)result_insect.length;
        Double b = (double)(arr1.length+arr2.length-result_insect.length);
        BigDecimal bi1 = new BigDecimal(a.toString());
        BigDecimal bi2 = new BigDecimal(b.toString());
        BigDecimal divide = bi1.divide(bi2, 4, RoundingMode.HALF_UP);
        return divide.toString();
    }

    public static String[] intersect(String[] arr1, String[] arr2) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        LinkedList<String> list = new LinkedList<String>();
        for (String str : arr1) {
            if (!map.containsKey(str)) {
                map.put(str, Boolean.FALSE);
            }
        }
        for (String str : arr2) {
            if (map.containsKey(str)) {
                map.put(str, Boolean.TRUE);
            }
        }

        for (Map.Entry<String, Boolean> e : map.entrySet()) {
           if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
           }
        }
        String[] result = {};
        return list.toArray(result);
    }
}