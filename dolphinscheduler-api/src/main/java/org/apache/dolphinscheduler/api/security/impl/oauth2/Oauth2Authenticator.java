package org.apache.dolphinscheduler.api.security.impl.oauth2;

import org.apache.dolphinscheduler.api.security.impl.AbstractSsoAuthenticator;
import org.apache.dolphinscheduler.api.service.UsersService;
import org.apache.dolphinscheduler.dao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SaToken认证服务
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class Oauth2Authenticator extends AbstractSsoAuthenticator {

	private static final Logger log = LoggerFactory.getLogger(Oauth2Authenticator.class) ; 

    @Autowired
    private UsersService userService;

    @Override
    public User login(String userId, String password, String extra) {
        return userService.queryUser(userId, password);
    }

	@Override
	public String getSignInUrl() {
	
		log.info("get signIn url:{}" , userService) ;

        return null ;
	}
}