import java.util.*;
import java.io.*;
public class BayesianNetworkROC
{
    public static void DisplayArrayList(ArrayList<String> al)
    {
        for(int i = 0; i < al.size();i++)
        {   System.out.println(al.get(i));  }
    }
    public static void DisplayMatrix(int[][] arr,int m,int n,String text)
    {   System.out.println(text);
        for(int i=0;i<m;i++)
        {   for(int j=0;j<n;j++)
            {   System.out.print(arr[i][j]+"\t");   }
            System.out.println();
        }
    }
    
    public static void DisplayProb(ArrayList<String> al1,int[] count_crime,int totalNumData)
    {   for(int i = 0; i < al1.size();i++)
        {   
            System.out.println(al1.get(i)+"\t\t:"+count_crime[i]+" /"+totalNumData);  
        }
    }
    
    public static void main(String[] args) throws IOException
    {   
        //System.out.println(args[0]);
        //System.out.println(args[1]);
        //System.out.println(args[2]);
        
        ArrayList<String> DistinctMonth    = new ArrayList<String>();
        ArrayList<String> DistinctDay      = new ArrayList<String>();
        ArrayList<String> DistinctAreaCode = new ArrayList<String>();
        ArrayList<String> DistinctTimeSlot = new ArrayList<String>();
        ArrayList<String> DistinctCrime    = new ArrayList<String>();
        
        DistinctMonth.add("January");   DistinctMonth.add("February");  DistinctMonth.add("March"); DistinctMonth.add("April");
        DistinctMonth.add("May");       DistinctMonth.add("June");      DistinctMonth.add("July");  DistinctMonth.add("August");
        DistinctMonth.add("September"); DistinctMonth.add("October");   DistinctMonth.add("November");  DistinctMonth.add("December");
        
        DistinctDay.add("Sunday");      DistinctDay.add("Monday");  DistinctDay.add("Tuesday"); DistinctDay.add("Wednesday");
        DistinctDay.add("Thursday");    DistinctDay.add("Friday");  DistinctDay.add("Saturday");
        
        DistinctCrime.add("RAPE");  DistinctCrime.add("ROBBERY");
        DistinctCrime.add("ATTEMPTED ROBBERY");  DistinctCrime.add("ASSAULT WITH DEADLY WEAPON");
        DistinctCrime.add("BURGLARY");  DistinctCrime.add("VEHICLE BURGLARY");
        DistinctCrime.add("THEFT (GRAND)");  DistinctCrime.add("PICKPOCKETING");
        DistinctCrime.add("SHOPLIFTING (GRAND)");  DistinctCrime.add("THEFT FROM VEHICLE (PETTY)");
        DistinctCrime.add("SHOPLIFTING (PETTY)");  DistinctCrime.add("STOLEN VEHICLE");
        DistinctCrime.add("ASSAULT");  DistinctCrime.add("INDECENT EXPOSURE");
        DistinctCrime.add("BATTERY WITH SEXUAL CONTACT");  DistinctCrime.add("KIDNAPPING");
        DistinctCrime.add("EXTORTION");  
        
        for(int i=1; i <= 21; i++)  {   DistinctAreaCode.add(i+"");   }
        
        for(int i=1; i <= 8; i++)   {   DistinctTimeSlot.add("T"+i);   }
        
        /*
        // REMOVE TO VIEW THE DATA ENTERED
        
        DisplayArrayList(DistinctMonth);
        DisplayArrayList(DistinctDay);
        DisplayArrayList(DistinctAreaCode);
        DisplayArrayList(DistinctTimeSlot);
        DisplayArrayList(DistinctCrime);
        */
        int[][] prob_month    = new int[12][17];
        int[][] prob_day      = new int[7][17];
        int[][] prob_areacode = new int[21][17];
        int[][] prob_timeslot = new int[8][17];
        int[]   count_crime   = new int[17];
        int     totalNumData  = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String input = "";
        while(true)
        {   input = br.readLine();
            if(input.equals("stop"))
                break;
            //TO VIEW THE DATA FROM FILE
            //System.out.println(input);
            StringTokenizer st = new StringTokenizer(input,",");               
            String dayINP = st.nextToken();
            String monthINP = st.nextToken();
            String timeslotINP = st.nextToken(); 
            String areacodeINP = st.nextToken();                             
            String crimeINP = st.nextToken();
         
            prob_day[DistinctDay.indexOf(dayINP)][DistinctCrime.indexOf(crimeINP)]++;
            prob_month[DistinctMonth.indexOf(monthINP)][DistinctCrime.indexOf(crimeINP)]++;   
            prob_areacode[DistinctAreaCode.indexOf(areacodeINP)][DistinctCrime.indexOf(crimeINP)]++;
            prob_timeslot[DistinctTimeSlot.indexOf(timeslotINP)][DistinctCrime.indexOf(crimeINP)]++;
            count_crime[DistinctCrime.indexOf(crimeINP)]++;
            totalNumData++;
        }
        
        System.out.println("DATA ENTERED");
        DisplayMatrix(prob_month,12,17,"MONTH");
        DisplayMatrix(prob_day,7,17,"DAY");
        DisplayMatrix(prob_timeslot,8,17,"TIME_SLOT");
        DisplayMatrix(prob_areacode,21,17,"AREA_CODE");
        DisplayProb(DistinctCrime,count_crime,totalNumData);
        
        String InputData;
        Scanner in2 = new Scanner(System.in);
        
        int[][] ROCData = new int[17][17];
        
        int correct = 0;
        int totz = 0;
		while(true)
        {	totz++;
            InputData = in2.nextLine();
            if(InputData.equals("stop"))
                break;
			
			System.out.println("====================================================================================");
			System.out.println("INPUT DATA : "+InputData);
			System.out.println();
			
            StringTokenizer st = new StringTokenizer(InputData,",");               
            String dayINP      = st.nextToken();
            String monthINP    = st.nextToken();
            String timeslotINP = st.nextToken(); 
            String areacodeINP = st.nextToken();
            String crimeINP    = st.nextToken();

            double[] allprobs = new double[17];
        
            for(int i=0; i < DistinctCrime.size();i++)
            {   
                int index = DistinctCrime.indexOf(DistinctCrime.get(i));

                double a = (double)prob_day[DistinctDay.indexOf(dayINP)][index] / (double)count_crime[DistinctCrime.indexOf(DistinctCrime.get(i))];
                double b = (double)prob_month[DistinctMonth.indexOf(monthINP)][index] / (double)count_crime[DistinctCrime.indexOf(DistinctCrime.get(i))];   
                double c = (double)prob_areacode[DistinctAreaCode.indexOf(areacodeINP)][index] / (double)count_crime[DistinctCrime.indexOf(DistinctCrime.get(i))];
                double d = (double)prob_timeslot[DistinctTimeSlot.indexOf(timeslotINP)][index] / (double)count_crime[DistinctCrime.indexOf(DistinctCrime.get(i))];

                double e = (double)count_crime[index] / (double)totalNumData;

                double p_crime = a*b*c*d*e;

                allprobs[i] = p_crime;

                //System.out.println(a+" "+b+" "+c+" "+d+" "+e);

                System.out.println(DistinctCrime.get(i)+" : "+p_crime);
            }

            double max = allprobs[0];
            int valr = -1;

            for(int i =0; i < 17; i++)
            {   if(allprobs[i] >= max)
                {
                    max = allprobs[i];
                    valr = i;
                }
            }
            System.out.println("\nACTUAL     : "+crimeINP);
            System.out.println("\nPREDICTION : "+DistinctCrime.get(valr));
            //System.out.println(DistinctCrime.get(valr)+" "+allprobs[valr]);
            System.out.println();
            if(crimeINP.equals(DistinctCrime.get(valr)))
			{	correct++;
				//System.out.println(InputData);
			}
            //x -> actual y -> predicted
            ROCData[DistinctCrime.indexOf(crimeINP)][valr]++;
			System.out.println();
			System.out.println("====================================================================================");
        }
		totz--;
		System.out.println();
        System.out.println("CORRECTLY GUESSED : "+correct);
        System.out.println("ACCURACY          : "+((double)correct/totz)*100);
        for(int i=0;i<17;i++)
        {   System.out.println();
            for(int j=0;j<17;j++)
            {   System.out.print(ROCData[i][j]+"\t");
            
            }
        }
    }
}
/*
FIRST ARGS : TrainingSet
*/