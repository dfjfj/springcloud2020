package com.atguigu.cloud.support.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对象拷贝工具类
 *
 * @author zhangtao
 */
public class BeanConvertUtil {


    /**
     * One type of List, converting to other types of List
     *
     * @param srcList  原始对象
     * @param destType 目标类型
     * @return
     */
    public static <T> List<T> convertBeanList(List<?> srcList,
                                              Class<T> destType) {

        if (srcList == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (Object obj : srcList) {
            if (obj instanceof Map) {
                String json = JSONObject.toJSONString(obj);
                obj = JSON.parseObject(json, destType);
            }
            T destObj = convertBean(obj, destType);
            if (destObj != null) {
                list.add(destObj);
            }
        }
        return list;
    }


    /**
     * A type of object, cast to another type of object , And copies of the same
     * attributes inside
     *
     * @param src
     * @param type
     * @return
     */
    public static <T> T convertBean(Object src, Class<T> type) {
        if (src == null) {
            return null;
        }
        T desObj = null;
        try {
            desObj = type.newInstance();
            copyBean(src, desObj);
        } catch (Exception e) {

        }
        return desObj;
    }

    /**
     * Copies of the same name as the property of the bean
     *
     * @param src
     * @param dest
     */
    public static void copyBean(Object src, Object dest) {
        try {
            BeanUtils.copyProperties(src, dest);
        } catch (Exception e) {
        }
    }

}
