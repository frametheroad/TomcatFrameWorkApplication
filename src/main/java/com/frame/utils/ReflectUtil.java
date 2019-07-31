package com.frame.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wuming on 2019/7/30.
 */
public class ReflectUtil {
    /**
     * 反射对象
     *
     * @return 是否有数据为null
     * @throws Exception
     */
    public static boolean reflectObject(Object obj) {
        boolean len = false;
        Field[] field = obj.getClass().getDeclaredFields();
        try {
            for (int j = 0; j < field.length; j++) {
                String name = field[j].getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                if (name.equals("_id")) {
                    Method m = obj.getClass().getMethod("get" + name);
                    String value = (String) m.invoke(obj);
                    if (value != null && !"".equals(value)) {
                        System.out.println("属性值为：" + value);
                        len = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }
}
