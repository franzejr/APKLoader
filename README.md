# APKLoader
Load an APK file and use reflection to execute a method

##### Usage

Loading an APK:
```java

//Class you wan to load
className = "com.ufc.methods.Sum";
		
//Apk Name
String appName = "StaticMath.apk";

String pathExternalStorage = Environment.getExternalStorageDirectory().toString();
appDirectory =  new File(pathExternalStorage +"/" + appName);
	
staticMathApk = (APKLoader) APKLoader.newInstance(appDirectory,
				getCacheDir().getAbsolutePath(), getClassLoader());
```

Executing a method:
```java
sumClass = staticMathApk.loadClass(className);
ClassLoaderWrapper classLoader = new ClassLoaderWrapper(sumClass);
//hashMap contains the parameters from my Method

//Getting the result
result = (String) classLoader.executeMethod("execute", hashMap);
```
