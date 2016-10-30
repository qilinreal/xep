package com.ssh.xep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ssh.xep.util.MakeBpmn;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException, DocumentException, IOException, ClassNotFoundException {
		Document document = DocumentHelper.parseText("<acr attr='1'><node at='123'>va</node></acr>");
		System.out.println(document.asXML());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		Element root = document.getRootElement();
		Element e = root.element("node");
		root.remove(e);
		root.setText("nihao:");
		e.setText("cctv");
		System.out.println(document.asXML());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(e.asXML());
	}
}
