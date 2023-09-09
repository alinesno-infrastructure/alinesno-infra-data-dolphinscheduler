package org.apache.dolphinscheduler.api.consumer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Http请求服务
 * 
 * @author LuoAnDong
 * @since 2021年8月4日 下午7:13:25
 */
@Component
public class HttpConsumer {

	private static final Logger log = LoggerFactory.getLogger(HttpConsumer.class) ; 
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 请求头信息
	 */
	@Value("${alinesno.client.gate-token:}") 
	private String gatewayAuthToken ; 

	/**
	 * get请求
	 *
	 * @param url       请求地址
	 * @param headerMap 头部信息
	 * @param resp      响应结果类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object get(String url, Map<String, String> headerMap, Class<?> resp) {
		HttpHeaders httpHeaders = new HttpHeaders();

		if (headerMap != null) {
			for (Map.Entry<String, String> stringStringEntry : headerMap.entrySet()) {
				httpHeaders.add(stringStringEntry.getKey(), stringStringEntry.getValue());
			}
		}
	
		if(gatewayAuthToken != null) {
			httpHeaders.add(Constants.X_AUTH_TOKEN , gatewayAuthToken) ; 
		}

		@SuppressWarnings("rawtypes")
		HttpEntity httpEntity = new HttpEntity(httpHeaders);
		ResponseEntity<?> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, resp);

		log.debug("url = {} , get result = {}" , url ,  result);
		
		return result.getBody();
	}

	/**
	 * post 请求
	 *
	 * @param url        请求地址
	 * @param headerMap  头信息
	 * @param jsonObject 请求参数 JSON
	 * @return JSONObject
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String post(String url, Map<String, String> headerMap, Object jsonObject) {

		HttpHeaders httpHeaders = new HttpHeaders();

		if (headerMap != null) {
			for (Map.Entry<String, String> stringStringEntry : headerMap.entrySet()) {
				httpHeaders.add(stringStringEntry.getKey(), stringStringEntry.getValue());
			}
		}
	
		if(gatewayAuthToken != null) {
			httpHeaders.add(Constants.X_AUTH_TOKEN , gatewayAuthToken) ; 
		}

		HttpEntity httpEntity = new HttpEntity(jsonObject, httpHeaders);
		String result = restTemplate.postForObject(url, httpEntity, String.class);
		
		log.debug("url = {} , post result = {}" , url , result);

		return result;
	}

	/**
	 * RestTemplate发送POST请求之formData形式
	 * 
	 * @return
	 */
	public String postFormData(String url, Map<String, Object> paramsMap) {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

		// 接口参数
		if (paramsMap != null) {
			for (String k : paramsMap.keySet()) {
				map.add(k, paramsMap.get(k));
			}
		}
		
		if(gatewayAuthToken != null) {
			headers.add(Constants.X_AUTH_TOKEN , gatewayAuthToken) ; 
		}

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, Object>> param = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, param, String.class);

		String body = response.getBody();
		return body;
	}
}