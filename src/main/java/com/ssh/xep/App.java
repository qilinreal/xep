package com.ssh.xep;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

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
		while((str=sc.nextLine()).equals("0") == false) {
			xmlStr += str+"\n";
		}
		System.out.println(DocumentHelper.parseText(xmlStr).asXML());
	}
}
