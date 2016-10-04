package com.ssh.xep;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import com.ssh.xep.util.MakeBpmn;
import com.ssh.xep.util.XML2JSON;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException, UnsupportedEncodingException, DocumentException {
		Scanner sc = new Scanner(System.in);
		String xmlStr = "";
		String str;
		while ((str = sc.nextLine()).equals("0") == false) {
			xmlStr += str + "\n";
		}
		// Element e = DocumentHelper.parseText(xmlStr).getRootElement();
		Element e = new MakeBpmn("cc").getXML().getRootElement();
		System.out.println(e.element("BPMNDiagram").element("BPMNPlane").addAttribute("bpmnElement", "ccc").getDocument().asXML());
	}
}
