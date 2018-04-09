import java.lang.String;


public class ATask {

	String Name;
	int Priority;
	int Start;
	int CPU;
	
	ATask(String NameToSet, int PriorityToSet,int StartToSet, int CPUToSet)
	{
		Name = NameToSet;
		Priority = PriorityToSet;
		Start = StartToSet;
		CPU = CPUToSet;
	}
	
	public void test() {
		System.out.println(Name + "      " + Priority + "      " + Start + "       " + "     " + CPU );
	}
}
