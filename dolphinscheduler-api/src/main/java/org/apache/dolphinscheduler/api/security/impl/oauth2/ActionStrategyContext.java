package org.apache.dolphinscheduler.api.security.impl.oauth2;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ActionStrategyContext {
	
    public final HttpServletRequest request;
    public final HttpServletResponse response;
    public final ConcurrentHashMap<String, String> sessionContext;

    /**
     * @param request        当前请求
     * @param response       当前响应
     * @param sessionContext 回话存储
     * @throws NullPointerException 当任意参数为 null 时
     */
    public ActionStrategyContext(HttpServletRequest request,
                                 HttpServletResponse response,
                                 ConcurrentHashMap<String, String> sessionContext) {
        if (request == null || response == null || sessionContext == null) {
            throw new NullPointerException(String.format("request = %s,response = %s,sessionContext = %s", request, response, sessionContext));
        }
        this.request = request;
        this.response = response;
        this.sessionContext = sessionContext;
    }
    
}
