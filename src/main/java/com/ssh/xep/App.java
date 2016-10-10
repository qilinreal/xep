package com.ssh.xep;

import java.io.UnsupportedEncodingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.DocumentException;
import com.ssh.xep.util.MakeBpmn;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException, UnsupportedEncodingException, DocumentException {
		MakeBpmn bpmn = new MakeBpmn("cccc");
		bpmn.addTask("123", "ccccc", 123, "cais", "this is a script");
	}
}
