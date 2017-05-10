
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class WebTest extends Thread  
{  
  
    ServerSocket server = null;  
    Socket sk = null;  
    BufferedReader rdr = null;  
    PrintWriter wtr = null;  
  
    public WebTest()  
    {  
        try  
        {  
            server = new ServerSocket(1987);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    public void run()  
    {  
  
        while (true)  
            {  
                System.out.println("Listenning...");  
                try  
                {  
//                  每个请求交给一个线程去处理  
                    sk = server.accept();  
                    ServerThread th = new ServerThread(sk);  
                    th.start();  
                    //sleep(1000);  
                }  
                catch (Exception e)  
                {  
                    e.printStackTrace();  
                }  
                  
            }  
    }  
  
    public static void main(String [] args)  
    {  
        new WebTest().start();  
    } 
    private int clientCount=0;
    class ServerThread extends Thread  
    {  
  
    	
        Socket sk = null;  
        public ServerThread(Socket sk)  
        {  
            this.sk = sk;  
        }  
        public void run()  
        {  
            try  
            {  
                wtr = new PrintWriter(sk.getOutputStream());  
                rdr = new BufferedReader(new InputStreamReader(sk  
                        .getInputStream()));  
                String line =""; 
                try {
                	if (!sk.isClosed()) {
						
                		line = rdr.readLine();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
                System.out.println("从客户端来的信息：" + line);  
//              特别，下面这句得加上     “\n”,  
                
             
                //System.out.println("已经返回给客户端！");  
               if (line!=null&&line.indexOf("?")!=-1) {
            	   clientCount++;
            	   wtr.println("你好，您是第！'" + clientCount + "'位访问者\n"); 
			    }else if (line.indexOf("ico")!=-1) {
					
				}
                
               wtr.flush();  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  finally {
            	  wtr.close();
            	 
            	 
			}
              
        }  
          
    }  
}