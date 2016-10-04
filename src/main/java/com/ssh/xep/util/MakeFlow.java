package com.ssh.xep.util;

import javax.xml.parsers.ParserConfigurationException;

public class MakeFlow {
	private MakeBpmn bpmn;

	public MakeFlow(String loadName, String name) throws ParserConfigurationException {
		bpmn = new MakeBpmn(loadName, name);
	}

	// 添加工具
	// 工具是获取工具ID的，执行程序的时候，会根据工具ID获取工具位置，然后执行工具
	public void addTask(String id, String toolId) {
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
