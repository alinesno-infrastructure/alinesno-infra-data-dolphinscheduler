package org.apache.dolphinscheduler.api.consumer.dto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 功能名： 【请填写功能名称】 数据表： manager_application
 * 
 * @author LuoAnDong ${authorEmail}
 * @date 2021-08-04 19:24:38
 */
public class ManagerApplicationDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	// fields
	/**
	 * "applicationDesc"
	 */
	private String applicationDesc;
	/**
	 * "applicationIcons"
	 */
	private String applicationIcons;
	/**
	 * "父类Id"
	 */
	private String pid;
	/**
	 * "applicationLink"
	 */
	private String applicationLink;
	/**
	 * "applicationCode"
	 */
	private String applicationCode;

	// getter and setter
	public String getApplicationDesc()

	{
		return this.applicationDesc;
	}

	public ManagerApplicationDto setApplicationDesc(String arg) {
		this.applicationDesc = arg;
		return this;
	}

	public String getApplicationIcons()

	{
		return this.applicationIcons;
	}

	public ManagerApplicationDto setApplicationIcons(String arg) {
		this.applicationIcons = arg;
		return this;
	}

	public String getPid()

	{
		return this.pid;
	}

	public ManagerApplicationDto setPid(String arg) {
		this.pid = arg;
		return this;
	}

	public String getApplicationLink()

	{
		return this.applicationLink;
	}

	public ManagerApplicationDto setApplicationLink(String arg) {
		this.applicationLink = arg;
		return this;
	}

	public String getApplicationCode()

	{
		return this.applicationCode;
	}

	public ManagerApplicationDto setApplicationCode(String arg) {
		this.applicationCode = arg;
		return this;
	}

	/**
	 * 快速从 dto 转换成 entity
	 * 
	 * @return dto 对应的 entity
	 */
	public ManagerApplicationDto mapToDto() {
		ObjectMapper toDtoMapper = new ObjectMapper();
		toDtoMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return toDtoMapper.convertValue(this, ManagerApplicationDto.class);
	}

	/**
	 * 快速从 entity 转换成 dto
	 * 
	 * @return dto 对应的 entity
	 */
	public ManagerApplicationDto fromDto(ManagerApplicationDto entity) {
		ObjectMapper fromDtoMapper = new ObjectMapper();
		fromDtoMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return fromDtoMapper.convertValue(entity, ManagerApplicationDto.class);
	}

}
