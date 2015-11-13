package com.pingan.cong.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pingan.cong.model.ClassTree.Node;
import com.pingan.cong.utils.PropertiesUtil;
import com.pingan.cong.utils.StringUtil;

public class JavaWriter {

	private String fileName;
	private String path;
	private String packageName;
	private Set<String> importSet = new HashSet<String>();
	private StringBuilder headerBuilder = new StringBuilder();
	private StringBuilder bodyBuilder = new StringBuilder();
	private ClassTree tree;

	public void setTree(ClassTree tree) {
		this.tree = tree;
	}

	public JavaWriter() {

	}

	public String getFileName() {

		StringBuilder sb = new StringBuilder();
		if (path != null) {
			sb.append(path);
		}
		if (packageName != null) {
			String[] pathInfo = packageName.split("\\.");
			for (String string : pathInfo) {
				sb.append(string).append(File.separator);
			}
		}
		return sb.append(fileName + ".java").toString();
	}

	public void setFileName(String fileName) {
		this.fileName = StringUtil.toUpperCaseFirstOne(fileName);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		if (!path.endsWith(File.separator)) {
			this.path = path + File.separator;
		}
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	private void addImport(Node node) {

		if (node.getType() == Type.LIST) {
			importSet.add(ImportPackage.LIST);
		} else {
			importSet.add(node.getClassPath());
		}
	}

	private void buildImport(StringBuilder headerBuilder) {
		Iterator<String> iterator = importSet.iterator();
		while (iterator.hasNext()) {
			headerBuilder.append("import ").append(iterator.next()).append(";");
		}
		headerBuilder.append("import ").append("com.paic.hyperion.core.hfjson.annotation.HFJsonField").append(";");
		headerBuilder.append("import ").append("com.paic.hyperion.core.hfjson.annotation.HFJsonObject").append(";");
		headerBuilder.append("import ").append("com.paic.hyperion.model.BaseModel").append(";");


	}

	public void writeJson2JavaFile(JsonEntity jsonEntity) throws Exception {

		if (StringUtil.isBlank(jsonEntity.getClassName()) || StringUtil.isBlank(jsonEntity.getPackageName())) {
			return;
		}

		JsonParser parser = new JsonParser();
		System.out.println(jsonEntity.getJsonString());
		JsonElement jsonElement = parser.parse(jsonEntity.getJsonString());
		if (!jsonElement.isJsonObject()) {
			throw new JsonSyntaxException("Illegal JsonString!");
		}
		ClassTree tree = new ClassTree();
		tree.genClassTree(jsonElement, jsonEntity);
//		tree.printTree();
		tree.preprocessing();
		this.setTree(tree);
		this.setFileName(jsonEntity.getClassName());
		this.setPath(PropertiesUtil.getProperty("output.dir.path"));
		this.setPackageName(jsonEntity.getPackageName());
		this.writeTree2File();
	}

	public void writeTree2File() {

		try {
			if (StringUtil.isBlank(fileName))
				return;
			String name = this.getFileName();
			System.out.println(name);
			File file = new File(name);

			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}

			FileWriter writer = new FileWriter(file);
			headerBuilder.append(JavaToken.PAGKAGE + packageName + ";" + JavaToken.NEWLINE);
			traverseTree(tree.getRoot());
			buildImport(headerBuilder);
			writer.write(headerBuilder.toString());
			writer.write(bodyBuilder.toString());
			writer.flush();
			writer.close();
			JavaFomater.format(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 遍历tree
	 * 
	 * @param node
	 */
	public void traverseTree(Node node) {
		
		if (Type.OBJECT == node.getType()) {
			if (!node.isWritable()) {
				if (node.getParent().getType() != Type.LIST) {
					bodyBuilder.append("@HFJsonField" + JavaToken.NEWLINE);
					bodyBuilder.append(
							JavaToken.PRIVATE + node.getClassName() + " " + node.getName() + ";" + JavaToken.NEWLINE);
					addImport(node);
					writeGetterAndSetter(node);
				}

			} else {
				if (node == tree.getRoot()) {
					bodyBuilder.append("@HFJsonObject" + JavaToken.NEWLINE);
					bodyBuilder.append(JavaToken.PUBLIC + JavaToken.CLASS + StringUtil.toUpperCaseFirstOne(fileName)
							+" "+JavaToken.EXTENDS+"BaseModel"+" {" + JavaToken.NEWLINE);
				} else {
					if (node.getParent().getType() != Type.LIST) {
						bodyBuilder.append("@HFJsonField" + JavaToken.NEWLINE);
						bodyBuilder.append(JavaToken.PRIVATE + node.getClassName() + " " + node.getName() + ";"
								+ JavaToken.NEWLINE);
						writeGetterAndSetter(node);
					}
					bodyBuilder.append("@HFJsonObject" + JavaToken.NEWLINE);
					bodyBuilder.append(JavaToken.PUBLIC + JavaToken.STATIC + JavaToken.CLASS + node.getClassName()
							+ " {" + JavaToken.NEWLINE);
				}
				List<Node> children = node.getChildren();
				if (children != null && !children.isEmpty()) {
					for (Node child : children) {
						if (child.isWritable()) {
							traverseTree(child);
						}
					}
				}
				bodyBuilder.append("}");
			}
		} else if (Type.LIST == node.getType()) {
			addImport(node);
			bodyBuilder.append("@HFJsonField" + JavaToken.NEWLINE);
			if (node.hasSameChildType()) {
				Node child = node.getChildren().get(0);
				if (!child.isWritable()) {
					addImport(child);
				}
				bodyBuilder.append(JavaToken.PRIVATE + "List<" + child.getClassName() + ">" + node.getName() + ";"
						+ JavaToken.NEWLINE);

			} else {

				bodyBuilder.append(JavaToken.PRIVATE + "List " + node.getName() + ";" + JavaToken.NEWLINE);
			}
			writeGetterAndSetter(node);
			List<Node> children = node.getChildren();
			if (children != null && !children.isEmpty()) {
				for (Node child : children) {
					if (child.getType() == Type.OBJECT) {
						// if(child.isWritable()){
						traverseTree(child);
						// }
						break;
					}
				}
			}
		} else {
			if (!"classname".equals(node.getName())) {
				bodyBuilder.append("@HFJsonField" + JavaToken.NEWLINE);
			}
			bodyBuilder.append(
					JavaToken.PRIVATE + node.getType().getName() + " " + node.getName() + ";" + JavaToken.NEWLINE);
			if (!"classname".equals(node.getName())) {
				writeGetterAndSetter(node);
			}

		}

	}

	/**
	 * 生成setter和getter方法
	 * 
	 * @param node
	 */
	public void writeGetterAndSetter(Node node) {

		if (Type.NULL == node.getType())
			return;

		if (Type.OBJECT == node.getType()) {

			// set方法
			bodyBuilder
					.append(JavaToken.PUBLIC + JavaToken.VOID + "set" + StringUtil.toUpperCaseFirstOne(node.getName())
							+ "(" + node.getClassName() + " " + node.getName() + "){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.THIS + "." + node.getName() + "=" + node.getName() + ";");
			bodyBuilder.append("}");

			// get方法
			bodyBuilder.append(JavaToken.PUBLIC + node.getClassName() + " get"
					+ StringUtil.toUpperCaseFirstOne(node.getName()) + "(){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.RETURN + JavaToken.THIS + "." + node.getName() + ";");
			bodyBuilder.append("}");

		} else if (Type.LIST == node.getType()) {

			String listStr = "";
			if (node.hasSameChildType()) {
				Node child = node.getChildren().get(0);
				listStr = "List<" + child.getClassName() + ">";
			} else {
				listStr = "List";
			}
			// set方法
			bodyBuilder
					.append(JavaToken.PUBLIC + JavaToken.VOID + "set" + StringUtil.toUpperCaseFirstOne(node.getName())
							+ "(" + listStr + " " + node.getName() + "){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.THIS + "." + node.getName() + "=" + node.getName() + ";");
			bodyBuilder.append("}");

			// get方法
			bodyBuilder.append(JavaToken.PUBLIC + listStr + " get" + StringUtil.toUpperCaseFirstOne(node.getName())
					+ "(){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.RETURN + JavaToken.THIS + "." + node.getName() + ";");
			bodyBuilder.append("}");

		} else if (Type.BOOLEAN == node.getType()) {
			// set方法
			bodyBuilder
					.append(JavaToken.PUBLIC + JavaToken.VOID + "set" + StringUtil.toUpperCaseFirstOne(node.getName())
							+ "(" + Type.BOOLEAN.getName() + " " + node.getName() + "){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.THIS + "." + node.getName() + "=" + node.getName() + ";");
			bodyBuilder.append("}");

			// get方法
			bodyBuilder.append(JavaToken.PUBLIC + Type.BOOLEAN.getName() + " is"
					+ StringUtil.toUpperCaseFirstOne(node.getName()) + "(){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.RETURN + JavaToken.THIS + "." + node.getName() + ";");
			bodyBuilder.append("}");
		} else {

			// set方法
			bodyBuilder
					.append(JavaToken.PUBLIC + JavaToken.VOID + "set" + StringUtil.toUpperCaseFirstOne(node.getName())
							+ "(" + node.getType().getName() + " " + node.getName() + "){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.THIS + "." + node.getName() + "=" + node.getName() + ";");
			bodyBuilder.append("}");

			// get方法
			bodyBuilder.append(JavaToken.PUBLIC + node.getType().getName() + " get"
					+ StringUtil.toUpperCaseFirstOne(node.getName()) + "(){" + JavaToken.NEWLINE);
			bodyBuilder.append(JavaToken.RETURN + JavaToken.THIS + "." + node.getName() + ";");
			bodyBuilder.append("}");

		}

	}

}
