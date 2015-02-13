package com.example.apkloader;

import java.io.File;
import java.lang.ClassLoader;

import dalvik.system.DexClassLoader;

public class APKLoader extends DexClassLoader {

	private String apkPath;

	public APKLoader(String apkPath, String cacheDir, ClassLoader classLoader) {
		super(apkPath, cacheDir, null, classLoader);
		this.apkPath = apkPath;
	}
	
	public APKLoader(File file, String cacheDir, ClassLoader classLoader) {
		super(file.getAbsolutePath(), cacheDir, null, classLoader);
		this.apkPath = apkPath;
	}
	
	
	public static DexClassLoader newInstance(File appDirectory, String cacheDir, ClassLoader classLoader){
			APKLoader apkLoader = new APKLoader(appDirectory, cacheDir, classLoader);
			apkLoader.apkPath = appDirectory.getAbsolutePath();
			return apkLoader;
	}
	
	
	public String getApkPath() {
		return apkPath;
	}

	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}

	

}
