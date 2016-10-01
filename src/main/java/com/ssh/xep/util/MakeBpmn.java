package com.ssh.xep.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class MakeBpmn {
	private Document xml;
	private Set<String> exts;

	public MakeBpmn(String loadName) throws ParserConfigurationException {
		this(loadName, loadName + " name");
	}

	public MakeBpmn(String loadName, String name) throws ParserConfigurationException {
		this.exts = new HashSet<String>();

		xml = DocumentFactory.getInstance().createDocument();
		Element e = xml.addElement("definitions", "http://www.omg.org/spec/BPMN/20100524/MODEL");

		e.addAttribute("id", "Definition");
		e.addAttribute("targetNamespace", "http://www.jboss.org/drools");
		e.addAttribute("typeLanguage", "http://www.java.com/javaTypes");
		e.addAttribute("expressionLanguage", "http://www.mvel.org/2.0");
		e.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		e.addAttribute("xsi:schemaLocation", "http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd");
		e.addAttribute("xmlns:g", "http://www.jboss.org/drools/flow/gpd");
		e.addAttribute("xmlns:bpmndi", "http://www.omg.org/spec/BPMN/20100524/DI");
		e.addAttribute("xmlns:dc", "http://www.omg.org/spec/DD/20100524/DC");
		e.addAttribute("xmlns:di", "http://www.omg.org/spec/DD/20100524/DI");
		e.addAttribute("xmlns:tns", "http://www.jboss.org/drools");

		Element e1 = e.addElement("process");
		Element e2 = e.addElement("bpmndi:BPMNDiagram", "http://www.omg.org/spec/BPMN/20100524/DI");

		e1.addAttribute("processType", "Private");
		e1.addAttribute("isExecutable", "true");
		e1.addAttribute("id", loadName);
		e1.addAttribute("name", name);

		e1.addElement("extensionElements");
		Element eStart = e1.addElement("startEvent");
		eStart.addAttribute("id", "_1");
		eStart.addAttribute("isInterrupting", "true");

		Element eEnd = e1.addElement("endEvent");
		eEnd.addAttribute("id", "_3");
		eEnd.addElement("terminateEventDefinition");

		Element plane = e2.addElement("bpmndi:BPMNPlane", "http://www.omg.org/spec/BPMN/20100524/DI");
		plane.addAttribute("bpmnElement", loadName);
	}

	public void addTask(String taskId, File scriptFile) throws IOException {
		addTask(taskId, scriptFile, null);
	}

	/**
	 * @param taskId
	 *            任务ID
	 * @param scriptFile
	 *            脚本文件
	 * @param exts
	 *            引用包
	 * @throws IOException
	 */
	public void addTask(String taskId, File scriptFile, String exts) throws IOException {
		FileInputStream fis = new FileInputStream(scriptFile);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		fis.close();

		addTask(taskId, sb.toString(), exts);
	}

	public void addTask(String taskId, String script) {
		addTask(taskId, script, (String) null);
	}

	/**
	 * @param taskId
	 *            任务ID
	 * @param script
	 *            脚本
	 * @param exts
	 *            引用包
	 */
	public void addTask(String taskId, String script, String exts) {
		if (exts != null) {
			addTask(taskId, script, exts.split(";"));
		} else {
			addTask(taskId, script, (String[]) null);
		}
	}

	public void addTask(String taskId, String script, String[] exts) {
		if (exts != null) {
			Element e = xml.getRootElement();
			e = e.element("process").element("extensionElements");
			for (String s : exts) {
				char[] cs = s.toCharArray();
				int bg = 0;
				while (cs[bg] == ' ' || cs[bg] == '\r' || cs[bg] == '\n' || cs[bg] == '\t') {
					bg++;
				}
				int ed = cs.length - 1;
				while (cs[ed] == ' ' || cs[ed] == '\r' || cs[ed] == '\n' || cs[ed] == '\t') {
					ed--;
				}
				// 去除import
				if (bg + 7 >= ed) {
					continue;
				}
				// 去除import
				s = s.substring(bg + 7, ed + 1);
				if (this.exts.contains(s) == false) {
					this.exts.add(s);
					e.addElement("tns:import", "http://www.jboss.org/drools").addAttribute("name", s);
				}
			}
		}

		Element e = xml.getRootElement().element("process");
		String id = "_jbpm-unique-" + taskId;
		String name = taskId;
		String scriptFormat = "http://www.java.com/java";
		e = e.addElement("scriptTask");
		e.addAttribute("id", id);
		e.addAttribute("name", name);
		e.addAttribute("scriptFormat", scriptFormat);
		e.addElement("script").addText(script);
	}

	public void addConnection(String fromId, String toId) {
		String sourceRef = fromId;
		if(sourceRef.equals("_1") == false) {
			sourceRef = "_jbpm-unique-"+sourceRef;
		}
		String targetRef = toId;
		if(targetRef.equals("_3") == false) {
			targetRef = "_jbpm-unique-"+targetRef;
		}
		String id = sourceRef+"-"+targetRef;
		Element e = xml.getRootElement().element("process").addElement("sequenceFlow");
		e.addAttribute("id", id).addAttribute("sourceRef", sourceRef).addAttribute("targetRef", targetRef);
	}

	public String get() throws TransformerFactoryConfigurationError, TransformerException {
		return xml.asXML();
	}
}
