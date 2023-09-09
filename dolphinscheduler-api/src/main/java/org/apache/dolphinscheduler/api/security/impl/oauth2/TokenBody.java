package org.apache.dolphinscheduler.api.security.impl.oauth2;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * AccessToken获取到信息 
 * 
 * @author luoandon@gmail.com
 * @date 2021/11/11
 */
public class TokenBody {
	
    private final String userId;
    private final String userName;

    public TokenBody(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

	public static TokenBody parseToken(String token , TokenProperties tokenProperties) {
        try {
            if (!StringUtils.hasLength(token)) {
                return null;
            }
            
            token = token.replace("Bearer ","");
            
            Claims tokenParsed = Jwts.parser().setSigningKey(tokenProperties.getSecret().getBytes()).parseClaimsJws(token).getBody();
            String userId = tokenParsed.get("openid", String.class);
            String userName = tokenParsed.get("user_name", String.class);
            
            return new TokenBody(userId, userName);
        } catch (Exception e) {
            return null;
        }
		
    }
}
