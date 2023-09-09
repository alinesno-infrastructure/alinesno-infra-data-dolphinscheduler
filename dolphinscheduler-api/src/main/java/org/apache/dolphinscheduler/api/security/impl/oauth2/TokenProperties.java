package org.apache.dolphinscheduler.api.security.impl.oauth2;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 令牌配置
 *
 * @author LuoAnDong
 * @since 2021年5月22日 下午2:29:03
 */
@Component
public class TokenProperties {

    @Value("${alinesno.token.header:Authorization}")
    private String header;// 令牌自定义标识
    
    @Value("${alinesno.token.secret:abcdefghijklmnopqrstuvwxyz}")
    private String secret; // 令牌秘钥
    
    @Value("${alinesno.token.expireTime:1800}")
    private int expireTime; // 令牌有效期（默认30分钟）

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getExpireTime() {
        return (int) Duration.ofMinutes(expireTime).toMinutes();
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

}
