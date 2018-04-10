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
		ArrayList<ATask> Kernel = new ArrayList<ATask>();
		LinkedList<ATask> User = new LinkedList<ATask>();
		
		// As time passes
		for(int time = 0; !isFinished(Tasks); time++)
		{	
			ArrayList<ATask> TasksToWorkWith = new ArrayList<ATask>();
			for(ATask task : Tasks)
			{
				if(task.Start <= time && task.CPU != 0)
				{
					TasksToWorkWith.add(task);
				}
				if(task.CPU == 0)
				{
					if(Kernel.contains(task))
					{
						Kernel.remove(task);
					}
					if(User.contains(task))
					{
						User.remove(task);
					}
				}
			}
		
			for(ATask task : TasksToWorkWith)
			{
				if(task.Priority == 0 && !Kernel.contains(task))
				{
					Kernel.add(task);
				}
				if(task.Priority == 1 && !User.contains(task))
				{
					User.add(task);
				}
			}
			
			// TODO handle exception
			Collections.sort(Kernel , new Comparator<ATask>() {
				@Override
				public int compare(ATask lhs, ATask rhs) {
					return lhs.CPU < rhs.CPU ? -1 : (lhs.CPU > rhs.CPU ? 1 : 0);
				}
			});

			
			if(!Kernel.isEmpty())
			{
				// SRTF: Shortest remaining time first
				// TODO remember sorting the list. If a task is terminated, it goes last on the list if the CPU times equals
				
				RRtime = 2;
				
					if(!firstLine.isEmpty())
					{
						String temp = firstLine.substring(firstLine.length()-1);
						if(!Kernel.get(0).Name.equals(temp))
							firstLine += Kernel.get(0).Name;
					}
					else if(firstLine.isEmpty()){
						firstLine += Kernel.get(0).Name;
					}
					Kernel.get(0).CPU -= 1;
			}
			else 
			{
				// RR: Round robin scheduling
				
				if(!firstLine.isEmpty())
				{
					String temp = firstLine.substring(firstLine.length()-1);
					if(!User.get(0).Name.equals(temp))
					{
						firstLine += User.get(0).Name;
					}
				}
				else if(firstLine.isEmpty())
				{
					firstLine += User.get(0).Name;
				}
				
				User.getFirst().CPU -= 1;
				RRtime -= 1;
				
				if(RRtime == 0)
				{
					RRtime = 2;
					User.add(User.pollFirst());
				}
			}
		}
		System.out.println(firstLine);
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
