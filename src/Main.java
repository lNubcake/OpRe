import java.io.IOException;
import java.util.*;


public class Main {
	
	static ArrayList<ATask> Tasks = new ArrayList<ATask>();

	public static void main(String[] args) {
		
		Scanner myScanner = new Scanner(System.in);
		ArrayList<String> TextToProcess = new ArrayList<String>();
		
		// Scanning in text till EOF
		while(myScanner.hasNextLine())
		{
			TextToProcess.add(myScanner.nextLine());
		}
		myScanner.close();
		// Converts the text to tasks
		try{
		Tasks = TextProcessor(TextToProcess);
		}catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		// Processes the tasks and gives the output
		TaskProcessor(Tasks);
	}
	
	public static ArrayList<ATask> TextProcessor(ArrayList<String> TextToProcess)
	{
		ArrayList<ATask> Result = new ArrayList<ATask>();
		
		for(String currentLine : TextToProcess)
		{
				String[] split = currentLine.split(",");
				if(split.length == 4)
				{
				ATask TaskToAdd = new ATask(split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2]),Integer.parseInt(split[3]));
				Result.add(TaskToAdd);
				}
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
						if(Kernel.isEmpty())
						{
							RRtime = 2;
							User.add(User.pollFirst());
						}
					}
					if(User.contains(task))
					{
						User.remove(task);
					}
				}
			}
		
			boolean UserFlag = false;
			for(ATask task : TasksToWorkWith)
			{
				if(task.Priority == 0 && !Kernel.contains(task))
				{
					Kernel.add(task);
				}
				if(task.Priority == 1 && !User.contains(task))
				{
					User.add(task);
					UserFlag = true;
				}
			}
			if(UserFlag && RRtime == 0)
			{
				RRtime = 2;
				User.add(User.pollFirst());
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
				
					if(!firstLine.isEmpty())
					{
						if(!Kernel.get(0).Name.equals(firstLine.substring(firstLine.length() - 1)))
							firstLine += Kernel.get(0).Name;
					}
					else if(firstLine.isEmpty()){
						firstLine += Kernel.get(0).Name;
					}
					Kernel.get(0).CPU -= 1;
					for(ATask task : TasksToWorkWith)
					{
						if(!Kernel.get(0).equals(task))
						{
							task.Wait += 1;
						}
					}
			}
			else if(!User.isEmpty())
			{
				// RR: Round robin scheduling
				
				if(RRtime == 0)
				{
					RRtime = 2;
					User.add(User.pollFirst());
				}
				
				if(!firstLine.isEmpty())
				{
					if(!User.get(0).Name.equals(firstLine.substring(firstLine.length() - 1)))
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
				
				for(ATask task : TasksToWorkWith)
				{
					if(!User.get(0).equals(task))
					{
						task.Wait += 1;
					}
				}
			}
		}
		
		for(ATask task : Tasks)
		{
			secondLine += (task.Name + ":" + task.Wait + ",");
		}
		// Cutting down the last ','
		if(!secondLine.equals(""))
			secondLine = secondLine.substring(0, secondLine.length()-1);
		
		System.out.println(firstLine);
		System.out.println(secondLine);
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
