package bevol.udf;

import com.aliyun.odps.udf.UDF;
import com.aliyun.odps.utils.StringUtils;

import java.util.TreeSet;

/**
* @program: 字符串去重排序
* @description: 
* @author: zhusijie
* @create: 2019/5/31 13:20
**/
public class ArraySortSet extends UDF {
    public ArraySortSet(){}

    public String evaluate(String str) {
        String[] chars = str.split(",");
        TreeSet<Integer> treeSet = new TreeSet();
        for (String s : chars) {
            treeSet.add(Integer.parseInt(s));
        }
        str = StringUtils.join(treeSet.toArray(),",");
        return str;
    }
}