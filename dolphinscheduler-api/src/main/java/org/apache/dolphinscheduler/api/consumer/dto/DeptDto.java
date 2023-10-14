package org.apache.dolphinscheduler.api.consumer.dto;

/**
 * 组织机构
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
public class DeptDto {

	private String deptId; // ": 3,
	private String deptName; // ": "xxx行政部",
	private String parentDeptId; // ": 2

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

}
