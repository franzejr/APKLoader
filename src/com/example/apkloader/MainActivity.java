package com.example.apkloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private File appDirectory;
	private String className;
	private APKLoader staticMathApk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView txtView = (TextView) findViewById(R.id.result);

		loadAPKFile();

		staticMathApk = (APKLoader) APKLoader.newInstance(appDirectory,
				getCacheDir().getAbsolutePath(), getClassLoader());

		txtView.setText(appDirectory.toString());
		Class sumClass;
		String result = "";

		try {
			sumClass = staticMathApk.loadClass(className);
			
			ClassLoaderWrapper classLoader = new ClassLoaderWrapper(sumClass);

			HashMap<String, String> hashMap = loadParameters();

			result = (String) classLoader.executeMethod("execute", hashMap);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		txtView.setText(result);
	}

	private HashMap<String, String> loadParameters() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("first", "33222333");
		hashMap.put("second", "2");
		return hashMap;
	}

	private void loadAPKFile() {
		//Classes
		className = "com.ufc.methods.Sum";
		
		//Apk
		String appName = "StaticMath.apk";
		
		String pathExternalStorage = Environment.getExternalStorageDirectory().toString();
		appDirectory =  new File(pathExternalStorage +"/" + appName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
