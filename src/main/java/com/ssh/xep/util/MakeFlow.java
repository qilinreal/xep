package com.ssh.xep.util;

import javax.xml.parsers.ParserConfigurationException;

public class MakeFlow {
	private MakeBpmn bpmn;

	public MakeFlow(String loadName, String name) throws ParserConfigurationException {
		bpmn = new MakeBpmn(loadName, name);
	}

	public MakeBpmn getBpmn() {
		return bpmn;
	}

	// 添加工具
	// 工具是获取工具ID的，执行程序的时候，会根据工具ID获取工具位置，然后执行工具
	// 获取工具这个功能是在JBPM执行时实现的
	/*
	 * String path = conn.executeSql("select toolPath from tool where toolId = [toolId]");
	 * Process p = Process.execute("path");
	 */
	public void addTask(String id, String name, String toolId, String toolName) {
	}

	// 给不同ID之间添加连接
	public void addConnection(String fromId, String toId) {
		bpmn.addConnection(fromId, toId);
	}

	// 添加分开节点
	public void addDiverging(String gatewayId) {
		bpmn.addDiverging(gatewayId);
	}

	// 添加合并节点
	public void addConverging(String gatewayId) {
		bpmn.addConverging(gatewayId);
	}
}
