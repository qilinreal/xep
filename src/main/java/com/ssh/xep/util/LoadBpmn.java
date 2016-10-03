package com.ssh.xep.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 加载BPMN数据并生成对应文件。
 * 
 * @author qilin
 */
public class LoadBpmn {
	private static final String loadNamePrefix = "com.sample.";

	/**
	 * 在用户特定路径下生成bpmn文件
	 * 
	 * @param bpmnStr
	 * @param userId
	 * @param jobId
	 * @param userRoot
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void loadBpmn(String bpmnStr, String userId, String jobId, String userRoot)
			throws DocumentException, IOException {
		Document xml = DocumentHelper.parseText(bpmnStr);
		Element process = xml.getRootElement().element("process");
		process.remove(process.attribute("id"));
		process.addAttribute("id", loadNamePrefix + userId + "." + jobId);

		if (userRoot.endsWith("/") == false) {
			userRoot += "/";
		}
		File bpmnFile = new File(userRoot + "bin/com/jbpm/" + userId + "_" + jobId + ".bpmn");
		if (bpmnFile.exists()) {
			bpmnFile.delete();
		}
		bpmnFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(bpmnFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		osw.write(xml.asXML());
		osw.flush();
		osw.close();
		fos.close();
	}
}
