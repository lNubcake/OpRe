import java.lang.String;


public class ATask {

	String Name;
	int Priority;
	int Start;
	int CPU;
	int Wait;
	
	ATask(String NameToSet, int PriorityToSet,int StartToSet, int CPUToSet)
	{
		Name = NameToSet;
		Priority = PriorityToSet;
		Start = StartToSet;
		CPU = CPUToSet;
		Wait = 0;
	}
}
