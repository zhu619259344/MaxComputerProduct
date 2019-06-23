package bevol.udf;

import com.aliyun.odps.udf.UDF;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *搜索下五个产品筛选
 * */
public class SearchGoodsList extends UDF {

    public SearchGoodsList(){}

    private int listLength = 5;
    private final String split = ",";

    public String evaluate(String s1,String s2,String s3,String s4,String s5) {
        ArrayList chars1 = new ArrayList();
        ArrayList chars2 = new ArrayList();
        ArrayList chars3 = new ArrayList();
        ArrayList chars4 = new ArrayList();
        ArrayList chars5 = new ArrayList();
        /**
         * 用于5个篮子去重
         **/
        Set setTmp = new HashSet();
        int size = 0;
        if(s1!=null && s1!=""){
            for(String str:s1.split(split)){
                chars1.add(str);
                setTmp.add(str);
            }
        }
        if(s2!=null && s2!=""){
            for(String str:s2.split(split)){
                size = setTmp.size();
                setTmp.add(str);
                if(size != setTmp.size()){
                    chars2.add(str);
                }
            }
        }
        if(s3!=null && s3!=""){
            for(String str:s3.split(split)){
                size = setTmp.size();
                setTmp.add(str);
                if(size != setTmp.size()){
                    chars3.add(str);
                }
            }
        }
        if(s4!=null && s4!=""){
            for(String str:s4.split(split)){
                size = setTmp.size();
                setTmp.add(str);
                if(size != setTmp.size()){
                    chars4.add(str);
                }
            }
        }
        if(s5!=null && s5!=""){
            for(String str:s5.split(split)){
                size = setTmp.size();
                setTmp.add(str);
                if(size != setTmp.size()){
                    chars5.add(str);
                }
            }
        }

        ArrayList chars6 = new ArrayList();
        String str = "";
        if(chars1.size() >= listLength){
            chars6 = getArrayItems(chars1,listLength);
        }else {
            if (chars1.size() > 0) {
                for (int i = 0;i < chars1.size(); i++) {
                    chars6.add(chars1.get(i));
                }
                listLength -= chars1.size();
            }
            if (chars2.size() >= listLength) {
                ArrayList chars22 = getArrayItems(chars2, listLength);
                for (int i = 0; i < chars22.size(); i++) {
                    chars6.add(chars22.get(i));
                }
            } else {
                if (chars2.size() > 0) {
                    for (int i = 0;i < chars2.size(); i++) {
                        chars6.add(chars2.get(i));
                    }
                    listLength -= chars2.size();
                }
                if (chars3.size() >= listLength) {
                    ArrayList chars33 = getArrayItems(chars3, listLength);
                    for (int i = 0; i < chars33.size(); i++) {
                        chars6.add(chars33.get(i));
                    }
                } else {
                    if (chars3.size() > 0) {
                        for (int i = 0;i < chars3.size(); i++) {
                            chars6.add(chars3.get(i));
                        }
                        listLength -= chars3.size();
                    }
                    if (chars4.size() >= listLength) {
                        ArrayList chars44 = getArrayItems(chars4, listLength);
                        for (int i = 0; i < chars44.size(); i++) {
                            chars6.add(chars44.get(i));
                        }
                    } else {
                        if (chars4.size() > 0) {
                            for (int i = 0;i < chars4.size(); i++) {
                                chars6.add(chars4.get(i));
                            }
                            listLength -= chars4.size();
                        }
                        if (chars5.size() >= listLength) {
                            ArrayList chars55 = getArrayItems(chars5, listLength);
                            for (int i = 0; i < chars55.size(); i++) {
                                chars6.add(chars55.get(i));
                            }
                        } else {
                            if (chars5.size() > 0) {
                                for (int i = 0;i < chars5.size(); i++) {
                                    chars6.add(chars5.get(i));
                                }
                            }
                        }
                    }
                }
            }
        }
        str = StringUtils.join(chars6,",");
        listLength = 5;
        return str;
    }


    /*
     * 数组随机取n个元素
     * */
    private ArrayList getArrayItems(ArrayList arr,int num) {
        ArrayList objArray = new ArrayList();
        for(int i = 0;i < arr.size(); i++){
            objArray.add(arr.get(i));
        }
        //取出的数值项,保存在此数组
        ArrayList return_array = new ArrayList();
        for (int i = 0; i < num; i++) {
            if (objArray.size()>0) {
                Integer arrIndex = (int)Math.floor(Math.random()*objArray.size());
                return_array.add(objArray.get(arrIndex));
                objArray.remove(objArray.get(arrIndex));
            } else {
                break;
            }
        }
        return return_array;
    }
}