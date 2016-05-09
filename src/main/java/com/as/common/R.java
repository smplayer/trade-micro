package com.as.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yrx on 2016/5/5.
 */
public class R<T> {
//    private final static String STATUS = "status";
    private final static String STATUS_SUCCESS = "status_success";
    private final static String RESULT = "result";

    private Map<String, Object> map = new HashMap<>();

    public R(){

    }

    public R(T result){
        this.result(result);
    }

    public R<T> result(T result){
        map.put(RESULT, result);
        return this;
    }

    public T result(){
        return (T) map.get(RESULT);
    }

    public Object message(String key){
        return map.get(key);
    }

    public R<T> message(String key, Object content){
        map.put(key, content);
        return this;
    }

    public R<T> success(Boolean v){
        map.put(STATUS_SUCCESS, v);
        return this;
    }

    public Boolean success(){
        return (Boolean) map.get(STATUS_SUCCESS);
    }

}
