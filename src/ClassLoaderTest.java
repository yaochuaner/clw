import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.omg.CORBA.portable.Streamable;

public class ClassLoaderTest extends ClassLoader{
	
	
	public static void main(String[] args) {
		String path="/Users/Yaochuan/";
		
		Class class1= new ClassLoaderTest().findclass(path, "test.class");
		
			try {
				Object o = class1.newInstance();
				  Method md=	class1.getMethod("say",String.class);
				  md.invoke(o, "fuck you ok??");
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
		
	}
	
	public Class<?> findclass(String path,String classname) {
		
		try{
			InputStream is=new FileInputStream(path+classname);
			ByteArrayOutputStream stream=new ByteArrayOutputStream();
			byte[] buffer=new byte[2048];
			int num=0;
			while ((num=is.read(buffer))!=-1) {
				stream.write(buffer,0,num);
			}
			
			byte[] classdata=stream.toByteArray();
		
			System.out.println(classdata.length);
			return  defineClass("test", classdata, 0, classdata.length);
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		
		return null;
	}

}
