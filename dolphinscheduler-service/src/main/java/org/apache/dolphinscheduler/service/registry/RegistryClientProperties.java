package org.apache.dolphinscheduler.service.registry;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性
 * 
 * @author LuoAnDong
 * @since 2023年1月22日 上午6:23:43
 */
@Configuration
@ConfigurationProperties(prefix = "registry")
public class RegistryClientProperties {

	private String pluginName;
	private String servers;
	private String namespace;
	private int baseSleepTimeMs;

	private int maxSleepMs; // =300
	private int maxRetries; // =5
	private int sessionTimeoutMs; // =30000
	private int connectionTimeoutMs; // =7500
	private int blockUntilCconnectedWwait; // =600
	private String digest;

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public int getBaseSleepTimeMs() {
		return baseSleepTimeMs;
	}

	public void setBaseSleepTimeMs(int baseSleepTimeMs) {
		this.baseSleepTimeMs = baseSleepTimeMs;
	}

	public int getMaxSleepMs() {
		return maxSleepMs;
	}

	public void setMaxSleepMs(int maxSleepMs) {
		this.maxSleepMs = maxSleepMs;
	}

	public int getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

	public int getBlockUntilCconnectedWwait() {
		return blockUntilCconnectedWwait;
	}

	public void setBlockUntilCconnectedWwait(int blockUntilCconnectedWwait) {
		this.blockUntilCconnectedWwait = blockUntilCconnectedWwait;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

}
