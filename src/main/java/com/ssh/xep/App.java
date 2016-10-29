package com.ssh.xep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

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
			TransformerException, DocumentException, IOException {
		FileInputStream fis = new FileInputStream("bpmn_script_template.dat");
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		while((line=br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		String ret = String.format(sb.toString(), "This is the ret", 442);
		System.out.println(ret);
	}
}
