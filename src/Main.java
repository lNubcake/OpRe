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
			ArrayList<ATask> TasksToWorkWith = new ArrayList<ATask>();
			for(ATask task : Tasks)
			{
				// It's already sorted based on Start time
				if(task.Start <= time && task.CPU != 0)
				{
					TasksToWorkWith.add(task);
				}
				
			}
			
			ArrayList<ATask> Kernel = new ArrayList<ATask>();
			ArrayList<ATask> User = new ArrayList<ATask>();
			for(ATask task : TasksToWorkWith)
			{
				if(task.Priority == 0)
				{
					Kernel.add(task);
				}
				if(task.Priority == 1)
				{
					User.add(task);
				}
				// Still sorted based on Start time
			}
			if(!Kernel.isEmpty())
			{
				// SRTF: Shortest remaining time first
				RRtime = 2;
				if(!firstLine.isEmpty())
				{
					if(SRTF(Kernel).Name != firstLine.substring(firstLine.length() - 1);
				}
				SRTF(Kernel).CPU -= 1;
			}
			else 
			{
				if(!User.isEmpty())
				{
					// TODO implement this
					// RR: Round robin scheduling
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
	
	public static ATask SRTF(ArrayList<ATask> Tasks)
	{
		ATask Result = Tasks.get(0);
		
		for(int i = 0; i < Tasks.size(); i++)
		{
			if(Tasks.get(i).CPU > Tasks.get(i+1).CPU)
				Result = Tasks.get(i+1); 
		}
		
		return Result;
	}
}
