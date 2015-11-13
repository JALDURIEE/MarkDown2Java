package com.pingan.cong.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pingan.cong.utils.StringUtil;

public class ClassTree {

	private String packageName = "";

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void printTree() {

		if (root == null)
			return;
		else
			root.print(0);
	}

	public void addNode(Node child, Node parent) {
		parent.addChild(child);
	}

	/**
	 * 预处理
	 */
	public void preprocessing() {
		if (root == null) {
			return;
		}
		List<Node> objectList = new ArrayList<ClassTree.Node>();
		List<Node> listTypeItems = new ArrayList<ClassTree.Node>();
		Map<String,List<Node>> classMap = new HashMap<String,List<Node>>();
		root.traverse(objectList, listTypeItems,classMap);
		
		Set<String> keySet = classMap.keySet();
		for(String key:keySet){
			
			List<Node> classList =  classMap.get(key);
			Node firstClass= classList.get(0);
			for(int i=1;i<classList.size();i++){
				
				Node clazz = classList.get(i);
				 List<Node> nodes = clazz.getAllNodes();
				 for(int j=1 ; j<nodes.size();j++){
					 Node item = nodes.get(j);
					 if(firstClass.find(item.getName())==null){
						 
						 Node parent = firstClass.findByClassName(item.getParent().getClassName());
						 Node childNode = item.copyNode(null);
						 parent.addChild(childNode);
						 childNode.setParent(parent);
					 }
				 }
			}
		}
		

		for (int i = 0; i < listTypeItems.size(); i++) {

			Node node = listTypeItems.get(i);
			List<Node> children = node.getChildren();
			if (!children.isEmpty()) {
				Node firstChild = children.get(0);

				for (int j = 1; j < children.size(); j++) {

					Node child = children.get(j);
					 List<Node> nodes = child.getAllNodes();
					 for(Node item:nodes){
						 if(firstChild.find(item.getName())==null){
							 
							 Node parent = firstChild.find(item.getParent().getName());
							 Node childNode = item.copyNode(null);
							 parent.addChild(childNode);
							 childNode.setParent(parent);
						 }
					 }
				
				}

			}

		}

		for (int i = 0; i < objectList.size(); i++) {

			Node nodeA = objectList.get(i);
			if (!nodeA.isWritable())
				continue;

			for (int j = 0; j < objectList.size(); j++) {

				if (i == j)
					continue;
				Node nodeB = objectList.get(j);
				if (nodeA.equals(nodeB)) {
					nodeB.setWritable(false);
					nodeB.setClassName(nodeA.getClassName());
					nodeB.setClassPath(nodeA.getClassPath());
				} else if (nodeB.isContains(nodeA)) {
					nodeA.setWritable(false);
					nodeA.setClassName(nodeB.getClassName());
					nodeA.setClassPath(nodeB.getClassPath());
				}

			}
		}

	}

	/**
	 * 利用json生成tree
	 * 
	 * @param jsonElement
	 */
	public void genClassTree(JsonElement jsonElement, JsonEntity jsonEntity) {

		JsonObject jsonObject = jsonElement.getAsJsonObject();
		this.setPackageName(jsonEntity.getPackageName());
		root = new Node();
		root.setName(jsonEntity.getClassName());
		root.setClassName(jsonEntity.getClassName());
		root.setType(Type.OBJECT);
		parseJsonObject(jsonObject, root);
	}

	public void parseJsonObject(JsonObject jsonObject, Node parent) {

		Set<Map.Entry<String, JsonElement>> set = jsonObject.entrySet();
		for (Map.Entry<String, JsonElement> entry : set) {
			Node node = new Node();
			String name = entry.getKey();
			JsonElement value = entry.getValue();
			Type type = Type.getTypeFromElement(value);
			node.setName(name);
			if("classname".equals(name)){
				parent.setClassName(value.getAsString());
				parent.genClassPath();
			}
			node.setType(type);
			parent.addChild(node);
			node.setParent(parent);
			if (type == Type.OBJECT) {
				node.setClassName(StringUtil.toUpperCaseFirstOne(name));
				node.genClassPath();
				parseJsonObject(value.getAsJsonObject(), node);
			} else if (type == Type.LIST) {
				parseJsonArray(value.getAsJsonArray(), node);
			}
		}
	}

	public void parseJsonArray(JsonArray jsonArray, Node parent) {
		if (jsonArray != null && jsonArray.size() != 0) {

			for (int i = 0; i < jsonArray.size(); i++) {
				JsonElement jsonElement = jsonArray.get(i);
				Type type = Type.getTypeFromElement(jsonElement);
				if (type == Type.OBJECT) {
					Node node = new Node();
					node.setType(type);
					int index = parent.getName().indexOf("List");
					if (index != -1) {
						String name = parent.getName().substring(0, index);
						node.setName(name);
						node.setClassName(StringUtil.toUpperCaseFirstOne(name));

					} else {
						node.setName(parent.getName());
						node.setClassName(StringUtil.toUpperCaseFirstOne(parent.getName()));

					}
					
					if(i!=0){
						
						node.setClassName(parent.getChildren().get(0).getClassName());
					}
					parent.addChild(node);
					node.setParent(parent);
					node.genClassPath();
					parseJsonObject(jsonElement.getAsJsonObject(), node);
				} else if (type == Type.LIST) {
					Node node = new Node();
					node.setType(type);
					parent.addChild(node);
					node.setParent(parent);
					parseJsonArray(jsonElement.getAsJsonArray(), node);
				} else {
					Node node = new Node();
					node.setType(type);
					parent.addChild(node);
					node.setParent(parent);
				}
			}
		}

	}

