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
 * @author luoxiaodong
 * @version 1.0.0
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
	 * @param code 执行上下文
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
        return TokenBody.parseToken(accessToken , tokenProperties);
	}

	/**
	 * 获取授权服务器登录地址
	 *
	 * @param strategyContext 执行上下文
	 * @throws IOException
	 */
	@Override
	public void handleGetLoginEndPoint(ActionStrategyContext strategyContext) throws IOException {
	}

	/**
	 * 获取到登陆授权地址
	 * 
	 * @return
	 */
	@Override
	public LoginEndPoint getLoginEndPoint() {
        return new LoginEndPoint(clientProperties.getLoginEndPoint());
	}

	/**
	 * 应用退出登录处理
	 *
	 * @param strategyContext 执行上下文
	 */
	@Override
	public void handleSignOut(ActionStrategyContext strategyContext) throws IOException {
	}

	/**
	 * 获取退出接口
	 *
	 * @param strategyContext 执行上下文
	 */
	@Override
	public void handleUaaLogoutUrl(ActionStrategyContext strategyContext) throws IOException {
	}

}
