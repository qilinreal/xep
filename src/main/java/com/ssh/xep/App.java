package com.ssh.xep;

import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.ssh.xep.util.MakeBpmn;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException, UnsupportedEncodingException {
		MakeBpmn bpmn = new MakeBpmn("com.sample.hello", "hello man");
		bpmn.addTask("123", "for(int i=0; i<5; i++) {try {Thread.sleep(1000);\nSystem.out.println(\"1000\");}", "import java.net.URL;\r\n	 import java.lang.String;");
		System.out.println(bpmn.get());
	}
}
