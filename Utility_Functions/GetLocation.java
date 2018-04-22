import java.util.*;
public class GetLocation
{
	public static void main(String[] args)
	{	
		Scanner in = new Scanner(System.in);
		//System.out.println("Select class : ");
		//System.out.println("RAPE\nROBBERY\nATTEMPTED ROBBERY\nASSAULT WITH DEADLY WEAPON\nBURGLARY\nBURGLARY FROM VEHICLE\nTHEFT-GRAND ($950.01 & OVER)\nSHOPLIFTING-GRAND THEFT ($950.01 & OVER)\n");
		//System.out.println("THEFT, PERSON\nTHEFT FROM MOTOR VEHICLE - PETTY ($950 & UNDER)\nSHOPLIFTING - PETTY THEFT ($950 & UNDER)\nVEHICLE - STOLEN\nBATTERY - SIMPLE ASSAULT\nINDECENT EXPOSURE\nBATTERY WITH SEXUAL CONTACT\nEXTORTION\n");
		//String Crime = in.nextLine();
		String Crime = "KIDNAPPING";
		
		ArrayList<String> CrimeDescription = new ArrayList<String>();
		int[] CrimeCount = new int[25];
		double count = 0.0;
		
		String input ="";
		while(true)
		{	
			input = in.nextLine();
			if(input.equals("stop"))
				break;
			
			StringTokenizer st = new StringTokenizer(input,",");
			if(st.nextToken().equals(Crime))
				{	CrimeDescription.add("{lat: "+(st.nextToken())+", lng: "+(st.nextToken())+"},");	}
		}
		
		Random rand = new Random();
		int N = 200;/*
		for(int i = 0; i < N; i++)
		{	
			int  n = rand.nextInt(CrimeDescription.size()) + 1;
			System.out.println(CrimeDescription.get(n));	
		}/*/
		for(int i = 0; i < CrimeDescription.size(); i++)
		{	
			//int  n = rand.nextInt(CrimeDescription.size()) + 1;
			if(i%10 == 0)	System.out.println();
			System.out.print(CrimeDescription.get(i)+"\t");	
		}
	}
}