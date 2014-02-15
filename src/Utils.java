import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class Utils {
	// Enables parsing UTF-8 documents
	public static int parseInt(String s){
		try
		{
			//1: Removes negative chars
			int count = 0;
			byte[] allBytes = s.getBytes();
			for (int i = 0; i < allBytes.length; i++) {
				if(allBytes[i] > 0){
					count += 1;
				}
			}
			int current = 0;
			byte[] bRes = new byte[count];
			for (int i = 0; i < allBytes.length; i++) {
				if(allBytes[i] > 0)
				{
					bRes[current] = allBytes[i];
					current += 1;
				}
			}
			int result = Integer.parseInt(new String(bRes));
			return result;
		}
		catch(NumberFormatException ex)
		{
			Console.LogException(ex);
		}
		catch(Exception ex)
		{
			Console.LogException(ex);
		}
		return -1;
	}
	
	//Safety check if string can be parsed
	public static boolean intCanParse(String s){
		try
		{
			int count = 0;
			byte[] allBytes = s.getBytes();
			for (int i = 0; i < allBytes.length; i++) {
				if(allBytes[i] > 0){
					count += 1;
				}
			}
			int current = 0;
			byte[] bRes = new byte[count];
			for (int i = 0; i < allBytes.length; i++) {
				if(allBytes[i] > 0)
				{
				bRes[current] = allBytes[i];
				current += 1;
				}
			}
			
			int result = Integer.parseInt(new String(bRes));
			
			return true;
		}
		catch(NumberFormatException ex)
		{
			Console.LogException(ex);
		}
		catch(Exception ex)
		{
			Console.LogException(ex);
		}
		return false;
	}
	
	public static String[] getFileLines(File f){
		if(!f.exists())
			return null;
		
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(f));

	    	List<String> lines = new ArrayList<String>();
	    	
	        String line = br.readLine();

	        while (line != null) {
	        	lines.add(line);
	            line = br.readLine();
	        }
	        
	        br.close();
	        
	        String[] result = new String[lines.size()];
	        for (int i = 0; i < result.length; i++) {
				result[i] = lines.get(i);
			}
	        return result;
	    }
	    catch(Exception ex){
	    	Console.LogException(ex);
	    }
		
		return null;
	}
}
