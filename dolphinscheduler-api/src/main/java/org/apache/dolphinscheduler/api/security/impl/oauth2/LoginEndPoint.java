package org.apache.dolphinscheduler.api.security.impl.oauth2;

/**
 * @author luoandon@gmail.com
 * @date 2021/11/10
 */
public class LoginEndPoint {
    /**
     * 登录地址
     */
    private String loginEndPoint;

    public String getLoginEndPoint() {
        return loginEndPoint;
    }

    public LoginEndPoint(String loginEndPoint) {
        this.loginEndPoint = loginEndPoint;
    }

    public LoginEndPoint setLoginEndPoint(String loginEndPoint) {
        this.loginEndPoint = loginEndPoint;
        return this;
    }
}