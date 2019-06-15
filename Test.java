package alignstuff;

public class Test 
{
	public Test()
	{
	}
	
	public static void main(String[] args)
	{
		String test1 = "duck";
		String test2 = "trump";
		Align y = new Align();
		int output = y.globalAlign(test1, test2);
		System.out.println("traceback score: "+output);
		y.printAlignment();
	}
}
