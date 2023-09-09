package org.apache.dolphinscheduler.api.security.impl.oauth2;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

/**
 * 应用客户端行为动作策略接口 适配前后端分离的单页应用
 *
 * @author luoandon@gmail.com
 * @date 2021/11/9
 */
@Component
public class SpaUaaClientActionStrategyImpl implements IUaaClientActionStrategy {

	private static final Logger logger = LoggerFactory.getLogger(SpaUaaClientActionStrategyImpl.class);
	
	@Autowired
	ClientProperties clientProperties ; 
   
    @Autowired
    TokenProperties tokenProperties ; 
	
	/**
	 * 通过授权码和授权服务器交互获取token
	 *
	 * @param strategyContext 执行上下文
	 * @throws IOException
	 */
	@Override
	public TokenBody handleExchangeToken(String code) throws IOException {

		logger.info("handle token exchange request, code = {}", code);

		String tokenUrl = clientProperties.getTokenEndPoint(code);
		String jsonBody = HttpRequest.post(tokenUrl).contentType("application/x-www-form-urlencoded").execute().body();
		
		logger.debug("token url = {}" , tokenUrl) ; 
		logger.debug("json Body = {}" , jsonBody) ; 

		AccessToken accessTokenBean = JSONUtil.toBean(jsonBody , AccessToken.class);
		logger.debug("json object = {}", accessTokenBean);
		
		String accessToken = accessTokenBean.getAccess_token() ;
		
		// 获取openId
		TokenBody tokenBody = TokenBody.parseToken(accessToken , tokenProperties);
		
		return tokenBody ; 
	}

	/**
	 * 获取授权服务器登录地址
	 *
	 * @param strategyContext 执行上下文
	 * @throws IOException
	 */
	@Override
	public void handleGetLoginEndPoint(ActionStrategyContext strategyContext) throws IOException {
//		HttpServletResponse resp = strategyContext.response;
//		logger.info("handle request oauth login endpoint request");
//
////		ClientProperties clientProperties = Context.applicationContext.getBean(ClientProperties.class);
////		new LoginEndPoint(clientProperties.getLoginEndPoint());
//
//		LoginEndPoint loginEndPoint = getLoginEndPoint();
//		logger.debug("loginEndPoint = " + JSONObject.toJSONString(loginEndPoint));
//
//		AjaxResult ajaxResult = AjaxResult.success("操作成功", loginEndPoint);
//		
//		printJson(resp, ajaxResult);
	}

	/**
	 * 获取到登陆授权地址
	 * 
	 * @return
	 */
	@Override
	public LoginEndPoint getLoginEndPoint() {
		LoginEndPoint loginEndPoint = new LoginEndPoint(clientProperties.getLoginEndPoint());
		return loginEndPoint;
	}

	/**
	 * 应用退出登录处理
	 *
	 * @param strategyContext 执行上下文
	 */
	@Override
	public void handleSignOut(ActionStrategyContext strategyContext) throws IOException {
//		HttpServletResponse response = strategyContext.response;
//		HttpServletRequest request = strategyContext.request;
//		TokenBody tokenBody = parseToken(request.getParameter("token"));
//		if (tokenBody == null) {
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//		} else {
//			ConcurrentHashMap<String, String> sessionContext = strategyContext.sessionContext;
//			sessionContext.put(tokenBody.getUserId(), tokenBody.getUserName());
//			logger.info("user {} logout success", tokenBody.getUserName());
//			printJson(response, AjaxResult.success("操作成功", ""));
//		}
	}

	/**
	 * 获取退出接口
	 *
	 * @param strategyContext 执行上下文
	 */
	@Override
	public void handleUaaLogoutUrl(ActionStrategyContext strategyContext) throws IOException {
//		String uaaServer = clientProperties.getAuthorizationServer();
//
//		String url = uaaServer + "/preLogout";
//		AjaxResult ajaxResult = AjaxResult.success("操作成功", url);
//
//		printJson(strategyContext.response, ajaxResult);
	}

//	private void printJson(HttpServletResponse response, AjaxResult ajaxResult) throws IOException {
//
//		response.setHeader("Content-Type", "application/json;charset=utf-8");
//		response.setStatus(HttpServletResponse.SC_OK);
//
//		PrintWriter writer = response.getWriter();
//		writer.write(JSON.toJSONString(ajaxResult));
//		writer.flush();
//		writer.close();
//
//		response.flushBuffer();
//	}

//    private boolean isBlank(String s) {
//        return s == null || s.length() == 0 || " ".equals(s.trim());
//    }

}
