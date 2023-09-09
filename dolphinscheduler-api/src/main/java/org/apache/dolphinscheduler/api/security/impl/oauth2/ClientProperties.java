package org.apache.dolphinscheduler.api.security.impl.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author luaodnong@gmail.com
 * @date 2021/7/6
 */
@Component
public class ClientProperties {
    /**
     * 客户端id
     */
    @Value("${alinesno.uaa.client-id:}")
    private String clientId;
    /**
     * 客户端秘钥
     */
    @Value("${alinesno.uaa.client-secret:}")
    private String clientSecret;
    /**
     * 授权服务器地址
     */
    @Value("${alinesno.uaa.authorization-server:#{null}}")
    private String authorizationServer;
    /**
     * 授权码回调地址
     */
    @Value("${alinesno.uaa.redirect-uri:}")
    private String redirectUri;
    /**
     * 客户端应用类型<br />
     * SPA 前后端分离的单页应用<br />
     * MVC 前后端不分离的 MVC 架构应用<br />
     * 默认取值: SPA
     */
    @Value("${alinesno.uaa.client-type:SPA}")
    private String clientType;
    /**
     * 授权登录地址
     */
    @SuppressWarnings("unused")
	private String loginEndPoint;

    public String getLoginEndPoint() {
        return String.format("%s/oauth/authorize?response_type=code&redirect_uri=%s&client_id=%s",
                getAuthorizationServer(), getRedirectUri(), getClientId());
    }

    public String getClientType() {
        return clientType;
    }

    public ClientProperties setClientType(String clientType) {
        this.clientType = clientType;
        return this;
    }

    public String getTokenEndPoint(String code) {
        return String.format(
                "%s/oauth/token?grant_type=authorization_code&code=%s&redirect_uri=%s&client_id=%s&client_secret=%s",
                getAuthorizationServer(), code, getRedirectUri(), getClientId(), getClientSecret());
    }

    public ClientProperties setLoginEndPoint(String loginEndPoint) {
        this.loginEndPoint = loginEndPoint;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public ClientProperties setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public ClientProperties setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public String getAuthorizationServer() {
        return authorizationServer;
    }

    public ClientProperties setAuthorizationServer(String authorizationServer) {
        this.authorizationServer = authorizationServer;
        return this;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public ClientProperties setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }
}
