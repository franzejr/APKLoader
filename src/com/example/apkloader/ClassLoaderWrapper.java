package com.example.apkloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ClassLoaderWrapper{
	
	private Class classToLoad;

	public ClassLoaderWrapper(Class classToLoad){
		this.classToLoad = classToLoad;
	}
	
	/*
	 * Executes an arbitrary method passing only its name
	 * 
	 * @param methodName Method Name
	 * @param @params HashMap to be used as params
	 * @return Object 
	 */
	public Object executeMethod(String methodName, HashMap<String,String> params){

		try {

			Method executeMethod = lookForMethod(methodName);

			return executeMethod.invoke(this, params);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/*
	 * Executes an arbitrary method passing only its name
	 * 
	 * @param methodName Method Name
	 * @return Object 
	 */
	public Object executeMethod(String methodName){

		try {

			Method executeMethod = lookForMethod(methodName);

			return executeMethod.invoke(this);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	private Method lookForMethod(String methodName){
		for(Method m : classToLoad.getMethods()){
			if(m.getName().equals(methodName)){
				return m;
			}
		}
		return null;			
	}

}
