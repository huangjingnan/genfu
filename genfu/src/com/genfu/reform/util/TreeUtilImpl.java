package com.genfu.reform.util;

import java.util.ArrayList;
import java.util.List;

import com.genfu.reform.model.NavigationNode;

public class TreeUtilImpl implements TreeUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StringBuffer strTree = new StringBuffer();
		String temp = "a";
		strTree.append(temp);
		temp = "b";
		strTree.append(temp);
		System.out.println(strTree.toString());

	}

	@Override
	public String structureNaviTree(List<NavigationNode> list1) {
		StringBuffer strTree = new StringBuffer();
		NavigationNode tempNavigationNode = new NavigationNode();
		List<NavigationNode> treeAndItem = new ArrayList<NavigationNode>();
		long treeId = 0;
		while (!list1.isEmpty()) {
			tempNavigationNode = list1.get(0);
			if (treeAndItem.isEmpty()) {
				treeId = tempNavigationNode.getId();
				strTree.append("var tree" + treeId + " = new WebFXTree(\""
						+ tempNavigationNode.getNaviText() + "\",\""
						+ tempNavigationNode.getNaviAction() + "\"); \n");
			} else {
				int j = 0;
				for (; j < treeAndItem.size(); j++) {
					if (tempNavigationNode.getNaviParentId() == treeAndItem
							.get(j).getId()) {
						if (tempNavigationNode.getNaviParentId() == treeId) {
							strTree.append("var treeItem"
									+ tempNavigationNode.getId()
									+ " = new WebFXTreeItem(\""
									+ tempNavigationNode.getNaviText()
									+ "\",\""
									+ tempNavigationNode.getNaviAction()
									+ "\"); \n");
							strTree.append("tree" + treeId + ".add(treeItem"
									+ tempNavigationNode.getId() + "); \n");
						} else {
							strTree.append("var treeItem"
									+ tempNavigationNode.getId()
									+ " = new WebFXTreeItem(\""
									+ tempNavigationNode.getNaviText()
									+ "\",\""
									+ tempNavigationNode.getNaviAction()
									+ "\",treeItem"
									+ tempNavigationNode.getNaviParentId()
									+ "); \n");
						}

						break;
					}
				}
				// no parent node
				if (j >= treeAndItem.size()) {
					strTree.append("var treeItem" + tempNavigationNode.getId()
							+ " = new WebFXTreeItem(\""
							+ tempNavigationNode.getNaviText() + "\",\""
							+ tempNavigationNode.getNaviAction() + "\"); \n");
					strTree.append("tree" + treeId + ".add(treeItem"
							+ tempNavigationNode.getId() + "); \n");
				}

			}

			treeAndItem.add(tempNavigationNode);
			list1.remove(0);
		}

		if (strTree.length() > 100) {
			strTree.append("document.write(tree" + treeId
					+ ");\n document.close(); \n");
		}
		return strTree.toString();
	}
}
