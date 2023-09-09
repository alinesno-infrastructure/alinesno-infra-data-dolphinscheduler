package org.apache.dolphinscheduler.api.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 安全过滤路径，判断哪些不需要过滤的
 * 
 * @author LuoAnDong
 * @since 2020年12月3日 下午10:01:04
 *
 */
@Component
@ConfigurationProperties(prefix = "alinesno.client.authorty")
public class HttpAuthortyProperties {

	private String id; // 应用id
	private String url = "http://alinesno-authority.admin.beta.linesno.com" ; // 此为默认值
	private String accesskey;
	private String secretkey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

}
