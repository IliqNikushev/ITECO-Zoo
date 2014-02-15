import java.io.PrintStream;


public class Console{
	public static PrintStream out;
	
	public static void Log(Object obj){
		out.println(obj.toString());
	}

	public static void LogException(Exception ex) {
		Log("-----------EXCEPTION--------");
		Log(ex.getMessage());
		ex.printStackTrace();
		Log("---------EXCEPTION END------");
	}
}
