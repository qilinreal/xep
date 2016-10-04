package com.ssh.xep.util;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSON2XML {
	public static String json2XML(int flowId, String name, String jsonStr) throws ParserConfigurationException {
		JSONObject json = JSONObject.fromObject(jsonStr);
		return json2XML(flowId, name, json);
	}

	public static Document json2XMLObject(int flowId, String name, String jsonStr) throws ParserConfigurationException {
		JSONObject json = JSONObject.fromObject(jsonStr);
		return json2XMLObject(flowId, name, json);
	}

	public static String json2XML(int flowId, String name, JSONObject json) throws ParserConfigurationException {
		return json2XMLObject(flowId, name, json).asXML();
	}

	/**
	 * 将ID转换为_jbpm...开头
	 * 组成合适的bpmn文件
	 * @throws ParserConfigurationException 
	 */
	public static Document json2XMLObject(int flowId, String name, JSONObject json) throws ParserConfigurationException {
		JSONArray obj = json.getJSONArray("Obj");
		JSONArray link = json.getJSONArray("Link");
		MakeFlow flow = new MakeFlow(String.valueOf(flowId), name);
		return null;
	}
}
