package org.apache.dolphinscheduler.api.consumer;

import java.util.Collections;
import java.util.List;

import org.apache.dolphinscheduler.api.consumer.utils.AjaxResult;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 基础的引用配置对象
 * @author luoxiaodong
 * @version 1.0.0
 */
public class BaseConsumer {

	protected <T> List<T> toEntityList(String json, Class<T> cls) {
		AjaxResult ajaxResult = JSONObject.parseObject(json, AjaxResult.class);

		Object dataJson = ajaxResult.get("data");
		if (dataJson != null) {
			List<T> entity = JSONArray.parseArray(dataJson.toString(), cls);
			return entity;
		} else {
			return Collections.emptyList();
		}

	}

	protected <T> T toEntity(String json, Class<T> cls) {
		AjaxResult ajaxResult = JSONObject.parseObject(json, AjaxResult.class);
		return this.toEntity(ajaxResult, cls);
	}
	protected <T> T toEntity(AjaxResult ajaxResult, Class<T> cls) {

		Object jsonObject = ajaxResult.get("data");
		if (jsonObject != null) {
			return JSONObject.parseObject(jsonObject.toString(), cls);
		} else {
			return null;
		}
	}

}
