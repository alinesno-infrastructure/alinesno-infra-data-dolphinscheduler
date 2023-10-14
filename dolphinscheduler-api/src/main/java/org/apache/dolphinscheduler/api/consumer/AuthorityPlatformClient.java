package org.apache.dolphinscheduler.api.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.dolphinscheduler.api.consumer.dto.DeptDto;
import org.apache.dolphinscheduler.api.consumer.dto.EnterpriseDto;
import org.apache.dolphinscheduler.api.consumer.dto.EnterpriseThemeDto;
import org.apache.dolphinscheduler.api.consumer.dto.FieldDto;
import org.apache.dolphinscheduler.api.consumer.dto.ManagerAccountDto;
import org.apache.dolphinscheduler.api.consumer.dto.ManagerApplicationDto;
import org.apache.dolphinscheduler.api.consumer.dto.ManagerCodeDto;
import org.apache.dolphinscheduler.api.consumer.dto.UpdatePasswordDto;
import org.apache.dolphinscheduler.api.consumer.utils.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

/**
 * 对外权限第三方接口调用
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Component
public class AuthorityPlatformClient extends BaseConsumer {

	private static final Logger log = LoggerFactory.getLogger(AuthorityPlatformClient.class);

	private static final String URL_PREFIX = "/api/alinesno/cloud/authority/platform/";

	@Autowired
	private HttpAuthortyProperties httpAuthortyProperties;

	@Autowired
	private HttpConsumer httpConsumer;

	/**
	 * 获取应用登陆主题信息
	 * @return
	 */
	public EnterpriseThemeDto getLoginTheme(String springApplication) {

		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "EnterpriseTheme/getLoginTheme?applicationCode=" + springApplication);

		String result = (String) httpConsumer.get(url, null, String.class);
		log.debug("result = {}", result);

		return toEntity(result, EnterpriseThemeDto.class);
	}


	/**
	 * 通过用户信息获取企业信息，比如标题，主题色等
	 * @return
	 */
	public EnterpriseDto getEnterpriseByUserId(String userId , String springApplication) {
		
		Assert.hasLength(userId , "用户Id为空.");
		
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "getByUserId?userId=" + userId + "&applicationCode=" + springApplication);

		String result = (String) httpConsumer.get(url, null, String.class);
		log.debug("result = {}", result);

		return toEntity(result, EnterpriseDto.class);
	}

	/**
	 * 通过代码类型查询代码
	 *
	 * @return
	 */
	public List<ManagerCodeDto> codeListByType(String codeType) {

		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerCode/codeListByType?type=" + codeType);

		String result = (String) httpConsumer.get(url, null, String.class);
		log.debug("result = {}", result);

		return toEntityList(result, ManagerCodeDto.class);
	}

	/**
	 * 通过代码类型查询代码
	 *
	 * @return
	 */
	public List<ManagerCodeDto> codeListByType(String codeType,String applicationId) {

		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerCode/codeListByType?type=" + codeType + "&applicationId=" + applicationId);

		String result = (String) httpConsumer.get(url, null, String.class);
		log.debug("result = {}", result);

		return toEntityList(result, ManagerCodeDto.class);
	}

	/**
	 * 查询账户
	 *
	 * @param ids
	 * @return
	 */
	public List<ManagerAccountDto> findAccountEntityByIds(List<String> ids) {

		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerAccount/findIds");

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		String result = httpConsumer.post(url, headerMap, JSONObject.toJSON(ids));
		return toEntityList(result, ManagerAccountDto.class);
	}

//	/**
//	 * 查询租户
//	 *
//	 * @param ids
//	 * @return
//	 */
//	public List<ManagerTenantEntity> findTenantEntityByIds(List<String> ids) {
//		String url = httpAuthortyProperties.getUrl();
//		url += (URL_PREFIX + "ManagerTenant/findIds");
//
//		Map<String, String> headerMap = new HashMap<String, String>();
//		headerMap.put("Content-Type", "application/json");
//
//		String result = httpConsumer.post(url, headerMap, JSONObject.toJSON(ids));
//		return toEntityList(result, ManagerTenantEntity.class);
//	}

	/**
	 * 通过ids查询应用
	 *
	 * @param ids
	 * @return
	 */
	public List<ManagerApplicationDto> findApplicationEntityByIds(List<String> ids) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerApplication/findIds");

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		String result = httpConsumer.post(url, headerMap, JSONObject.toJSON(ids));
		return toEntityList(result, ManagerApplicationDto.class);
	}

	/**
	 * 代码查询
	 *
	 * @param object
	 * @param object2
	 * @return
	 */
	public ManagerCodeDto codeByType(Object codeType, Object codeValue) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerCode/codeByType?type=" + codeType + "&code=" + codeValue);

		String result = (String) httpConsumer.get(url, null, String.class);
		log.debug("result = {}", result);

		return toEntity(result, ManagerCodeDto.class);
	}

	/**
	 * 通过用户实体查询用户信息
	 *
	 * @param springApplication
	 * @return
	 */
	public ManagerApplicationDto findEntityByApplicationCode(String springApplication) {

		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerApplication/findEntityByApplicationCode?code=" + springApplication);

		String result = (String) httpConsumer.get(url, null, String.class);
		return toEntity(result, ManagerApplicationDto.class);
	}

