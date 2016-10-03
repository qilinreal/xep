package com.ssh.xep.util;

import javax.xml.parsers.ParserConfigurationException;

public class MakeFlow {
	private MakeBpmn bpmn;
	public MakeFlow(String loadName, String name) throws ParserConfigurationException {
		bpmn = new MakeBpmn(loadName, name);
	}
}
