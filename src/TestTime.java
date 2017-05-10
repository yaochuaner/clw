import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestTime {
	public static void main(String[] args) {
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		
	        try {
				System.out.println(format.parse("2017-10-24").getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
