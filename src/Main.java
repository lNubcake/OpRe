import java.io.IOException;
import java.util.*;


public class Main {
	
	static ArrayList<ATask> Tasks = new ArrayList<ATask>();

	public static void main(String[] args) {
		
		Scanner myScanner = new Scanner(System.in);
		ArrayList<String> TextToProcess = new ArrayList<String>();
		
		
		while(myScanner.hasNextLine())
		{
			TextToProcess.add(myScanner.nextLine());
		}
		myScanner.close();
		
		Tasks = TextProcessor(TextToProcess);
		TaskProcessor(Tasks);
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
	
	public static void TaskProcessor(ArrayList<ATask> Tasks)
	{
		/*
		 * If priority = 0 --> SRTF: Shortest remaining time first
		 * If priority = 1 --> RR: Round robin scheduling
		 */
		String firstLine = new String("");
		String secondLine = new String("");
		
		Collections.sort(Tasks , new Comparator<ATask>() {
			@Override
			public int compare(ATask lhs, ATask rhs) {
				return lhs.Start < rhs.Start ? -1 : (lhs.Start > rhs.Start ? 1 : 0);
			}
		});
		
		int RRtime = 2;
		// As time passes
		for(int time = 0; isFinished(Tasks); time++)
		{
			for(ATask task : Tasks)
			{
				if(task.Start <= time)
				{
					
				}
			}
		}
/*
		for(ATask test : Tasks)
		{
			test.test();
		}*/
	}
	
	public static boolean isFinished(ArrayList<ATask> Tasks)
	{
		for(ATask task : Tasks)
		{
			if(task.CPU != 0)
				return false;
		}
		return true;
	}
}
