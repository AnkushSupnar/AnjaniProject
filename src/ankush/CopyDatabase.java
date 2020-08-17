package ankush;

public class CopyDatabase 
{
	public CopyDatabase()
	{
		try {
		
			String command="cmd C:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin>mysqldump -u root -p hotel > todaydemo.sql";
			@SuppressWarnings("unused")
			Process p = Runtime.getRuntime().exec(command);
			
		} catch (Exception e) 
		{
			
			// TODO: handle exception
		}
		
	}

	public static void main(String[] args)
	{
		new CopyDatabase();
		// TODO Auto-generated method stub

	}

}
