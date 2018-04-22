import java.util.*;
public class CountClass
{
	public static void main(String[] args)
	{	
		Scanner in = new Scanner(System.in);
		String input = "";
		ArrayList<String> CrimeDescription = new ArrayList<String>();
		int[] CrimeCount = new int[25];
		double count = 0.0;
		
		while(true)
		{	
			input = in.nextLine();
			if(input.equals(" "))
				break;
			
			if(!CrimeDescription.contains(input))
			{	CrimeDescription.add(input);	}
			CrimeCount[CrimeDescription.indexOf(input)]++;
			count++;
		}
		for(int i = 0; i < CrimeDescription.size(); i++)
		{	System.out.println(CrimeDescription.get(i)+" "+CrimeCount[i]+" "+((CrimeCount[i]/count)*100));	}
	}
}