	public class Node {

		private String className = "";

		private String classPath = "";

		private String name = "";

		private Type type;

		private List<Node> children = new ArrayList<Node>();

		private Node parent;

		private boolean writable = true;

		public boolean isWritable() {

			return writable;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public void setWritable(boolean writable) {
			this.writable = writable;
		}

		public String getClassPath() {

			return this.classPath;
		}

		public void genClassPath() {
			StringBuilder sb = new StringBuilder();
			sb.append(getPackageName()).append(".");
			Stack<String> stack = new Stack<String>();
			Node currentNode = this;
			while (currentNode != null) {
				if (currentNode.type == Type.OBJECT) {
					stack.push(currentNode.getClassName());
				}
				currentNode = currentNode.getParent();
			}

			int size = stack.size();
			for (int i = 0; i < size; i++) {
				if (i != size - 1) {
					sb.append(stack.pop()).append(".");
				} else {
					sb.append(stack.pop());
				}
			}

			this.classPath = sb.toString();
		}

		public void setClassPath(String classPath) {

			this.classPath = classPath;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub

			if (obj == null)
				return false;

			if (this == obj)
				return true;

			if (!(obj instanceof Node)) {
				return false;
			}

			Node node = (Node) obj;

			if (this.getChildren().size() != node.getChildren().size()) {
				return false;
			}

			Set<String> set = new HashSet<String>();

			for (int i = 0; i < this.getChildren().size(); i++) {
				Node child1 = this.getChildren().get(i);
				Node child2 = node.getChildren().get(i);
				set.add(child1.getName());
				set.add(child2.getName());
			}

			if (set.size() != this.getChildren().size()) {
				return false;
			}

			return true;
		}

		public boolean isContains(Node node) {

			Map<String, String> map = new HashMap<String, String>();
			List<Node> children = this.getChildren();
			for (Node child : children) {
				map.put(child.getName(), "");
			}

			List<Node> children1 = node.getChildren();
			for (Node child : children1) {
				if (map.get(child.getName()) == null) {
					return false;
				}
			}

			return true;
		}

		/**
		 * 如果节点类型是List 判断它的孩子节点类型是否统一 以设置List泛型类型
		 * 
		 * @return
		 */
		public boolean hasSameChildType() {

			if (Type.LIST != type)
				return true;

			if (Type.LIST == type) {

				if (children == null || children.isEmpty())
					return false;

				if (children != null && !children.isEmpty()) {

					Node index = children.get(0);
					for (Node child : children) {
						if (child.getType() != index.getType()) {
							return false;
						}
					}
				}

			}

			return true;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public void print(int height) {
			for (int i = 0; i < height; i++)
				System.out.print("  ");
			System.out.println(this.toString());
			if (!children.isEmpty()) {
				for (Node node : children) {
					node.print(height + 1);
				}
			}
		}

		public Node copyNode(Node parent) {

			Node node = new Node();
			node.setName(this.getName());
			node.setClassName(this.getClassName());
			node.setClassPath(this.getClassPath());
			node.setType(this.getType());
			if (parent != null) {
				node.setParent(parent);
				parent.getChildren().add(node);
			}

			if (!children.isEmpty()) {
				for (Node child : children) {
					child.copyNode(node);
				}
			}

			return node;

		}
		
		public List<Node> getAllNodes(){
			
			List<Node> list = new ArrayList<ClassTree.Node>();
			traverse(list);
			return list;
		}
		
		private void traverse(List<Node> list){
			
			list.add(this);
			if (!children.isEmpty()) {
				for (Node node : children) {
					node.traverse(list);
				}
			}
			
			
		}

		public void traverse(List<Node> objectList, List<Node> listTypeItems,Map<String,List<Node>> classMap) {

			if (this.type == Type.OBJECT) {
				objectList.add(this);
			}

			if (this.type == Type.LIST) {
				listTypeItems.add(this);
			}
			
			if(this.type== Type.STRING){
				if("classname".equals(this.getName())){
					if(classMap.get(this.getParent().getClassName())==null){
						List<Node> classList = new ArrayList<Node>();
						classList.add(this.getParent());
						classMap.put(this.getParent().getClassName(), classList);
					}else{
						classMap.get(this.getParent().getClassName()).add(this.getParent());
					}
				}
			}
			
			if (!children.isEmpty()) {
				for (Node node : children) {
					node.traverse(objectList, listTypeItems,classMap);
				}
			}
		}

		public Node find(String nodeName) {

			Node node = null;
			if (this.getName().equals(nodeName)) {
				return this;
			}

			List<Node> children = this.getChildren();
			for (Node child : children) {
				if ((node = child.find(nodeName)) != null) {
					return node;
				}
			}

			return node;
		}
		
		public Node findByClassName(String className){
			Node node = null;
			if (this.getClassName().equals(className)) {
				return this;
			}

			List<Node> children = this.getChildren();
			for (Node child : children) {
				if ((node = child.findByClassName(className)) != null) {
					return node;
				}
			}

			return node;
		}

		@Override
		public String toString() {
			return name + "|" + type.getName();
		}

		public void addChild(Node node) {
			this.children.add(node);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public List<Node> getChildren() {
			return children;
		}

	}

}
