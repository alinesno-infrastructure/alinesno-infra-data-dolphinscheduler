package org.apache.dolphinscheduler.api.security.impl.oauth2;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用客户端行为动作策略接口
 *
 * @author luoandon@gmail.com
 * @date 2021/11/9
 */
public interface IUaaClientActionStrategy {

	// 放置accessToken，并确认是否过期
	public static final ConcurrentHashMap<String , AccessToken> accessTokenContainer = new ConcurrentHashMap<String , AccessToken>() ; 
	
	/**
	 * 通过授权码和授权服务器交互获取token
	 *
	 * @param strategyContext 执行上下文
	 * @throws IOException
	 */
	TokenBody handleExchangeToken(String code) throws IOException;

	/**
	 * 获取授权服务器登录地址
	 *
	 * @param strategyContext 执行上下文
	 * @throws IOException
	 */
	void handleGetLoginEndPoint(ActionStrategyContext strategyContext) throws IOException;

	/**
	 * 应用退出登录处理
	 *
	 * @param strategyContext 执行上下文
	 */
	void handleSignOut(ActionStrategyContext strategyContext) throws IOException;

	/**
	 * 获取退出接口
	 *
	 * @param strategyContext 执行上下文
	 */
	void handleUaaLogoutUrl(ActionStrategyContext strategyContext) throws IOException;

	/**
	 * 获取到登陆授权地址
	 * 
	 * @return
	 */
	LoginEndPoint getLoginEndPoint();

}
