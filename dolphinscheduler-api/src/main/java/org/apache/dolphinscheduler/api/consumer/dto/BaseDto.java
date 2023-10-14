package org.apache.dolphinscheduler.api.consumer.dto;

import java.util.Date;

/**
 * 实体对象基类,定义基本属性
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class BaseDto {

	private String id; // 唯一ID号

	private String fieldProp; // 字段属性

	/* 更新时间 用户可以点击更新，保存最新更新的时间 **/
	private Date addTime; // 添加时间

	private Date deleteTime; // 删除时间

	private Date updateTime; // 更新时间

	private int hasDelete = 0; // 是否删除(1删除|0正常|null正常)

	private int hasStatus; // = HasStatusEnums.LEGAL.value ; // 状态(0启用|1禁用)

	private String deleteManager; // 删除的人

	//////////////////////////////// 数据权限规划 _start ///////////////////////
	private String applicationId; // 所属应用 应用权限: 只能看到所属应用的权限【默认】

	private String applicationName; // 应用名称，唯一性，用于做应用标识【最好与springboot的applicaiotn.name对应】

	private String tenantId = "0"; // 所属租户 , 租户权限

	private String operatorId; // 操作员 用户权限: 只能看到自己操作的数据

	private String lastUpdateOperatorId; // 最后更新操作员 用户权限: 只能看到自己操作的数据

	private String fieldId; // 字段权限：部分字段权限无法加密或者不显示，或者大于某个值

	private String departmentId; // 部门权限: 只能看到自己所在部门的数据
	/////////////////////////////// 数据权限规划 _end ///////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFieldProp() {
		return fieldProp;
	}

	public void setFieldProp(String fieldProp) {
		this.fieldProp = fieldProp;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getHasDelete() {
		return hasDelete;
	}

	public void setHasDelete(int hasDelete) {
		this.hasDelete = hasDelete;
	}

	public int getHasStatus() {
		return hasStatus;
	}

	public void setHasStatus(int hasStatus) {
		this.hasStatus = hasStatus;
	}

	public String getDeleteManager() {
		return deleteManager;
	}

	public void setDeleteManager(String deleteManager) {
		this.deleteManager = deleteManager;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getLastUpdateOperatorId() {
		return lastUpdateOperatorId;
	}

	public void setLastUpdateOperatorId(String lastUpdateOperatorId) {
		this.lastUpdateOperatorId = lastUpdateOperatorId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
