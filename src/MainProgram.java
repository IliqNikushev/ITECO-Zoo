import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

public class MainProgram {
	public static int MONTHS_IN_A_YEAR = 12;
	
	public static void main(String[] arguments){
		try {
			Console.out = new PrintStream(System.out, true, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "";
		File inputsFile = new File(path);
		while(!inputsFile.exists()){
			try{
				path = JOptionPane.showInputDialog("Enter the input file path ( see in the local src\\input folder )");
				inputsFile = new File(path);
			}
			catch(NullPointerException ex){
				return;
			}
		}
		
		Zoo itecoZoo = new Zoo(inputsFile);
		itecoZoo.solve();
		JOptionPane.showMessageDialog(null, "Results are in the console");
	}
}
