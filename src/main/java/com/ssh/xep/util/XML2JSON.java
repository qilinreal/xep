package com.ssh.xep.util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;

/**
 * {
 * 	'Obj':[{'name':'', 'id':''}],
 * 	'Link':[{'from':'', 'to':''}]
 * }
 */
public class XML2JSON {
	public static JSONObject xml2JSONObj(String xmlStr) throws DocumentException {
		JSONObject ret = new JSONObject();
		JSONArray obj = new JSONArray();
		JSONArray link = new JSONArray();
		
		Document xml = DocumentHelper.parseText(xmlStr);
		Element e = xml.getRootElement().element("process");
		List<Element> el = e.elements("scriptTask");
		for(Element n : el) {
			JSONObject t = new JSONObject();
			t.put("id", n.attributeValue("id").substring(13));
			t.put("name", n.attributeValue("data-name"));
			obj.add(t);
		}
		
		el = e.elements("sequenceFlow");
		for(Element n : el) {
			JSONObject t = new JSONObject();
			String str = n.attributeValue("sourceRef");
			if(str.startsWith("_j")) {
				str = str.substring(13);
			}
			t.put("from", str);
			str = n.attributeValue("targetRef");
			if(str.startsWith("_j")) {
				str = str.substring(13);
			}
			t.put("to", str);
			link.add(t);
		}
		
		ret.put("Obj", obj);
		ret.put("Link", link);
		return ret;
	}
	
	public static String xml2JSON(String xmlStr) throws DocumentException {
		return xml2JSONObj(xmlStr).toString();
	}
}
