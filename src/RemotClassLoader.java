import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JTextArea;

import org.omg.CORBA.portable.Streamable;

import sun.net.www.http.HttpClient;

public class RemotClassLoader extends ClassLoader {

	public static void main(String[] args) {
		String path = "/Users/Yaochuan/";

		Class class1 = new RemotClassLoader().findclass(path, "test.class");

		try {
			Object o = class1.newInstance();
			Method md = class1.getMethod("say", String.class);
			md.invoke(o, "fuck you ok??");

		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		JTextArea textArea=new JTextArea("frame");
		textArea.setSize(200, 100);
		textArea.setVisible(true);

	}

	public Class<?> findclass(String path, String classname) {

		try {
			URL url = new URL("http://192.168.31.193:8080/webtest/test.class");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			// conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;
			// MSIE 5.0; Windows NT; DigExt)");

			// 得到输入流
			InputStream is = conn.getInputStream();

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[2048];
			int num = 0;
			while ((num = is.read(buffer)) != -1) {
				stream.write(buffer, 0, num);
			}

			byte[] classdata = stream.toByteArray();

			System.out.println(classdata.length);
			return defineClass("test", classdata, 0, classdata.length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
