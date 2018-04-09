import java.io.IOException;
import java.util.*;


public class Main {
	
	static ArrayList<ATask> ArrayOfTasks = new ArrayList<ATask>();
	

	public static void main(String[] args) {
		
		Scanner myScanner = new Scanner(System.in);
		ArrayList<String> TextToProcess = new ArrayList<String>();
		
		
		while(myScanner.hasNextLine())
		{
			TextToProcess.add(myScanner.nextLine());
		}
		myScanner.close();
		
		ArrayOfTasks = TextProcessor(TextToProcess);
	}
	
	public static ArrayList<ATask> TextProcessor(ArrayList<String> TextToProcess)
	{
		ArrayList<ATask> Result = new ArrayList<ATask>();
		
		for(String currentLine : TextToProcess)
		{
			String[] split = new String[4];
			split = currentLine.split(",");
			Result.add(new ATask(split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2]),Integer.parseInt(split[3])));
		}
		
		return Result;
	}

}
