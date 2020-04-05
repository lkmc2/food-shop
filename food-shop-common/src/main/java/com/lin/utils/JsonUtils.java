package com.lin.utils;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Json 工具类
 * @author lkmc2
 * @date 2020/4/5 20:58
 */
public class JsonUtils {

    /** jackson 转换器 **/
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成 json 字符串
     * @param data 实体类对象
     * @return json 字符串
     */
    public static String objectToJson(Object data) {
    	try {
            return MAPPER.writeValueAsString(data);
		} catch (JsonProcessingException e) {
            throw new RuntimeException(StrUtil.format("实体类【{}】转换成json字符串失败", data.getClass().getName()), e);
		}
    }
    
    /**
     * 将 json 字符串转化为实体类对象
     * @param jsonStr json 字符串
     * @param beanType 目标实体类类型
     * @return 实体类对象
     */
    public static <T> T jsonToPojo(String jsonStr, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonStr, beanType);
        } catch (Exception e) {
            throw new RuntimeException(StrUtil.format("json字符串转换成实体类【{}】失败", beanType.getName()), e);
        }
    }
    
    /**
     * 将json数据转换成pojo对象list
     * @param jsonStr json 字符串
     * @param beanType 目标实体类类型
     * @return 实体类列表
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
            return MAPPER.readValue(jsonStr, javaType);
		} catch (Exception e) {
            throw new RuntimeException(StrUtil.format("json字符串转换成实体类【{}】列表失败", beanType.getName()), e);
		}
    }
    
}