//	/**
//	 * 查询用户菜单
//	 *
//	 * @param resourceParent
//	 * @param applicationId
//	 * @param userId
//	 * @return
//	 */
//	public ManagerResourceEntity findMenusByApplicationAndAccount(String resourceParent, String applicationId,
//			String userId) {
//		String url = httpAuthortyProperties.getUrl();
//		url += (URL_PREFIX + "ManagerResource/findMenusByApplicationAndAccount");
//		url += "?resourceParent=" + resourceParent;
//		url += "&applicationId=" + applicationId;
//		url += "&userId=" + userId;
//
//		String result = (String) httpConsumer.get(url, null, String.class);
//		return toEntity(result, ManagerResourceEntity.class);
//	}

	/**
	 * 查询应用部门
	 *
	 * @param id
	 * @return
	 */
	public List<DeptDto> findOrg(String applicationId, String parentDeptId) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerDepartment/findDepByApplication" + "?applicationId=" + applicationId
				+ "&parentDeptId=" + parentDeptId);

		String result = (String) httpConsumer.get(url, null, String.class);
		return toEntityList(result, DeptDto.class);
	}

	/**
	 * 通过id查询用户
	 *
	 * @param id
	 * @return
	 */
	public ManagerAccountDto findById(String id) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "findById?id=" + id);

		String result = (String) httpConsumer.get(url, null, String.class);
		return toEntity(result, ManagerAccountDto.class);
	}

	/**
	 * 通过用户名查询用户
	 *
	 * @param loginName
	 * @return
	 */
	public ManagerAccountDto findByLoginName(String loginName) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "ManagerAccount/findByLoginName?loginName=" + loginName);

		String result = (String) httpConsumer.get(url, null, String.class);
		return toEntity(result, ManagerAccountDto.class);
	}

//	/**
//	 * 部门代码查询
//	 * 
//	 * @param ids
//	 * @return
//	 */
//	public List<ManagerDepartmentEntity> findDepartmentByIds(List<String> ids) {
//		String url = httpAuthortyProperties.getUrl();
//		url += (URL_PREFIX + "ManagerDepartment/findIds");
//
//		Map<String, String> headerMap = new HashMap<String, String>();
//		headerMap.put("Content-Type", "application/json");
//
//		String result = httpConsumer.post(url, headerMap, JSONObject.toJSON(ids));
//		return toEntityList(result, ManagerDepartmentEntity.class);
//	}


	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public AjaxResult updateManagerAccount(ManagerAccountDto user) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "updateManagerAccount");

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		String result = httpConsumer.post(url, headerMap, JSONObject.toJSON(user));
		return JSONObject.parseObject(result, AjaxResult.class);
	}

	/**
	 * 更新用户密码
	 * @param pwdDto
	 * @return
	 */
	public AjaxResult updatePwd(UpdatePasswordDto pwdDto) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "updatePwd");

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		String result = httpConsumer.post(url, headerMap, JSONObject.toJSON(pwdDto));
		return JSONObject.parseObject(result, AjaxResult.class);
	}


	public AjaxResult updateAvator(String avatorId, String accountId) {
		String url = httpAuthortyProperties.getUrl();
		url += (URL_PREFIX + "updateAvator");

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		FieldDto fieldDto = new FieldDto() ; 
		fieldDto.setId(accountId) ; 
		fieldDto.setValue(avatorId) ; 
		fieldDto.setField("avatorPath") ; 

		String result = httpConsumer.post(url, headerMap, JSONObject.toJSON(fieldDto));
		return JSONObject.parseObject(result, AjaxResult.class);
	}

}
