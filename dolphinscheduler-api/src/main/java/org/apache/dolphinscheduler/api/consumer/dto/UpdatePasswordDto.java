package org.apache.dolphinscheduler.api.consumer.dto;

/**
 * 密码dto
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class UpdatePasswordDto {

	private String accountId; // 用户id
	private String oldPassword;
	private String newPassword;
	private String confimPassword;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfimPassword() {
		return confimPassword;
	}

	public void setConfimPassword(String confimPassword) {
		this.confimPassword = confimPassword;
	}

}
