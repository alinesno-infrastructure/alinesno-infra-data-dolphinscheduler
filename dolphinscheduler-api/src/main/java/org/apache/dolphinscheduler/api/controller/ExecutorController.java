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

import io.swagger.annotations.*;
import org.apache.dolphinscheduler.api.aspect.AccessLogAnnotation;
import org.apache.dolphinscheduler.api.enums.ExecuteType;
import org.apache.dolphinscheduler.api.enums.Status;
import org.apache.dolphinscheduler.api.exceptions.ApiException;
import org.apache.dolphinscheduler.api.service.ExecutorService;
import org.apache.dolphinscheduler.api.utils.Result;
import org.apache.dolphinscheduler.common.Constants;
import org.apache.dolphinscheduler.common.enums.*;
import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.dolphinscheduler.api.enums.Status.*;

/**
 * executor controller
 */
@Api(tags = "EXECUTOR_TAG")
@RestController
@RequestMapping("projects/{projectCode}/executors")
public class ExecutorController extends BaseController {

    @Autowired
    private ExecutorService execService;

    /**
     * execute process instance
     *
     * @param loginUser login user
     * @param projectCode project code
     * @param processDefinitionCode process definition code
     * @param scheduleTime schedule time
     * @param failureStrategy failure strategy
     * @param startNodeList start nodes list
     * @param taskDependType task depend type
     * @param execType execute type
     * @param warningType warning type
     * @param warningGroupId warning group id
     * @param runMode run mode
     * @param processInstancePriority process instance priority
     * @param workerGroup worker group
     * @param timeout timeout
     * @param expectedParallelismNumber the expected parallelism number when execute complement in parallel mode
     * @return start process result code
     */
    @ApiOperation(value = "startProcessInstance", notes = "RUN_PROCESS_INSTANCE_NOTES")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "processDefinitionCode", value = "PROCESS_DEFINITION_CODE", required = true, dataType = "Long", example = "100"),
        @ApiImplicitParam(name = "scheduleTime", value = "SCHEDULE_TIME", required = true, dataType = "String"),
        @ApiImplicitParam(name = "failureStrategy", value = "FAILURE_STRATEGY", required = true, dataType = "FailureStrategy"),
        @ApiImplicitParam(name = "startNodeList", value = "START_NODE_LIST", dataType = "String"),
        @ApiImplicitParam(name = "taskDependType", value = "TASK_DEPEND_TYPE", dataType = "TaskDependType"),
        @ApiImplicitParam(name = "execType", value = "COMMAND_TYPE", dataType = "CommandType"),
        @ApiImplicitParam(name = "warningType", value = "WARNING_TYPE", required = true, dataType = "WarningType"),
        @ApiImplicitParam(name = "warningGroupId", value = "WARNING_GROUP_ID", required = true, dataType = "Int", example = "100"),
        @ApiImplicitParam(name = "runMode", value = "RUN_MODE", dataType = "RunMode"),
        @ApiImplicitParam(name = "processInstancePriority", value = "PROCESS_INSTANCE_PRIORITY", required = true, dataType = "Priority"),
        @ApiImplicitParam(name = "workerGroup", value = "WORKER_GROUP", dataType = "String", example = "default"),
        @ApiImplicitParam(name = "environmentCode", value = "ENVIRONMENT_CODE", dataType = "Long", example = "-1"),
        @ApiImplicitParam(name = "timeout", value = "TIMEOUT", dataType = "Int", example = "100"),
        @ApiImplicitParam(name = "expectedParallelismNumber", value = "EXPECTED_PARALLELISM_NUMBER", dataType = "Int", example = "8")
    })
    @PostMapping(value = "start-process-instance")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(START_PROCESS_INSTANCE_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = "loginUser")
    public Result startProcessInstance(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                       @ApiParam(name = "projectCode", value = "PROJECT_CODE", required = true) @PathVariable long projectCode,
                                       @RequestParam(value = "processDefinitionCode") long processDefinitionCode,
                                       @RequestParam(value = "scheduleTime", required = false) String scheduleTime,
                                       @RequestParam(value = "failureStrategy", required = true) FailureStrategy failureStrategy,
                                       @RequestParam(value = "startNodeList", required = false) String startNodeList,
                                       @RequestParam(value = "taskDependType", required = false) TaskDependType taskDependType,
                                       @RequestParam(value = "execType", required = false) CommandType execType,
                                       @RequestParam(value = "warningType", required = true) WarningType warningType,
                                       @RequestParam(value = "warningGroupId", required = false) int warningGroupId,
                                       @RequestParam(value = "runMode", required = false) RunMode runMode,
                                       @RequestParam(value = "processInstancePriority", required = false) Priority processInstancePriority,
                                       @RequestParam(value = "workerGroup", required = false, defaultValue = "default") String workerGroup,
                                       @RequestParam(value = "environmentCode", required = false, defaultValue = "-1") Long environmentCode,
                                       @RequestParam(value = "timeout", required = false) Integer timeout,
                                       @RequestParam(value = "startParams", required = false) String startParams,
                                       @RequestParam(value = "expectedParallelismNumber", required = false) Integer expectedParallelismNumber,
                                       @RequestParam(value = "dryRun", defaultValue = "0", required = false) int dryRun) {

        if (timeout == null) {
            timeout = Constants.MAX_TASK_TIMEOUT;
        }
        Map<String, String> startParamMap = null;
        if (startParams != null) {
            startParamMap = JSONUtils.toMap(startParams);
        }
        Map<String, Object> result = execService.execProcessInstance(loginUser, projectCode, processDefinitionCode, scheduleTime, execType, failureStrategy,
            startNodeList, taskDependType, warningType, warningGroupId, runMode, processInstancePriority, workerGroup, environmentCode,timeout, startParamMap, expectedParallelismNumber, dryRun);
        return returnDataList(result);
    }

    /**
     * batch execute process instance
     * If any processDefinitionCode cannot be found, the failure information is returned and the status is set to
     * failed. The successful task will run normally and will not stop
     * 
     * @param loginUser login user
     * @param projectCode project code
     * @param processDefinitionCodes process definition codes
     * @param scheduleTime schedule time
     * @param failureStrategy failure strategy
     * @param startNodeList start nodes list
     * @param taskDependType task depend type
     * @param execType execute type
     * @param warningType warning type
     * @param warningGroupId warning group id
     * @param runMode run mode
     * @param processInstancePriority process instance priority
     * @param workerGroup worker group
     * @param timeout timeout
     * @param expectedParallelismNumber the expected parallelism number when execute complement in parallel mode
     * @return start process result code
     */
    @ApiOperation(value = "batchStartProcessInstance", notes = "BATCH_RUN_PROCESS_INSTANCE_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionCodes", value = "PROCESS_DEFINITION_CODES", required = true, dataType = "String", example = "1,2,3"),
            @ApiImplicitParam(name = "scheduleTime", value = "SCHEDULE_TIME", required = true, dataType = "String"),
            @ApiImplicitParam(name = "failureStrategy", value = "FAILURE_STRATEGY", required = true, dataType = "FailureStrategy"),
            @ApiImplicitParam(name = "startNodeList", value = "START_NODE_LIST", dataType = "String"),
            @ApiImplicitParam(name = "taskDependType", value = "TASK_DEPEND_TYPE", dataType = "TaskDependType"),
            @ApiImplicitParam(name = "execType", value = "COMMAND_TYPE", dataType = "CommandType"),
            @ApiImplicitParam(name = "warningType", value = "WARNING_TYPE", required = true, dataType = "WarningType"),
            @ApiImplicitParam(name = "warningGroupId", value = "WARNING_GROUP_ID", required = true, dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "runMode", value = "RUN_MODE", dataType = "RunMode"),
            @ApiImplicitParam(name = "processInstancePriority", value = "PROCESS_INSTANCE_PRIORITY", required = true, dataType = "Priority"),
            @ApiImplicitParam(name = "workerGroup", value = "WORKER_GROUP", dataType = "String", example = "default"),
            @ApiImplicitParam(name = "environmentCode", value = "ENVIRONMENT_CODE", dataType = "Long", example = "-1"),
            @ApiImplicitParam(name = "timeout", value = "TIMEOUT", dataType = "Int", example = "100"),
            @ApiImplicitParam(name = "expectedParallelismNumber", value = "EXPECTED_PARALLELISM_NUMBER", dataType = "Int", example = "8")
    })
    @PostMapping(value = "batch-start-process-instance")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(START_PROCESS_INSTANCE_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = "loginUser")
    public Result batchStartProcessInstance(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                       @ApiParam(name = "projectCode", value = "PROJECT_CODE", required = true) @PathVariable long projectCode,
                                       @RequestParam(value = "processDefinitionCodes") String processDefinitionCodes,
                                       @RequestParam(value = "scheduleTime", required = false) String scheduleTime,
                                       @RequestParam(value = "failureStrategy", required = true) FailureStrategy failureStrategy,
                                       @RequestParam(value = "startNodeList", required = false) String startNodeList,
                                       @RequestParam(value = "taskDependType", required = false) TaskDependType taskDependType,
                                       @RequestParam(value = "execType", required = false) CommandType execType,
                                       @RequestParam(value = "warningType", required = true) WarningType warningType,
                                       @RequestParam(value = "warningGroupId", required = false) int warningGroupId,
                                       @RequestParam(value = "runMode", required = false) RunMode runMode,
                                       @RequestParam(value = "processInstancePriority", required = false) Priority processInstancePriority,
                                       @RequestParam(value = "workerGroup", required = false, defaultValue = "default") String workerGroup,
                                       @RequestParam(value = "environmentCode", required = false, defaultValue = "-1") Long environmentCode,
                                       @RequestParam(value = "timeout", required = false) Integer timeout,
                                       @RequestParam(value = "startParams", required = false) String startParams,
                                       @RequestParam(value = "expectedParallelismNumber", required = false) Integer expectedParallelismNumber,
                                       @RequestParam(value = "dryRun", defaultValue = "0", required = false) int dryRun) {

        if (timeout == null) {
            timeout = Constants.MAX_TASK_TIMEOUT;
        }

        Map<String, String> startParamMap = null;
        if (startParams != null) {
            startParamMap = JSONUtils.toMap(startParams);
        }

        Map<String, Object> result = new HashMap<>();
        List<String> processDefinitionCodeArray = Arrays.asList(processDefinitionCodes.split(Constants.COMMA));
        List<String> startFailedProcessDefinitionCodeList = new ArrayList<>();

        processDefinitionCodeArray = processDefinitionCodeArray.stream().distinct().collect(Collectors.toList());

        for (String strProcessDefinitionCode : processDefinitionCodeArray) {
            long processDefinitionCode = Long.parseLong(strProcessDefinitionCode);
            result = execService.execProcessInstance(loginUser, projectCode, processDefinitionCode, scheduleTime, execType, failureStrategy,
                    startNodeList, taskDependType, warningType, warningGroupId, runMode, processInstancePriority, workerGroup, environmentCode, timeout, startParamMap, expectedParallelismNumber, dryRun);

            if (!Status.SUCCESS.equals(result.get(Constants.STATUS))) {
                startFailedProcessDefinitionCodeList.add(String.valueOf(processDefinitionCode));
            }
        }

        if (!startFailedProcessDefinitionCodeList.isEmpty()) {
            putMsg(result, Status.BATCH_START_PROCESS_INSTANCE_ERROR, String.join(Constants.COMMA, startFailedProcessDefinitionCodeList));
        }

        return returnDataList(result);
    }

    /**
     * do action to process instance：pause, stop, repeat, recover from pause, recover from stop
     *
     * @param loginUser login user
     * @param projectCode project code
     * @param processInstanceId process instance id
     * @param executeType execute type
     * @return execute result code
     */
    @ApiOperation(value = "execute", notes = "EXECUTE_ACTION_TO_PROCESS_INSTANCE_NOTES")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "processInstanceId", value = "PROCESS_INSTANCE_ID", required = true, dataType = "Int", example = "100"),
        @ApiImplicitParam(name = "executeType", value = "EXECUTE_TYPE", required = true, dataType = "ExecuteType")
    })
    @PostMapping(value = "/execute")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(EXECUTE_PROCESS_INSTANCE_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = "loginUser")
    public Result execute(@ApiIgnore @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                          @ApiParam(name = "projectCode", value = "PROJECT_CODE", required = true) @PathVariable long projectCode,
                          @RequestParam("processInstanceId") Integer processInstanceId,
                          @RequestParam("executeType") ExecuteType executeType
    ) {
        Map<String, Object> result = execService.execute(loginUser, projectCode, processInstanceId, executeType);
        return returnDataList(result);
    }

    /**
     * check process definition and all of the son process definitions is on line.
     *
     * @param processDefinitionCode process definition code
     * @return check result code
     */
    @ApiOperation(value = "startCheckProcessDefinition", notes = "START_CHECK_PROCESS_DEFINITION_NOTES")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "processDefinitionCode", value = "PROCESS_DEFINITION_CODE", required = true, dataType = "Long", example = "100")
    })
    @PostMapping(value = "/start-check")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(CHECK_PROCESS_DEFINITION_ERROR)
    @AccessLogAnnotation(ignoreRequestArgs = "loginUser")
    public Result startCheckProcessDefinition(@RequestParam(value = "processDefinitionCode") long processDefinitionCode) {
        Map<String, Object> result = execService.startCheckByProcessDefinedCode(processDefinitionCode);
        return returnDataList(result);
    }
}
