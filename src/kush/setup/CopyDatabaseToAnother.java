package kush.setup;

import java.io.IOException;

public class CopyDatabaseToAnother 
{
	public CopyDatabaseToAnother() 
	{
		copy();
	}
	public void copy()
	{
		String command = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin>mysqldump -hlocalhost -uroot -\r\n" + 
				"p2355 hotel | mysql -hlocalhost -uroot -p2355 demo";
		//command = "mysqldump -hlocalhost -uroot p2355 hotel | mysql -hlocalhost -uroot -p2355 demo2";
		try {
			@SuppressWarnings("unused")
			Process p = Runtime.getRuntime().exec(command);
			System.out.println("Run");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		new CopyDatabaseToAnother();
		// TODO Auto-generated method stub

	}

}
