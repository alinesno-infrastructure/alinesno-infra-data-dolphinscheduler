package org.apache.dolphinscheduler.api.utils;

import cn.dev33.satoken.util.SaResult;

public class SaToResultUtil {

    public static Result to(SaResult result){
        if(result.getCode() == 200){
            return Result.success(result.getData()) ;
        }else{
            Result r = new Result();
            r.setCode(result.getCode());
            r.setMsg(result.getMsg());
            return r ;
        }
    }

}
