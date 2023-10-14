package org.apache.dolphinscheduler.api.consumer.dto;

/**
 * 企业信息管理
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class EnterpriseDto extends BaseDto {

	private String name; // 企业名称
	private String logo; // 企业logo
	private String enDesc; // 企业描述
	private String domainName; // 企业域名
	private String creditCode; // 企业信用代码

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEnDesc() {
		return enDesc;
	}

	public void setEnDesc(String enDesc) {
		this.enDesc = enDesc;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Override
	public String toString() {
		return "EnterpriseDto [name=" + name + ", logo=" + logo + ", enDesc=" + enDesc + ", domainName=" + domainName
				+ ", creditCode=" + creditCode + "]";
	}

}
