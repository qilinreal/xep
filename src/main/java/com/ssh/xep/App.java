package com.ssh.xep;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import com.ssh.xep.util.JobJSON;
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
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line=sc.nextLine()).equals("0") == false) {
			sb.append(line);
			sb.append("\n");
		}
		JobJSON json = new JobJSON();
		json.init(sb.toString());
		System.out.println(json.getJSON());
	}
}
