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

package org.apache.dolphinscheduler.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dolphinscheduler.api.aspect.AccessLogAnnotation;
import org.apache.dolphinscheduler.api.exceptions.ApiException;
import org.apache.dolphinscheduler.api.utils.Result;
import org.apache.dolphinscheduler.common.Constants;
import org.apache.dolphinscheduler.dao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

import static org.apache.dolphinscheduler.api.enums.Status.SIGN_OUT_ERROR;

/**
 * login controller
 */
@Api(tags = "DASHBOARD_TAG")
@RestController
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(DashboardController.class) ; 

    /**
     * sign out
     *
     * @param loginUser login user
     * @param request request
     * @return sign out result
     */
    @ApiOperation(value = "enteripriseInfo", notes = "ENTERIPRISE_INFO")
    @GetMapping(value = "/enteripriseInfo")
    @ApiException(SIGN_OUT_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = {"loginUser", "request"})
    public Result enteripriseInfo(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser, HttpServletRequest request) {
    
    	log.info("loginUser = {}" , loginUser) ; 
    	
        return success();
    }
}
