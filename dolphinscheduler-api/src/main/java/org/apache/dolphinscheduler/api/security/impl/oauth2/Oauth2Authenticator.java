/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.api.security.impl.oauth2;

import java.io.IOException;

import org.apache.dolphinscheduler.api.consumer.AuthorityPlatformClient;
import org.apache.dolphinscheduler.api.consumer.dto.ManagerAccountDto;
import org.apache.dolphinscheduler.api.security.impl.AbstractSsoAuthenticator;
import org.apache.dolphinscheduler.api.service.UsersService;
import org.apache.dolphinscheduler.common.enums.UserType;
import org.apache.dolphinscheduler.dao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Oauth2Authenticator extends AbstractSsoAuthenticator {

	private static final Logger log = LoggerFactory.getLogger(Oauth2Authenticator.class) ; 
	
    @Value("${alinesno.uua.authorization-server:null}")
    private String authorizationServer;
   
    @Autowired
    private ClientProperties clientProperties ; 

    @Autowired
    private IUaaClientActionStrategy uaaClientActionStrategy ; 
    
    @Autowired
    private AuthorityPlatformClient authorityPlatformClient ; 
	
    @Autowired
    private UsersService userService;

    @Override
    public User login(String userId, String password, String extra) {
        return userService.queryUser(userId, password);
    }
//    public User login(String userId, String code, String extra) {
//
//    	log.info("oauth2 loginUser , userId:{} , code:{} , extra:{} " , userId , code , extra);
//    	User user = null;
//
//    	try {
//    		TokenBody tokenBody = uaaClientActionStrategy.handleExchangeToken(userId);
//
//    		if (tokenBody.getUserName() != null) {
//                //check if user exist
//                user = userService.getUserByUserName(tokenBody.getUserName());
//                if (user == null) {
//
//                	ManagerAccountDto dto = authorityPlatformClient.findById(tokenBody.getUserId()) ;
//
//                	// 创建资源关联用户
//                    user = userService.createUser(UserType.GENERAL_USER , dto.getLoginName() , dto.getEmail(), dto.getName() , dto.getId() , dto.getPhone() , 1) ;
//
//                    // user = userService.createUser(UserType.GENERAL_USER, tokenBody.getUserName(), tokenBody.getUserName());
//                }
//            }
//
//
//		} catch (IOException e) {
//			log.debug("解决用户信息异常:{}" , e.getMessage());
//		}
//
//        return user ;
//    }

	@Override
	public String getSignInUrl() {
	
		log.info("get signIn url:{} , clientProperties:{}" , userService , clientProperties);
        
		// 1. 获取到code
		LoginEndPoint loginEndPoint = uaaClientActionStrategy.getLoginEndPoint() ; 
		
		return loginEndPoint.getLoginEndPoint();
	}
}
