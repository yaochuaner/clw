import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class test extends JFrame{
   public void say(String msg){
     System.out.println(msg);
     
     JFrame jFrame=new JFrame("fuck");
     jFrame.setVisible(true);
     jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     jFrame.setSize(200,200);
     
     JLabel jb=new JLabel("卿帅你好,给你E盘创建一个文本");
     jFrame.add(jb);
     
     File file=new File("E:/fuck.txt");
     if (!file.exists()) {
    	 try {
			file.createNewFile();
			
			FileOutputStream fos=new FileOutputStream(file);
			byte[] data="卿帅，你好啊".getBytes();;
			fos.write(data, 0, data.length);
			
			fos.flush();
			fos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     
   }

    public static void main(String[] args) {
	   new test().say("a");
    }
}
