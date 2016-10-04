package com.ssh.xep.util;

import java.util.Iterator;

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
	 * 根据json重构bpmn文件
	 * 将ID转换为_jbpm...开头 组成合适的bpmn文件
	 * 
	 * @throws ParserConfigurationException
	 */
	public static Document json2XMLObject(int flowId, String name, JSONObject json)
			throws ParserConfigurationException {
		JSONArray obj = json.getJSONArray("Obj");
		JSONArray link = json.getJSONArray("Link");
		JSONArray gateway = json.getJSONArray("Gateway");
		MakeFlow flow = new MakeFlow(String.valueOf(flowId), name);
		Iterator<Object> it = obj.iterator();
		while (it.hasNext()) {
			JSONObject o = (JSONObject) it.next();
			flow.addTask(o.getString("id"), o.getString("name"), o.getString("tool-id"), o.getString("tool-name"));
		}

		it = link.iterator();
		while (it.hasNext()) {
			JSONObject o = (JSONObject) it.next();
			flow.addConnection(o.getString("from"), o.getString("to"));
		}

		it = gateway.iterator();
		while (it.hasNext()) {
			JSONObject o = (JSONObject) it.next();
			if (o.getString("gatewayDirection").equals("Diverging")) {
				flow.addDiverging(o.getString("id"));
			} else {
				flow.addConverging(o.getString("id"));
			}
		}
		return flow.getBpmn().getXML();
	}
}
