package com.zzb.mynew.util;

import android.os.Environment;
import android.util.Log;

import com.zzb.mynew.common.baseapp.BaseApplication;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class CacheUtils {
	private static String rootPath = "/新闻/cache";
	private static String dataPath = "/data";
	public static String createFile(){
		String root;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			root = Environment.getExternalStorageDirectory().getPath();
		} else {
			root = BaseApplication.getAppContext().getCacheDir().getPath();
		}
		rootPath=root+rootPath;
		dataPath=rootPath+dataPath;
		File file =new File(dataPath);
		if(!file.exists()){
			file.mkdirs();
		}
		return dataPath;
	}
	public static void setData(String json,String name) {
		try {
			FileWriter fileWriter =new FileWriter(new File(dataPath,name+".txt"));
			BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
			bufferedWriter.write(json);
			bufferedWriter.flush();
			fileWriter.close();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getData(String name) {
		String json = "";
		try {
			FileReader reader = new FileReader(new File(dataPath,name+".txt"));
			BufferedReader bufferedReader = new BufferedReader(reader);
		     String line;
	             //分行读取
	          while (( line = bufferedReader.readLine()) != null) {
	                json += line + "\n";
	            }
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.d("TestFile", "The File doesn't not exist.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
