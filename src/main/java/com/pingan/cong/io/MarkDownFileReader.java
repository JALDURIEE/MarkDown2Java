package com.pingan.cong.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pingan.cong.model.ClassTree;
import com.pingan.cong.model.JavaToken;
import com.pingan.cong.model.JavaWriter;
import com.pingan.cong.model.JsonEntity;
import com.pingan.cong.utils.StringUtil;

public class MarkDownFileReader {

	public final static String MARKDOWN_EXT = ".md";

	public void traverseFiles(String fileName) {
		File file = new File(fileName);
		traverseFiles(file);
	}

	public void traverseFiles(File file) {

		if (file == null)
			return;

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					traverseFiles(files[i]);
				}
			}
		} else {

			String fileName = file.getName();
			if (fileName.endsWith(MARKDOWN_EXT)) {
				System.out.println(file.getPath());
				
					List<JsonEntity> list = parseMarkDownFile(file);

					if (list != null && !list.isEmpty()) {
						for (JsonEntity entity : list) {

							JavaWriter javaWriter = new JavaWriter();
							try{
							javaWriter.writeJson2JavaFile(entity);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				
			}
		}

	}

	/**
	 * 解析MarkDown文件返回json
	 * 
	 * @param fileName
	 * @return
	 */
	public List<JsonEntity> parseMarkDownFile(File file)  {

		FileReader reader;
		List<JsonEntity> list = new ArrayList<JsonEntity>();
		StringBuilder json = new StringBuilder();
		StringBuilder info = new StringBuilder();
		try {
			reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			boolean isInfoBody = false;
			JsonEntity jsonEntity = new JsonEntity();
			int backetCount = 0;
			while ((line = br.readLine()) != null) {

				if (isInfoBody && !line.trim().endsWith("-->")) {
					info.append(line);
				}
				if (line.trim().startsWith("<!--") && !line.trim().endsWith("-->")) {
					info.append(line);
					isInfoBody = true;
				}

				if (line.trim().endsWith("-->")) {
					try {
						info.append(line);
						isInfoBody = false;
						String str = info.toString();
						str = str.replaceAll("<!--", "");
						str = str.replaceAll("-->", "");
						String[] arr = str.split(",");
						for (int i = 0; i < arr.length; i++) {
							if (!StringUtil.isBlank(arr[i])) {
								String[] strArr = arr[i].split(":");
								if ("JAVA".equals(strArr[0].trim())) {
									jsonEntity.setPackageName(strArr[1].trim());
								} else if ("CLASSNAME".equals(strArr[0].trim())) {
									jsonEntity.setClassName(strArr[1].trim());
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (backetCount > 0 || line.trim().startsWith("{")) {

					json.append(line).append(JavaToken.NEWLINE);
					if (line.trim().startsWith("{")) {
						backetCount++;
					}
					if (line.trim().endsWith("}") || line.trim().endsWith("},")) {
						backetCount--;
					}

					if (backetCount == 0 && json.length() != 0) {
						if (!StringUtil.isBlank(jsonEntity.getClassName())
								&& !StringUtil.isBlank(jsonEntity.getPackageName())) {
							jsonEntity.setJsonString(json.toString());
							list.add(jsonEntity);
							jsonEntity = new JsonEntity();
							info = new StringBuilder();
						}
						json = new StringBuilder();

					}
				}


			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

}
