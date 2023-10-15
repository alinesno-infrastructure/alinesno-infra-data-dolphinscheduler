package org.apache.dolphinscheduler.api.controller.satoken;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.sso.SaSsoProcessor;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.dtflys.forest.Forest;
import org.apache.commons.lang.StringUtils;
import org.apache.dolphinscheduler.api.enums.Status;
import org.apache.dolphinscheduler.api.security.Authenticator;
import org.apache.dolphinscheduler.api.utils.Result;
import org.apache.dolphinscheduler.api.utils.SaToResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

import static org.apache.dolphinscheduler.api.enums.Status.IP_IS_EMPTY;
import static org.apache.dolphinscheduler.common.Constants.*;

/**
 * 集成Sa-Token前后端分离单点登陆代码
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@RestController
public class SsoH5Controller {

	private static final Logger log = LoggerFactory.getLogger(SsoH5Controller.class) ;

	@Autowired
	private Authenticator authenticator;

	// 当前是否登录
	@GetMapping("/sso/isLogin")
	public Object isLogin() {
		return SaResult.data(StpUtil.isLogin());
	}

	// 返回SSO认证中心登录地址
	@GetMapping("/sso/getSsoAuthUrl")
	public Result getSsoAuthUrl(String clientLoginUrl) {
		String serverAuthUrl = SaSsoUtil.buildServerAuthUrl(clientLoginUrl, "");
		return SaToResultUtil.to(SaResult.data(serverAuthUrl));
	}

	// 根据ticket进行登录
	@GetMapping("/sso/doLoginByTicket")
	public Result doLoginByTicket(String ticket , HttpServletRequest request) {
		Object loginId = SaSsoProcessor.instance.checkTicket(ticket, "/sso/doLoginByTicket");
		if(loginId != null) {
			StpUtil.login(loginId);

			String userName = "" ;
			String userPassword = "" ;

			String adminToken = validateUserLogin(userName , userPassword , request) ; //  UUID.randomUUID().toString() ;

			SaResult result = SaResult.data(StpUtil.getTokenValue());
			result.put("adminToken" , adminToken) ;

			return SaToResultUtil.to(result) ;
		}
		return SaToResultUtil.to(SaResult.error("无效ticket：" + ticket));
	}

	private String validateUserLogin(String userName , String userPassword , HttpServletRequest request) {

		// user ip check
		String ip = getClientIpAddress(request);

		// verify username and password
		Result<Map<String, String>> result = authenticator.authenticate(userName, userPassword, ip);
		log.debug("result = {}" , result.toString());

		return result.getData().toString();
	}

	/**
	 * get ip address in the http request
	 *
	 * @param request http servlet request
	 * @return client ip address
	 */
	public static String getClientIpAddress(HttpServletRequest request) {
		String clientIp = request.getHeader(HTTP_X_FORWARDED_FOR);

		if (StringUtils.isNotEmpty(clientIp) && !clientIp.equalsIgnoreCase(HTTP_HEADER_UNKNOWN)) {
			int index = clientIp.indexOf(COMMA);
			if (index != -1) {
				return clientIp.substring(0, index);
			} else {
				return clientIp;
			}
		}

		clientIp = request.getHeader(HTTP_X_REAL_IP);
		if (StringUtils.isNotEmpty(clientIp) && !clientIp.equalsIgnoreCase(HTTP_HEADER_UNKNOWN)) {
			return clientIp;
		}

		return request.getRemoteAddr();
	}

	// 配置SSO相关参数
	@Autowired
	private void configSso(SaSsoConfig sso) {
		// 配置Http请求处理器
		sso.setSendHttp(url -> {
			System.out.println("------ 发起请求：" + url);
			return Forest.get(url).executeAsString();
		});
	}

	// 全局异常拦截
	@ExceptionHandler
	public SaResult handlerException(Exception e) {
		return SaResult.error(e.getMessage());
	}

}