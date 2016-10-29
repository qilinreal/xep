package com.ssh.xep.util;

import java.util.Date;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JobJSON {
	private JSONObject json;

	public JobJSON() {
		json = new JSONObject();
	}

	public void init(String flowJSONStr) {
		JSONObject object = JSONObject.fromObject(flowJSONStr);
		init(object);
	}

	public void init(JSONObject json) {
		JSONArray Obj = json.getJSONArray("Obj");
		Iterator<Object> it = Obj.iterator();
		while (it.hasNext()) {
			JSONObject o = (JSONObject) it.next();
			if (o.getString("name").equals("start") || o.getString("name").equals("end")) {
				continue;
			}
			o.put("state", "wait");
//			o.put("bgTime", null);
//			o.put("edTime", null);
//			o.put("ret", null);
//			o.put("pid", null);
		}

		this.json = json;
	}

	public JSONObject getJSONObject() {
		return this.json;
	}

	public String getJSON() {
		return this.json.toString();
	}
}
