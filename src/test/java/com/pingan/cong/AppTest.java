package com.pingan.cong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pingan.cong.io.MarkDownFileReader;
import com.pingan.cong.model.ClassTree;
import com.pingan.cong.model.JavaWriter;
import com.pingan.cong.model.JsonEntity;
import com.pingan.cong.utils.PropertiesUtil;


public class AppTest 
{
	@Test
	public void writeMarkDownFiles(){
		
		MarkDownFileReader markDownFileReader = new MarkDownFileReader();
		
		
				try {
					markDownFileReader.traverseFiles(PropertiesUtil.getProperty("input.dir.path"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	
	}
	


}
