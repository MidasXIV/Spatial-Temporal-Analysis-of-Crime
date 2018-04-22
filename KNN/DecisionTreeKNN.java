import java.util.*;
import java.io.*;
public class DecisionTreeKNN
{
    public static ArrayList <Integer> indexOfAll (String str, ArrayList list)
    {   
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++)
            if(str.equals(list.get(i)))
                indexList.add(i);
        return indexList;
    }
    
    public static double EuclideanDistance(Double a, Double b, Double c, Double d)
    {   return Math.sqrt(Math.pow(a-b,2) + Math.pow(c-d,2));    }
    
    public static void main(String[] args) throws IOException
    {   
                
        ArrayList<String> LearnSetOne    = new ArrayList<String>();
        ArrayList<String> LearnSetCrm    = new ArrayList<String>();
        ArrayList<Double> LearnSetLat    = new ArrayList<Double>();
        ArrayList<Double> LearnSetLng    = new ArrayList<Double>();
        
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String input = "";
        int totalNumData = 0;
        
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
            Double latitudeINP = Double.parseDouble(st.nextToken());
            Double longitudINP = Double.parseDouble(st.nextToken());
            String DecisionTreeText = dayINP+","+monthINP+","+timeslotINP+","+areacodeINP;
            
            LearnSetOne.add(DecisionTreeText);
            LearnSetCrm.add(crimeINP);
            LearnSetLat.add(latitudeINP);
            LearnSetLng.add(longitudINP);
         
            totalNumData++;
        }
        
        //System.out.println("DATA ENTERED");
        
        String InputData;
        Scanner in2 = new Scanner(System.in);
        
        int correct = 0;
        int totz = 0;
        
        while(true)
        {   
            totz++;
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
            Double latitudeINP = Double.parseDouble(st.nextToken());
            Double longitudINP = Double.parseDouble(st.nextToken());
            
            String DecisionTreeText = dayINP+","+monthINP+","+timeslotINP+","+areacodeINP;
            
            ArrayList <Integer> indi = indexOfAll(DecisionTreeText,LearnSetOne);
            
            int KNNsize = 0;
            
            if(indi.size() >= 3)
                KNNsize = 3;
            else
                KNNsize = indi.size();
            
            //System.out.println(indi.size());
            
            HashMap<Double, String> hmap = new HashMap<Double, String>();
            
            
            for(int i = 0; i < indi.size(); i++)
            {   int index = indi.get(i);
                //System.out.println(LearnSetOne.get(index));
                double e = EuclideanDistance(latitudeINP,LearnSetLat.get(index),longitudINP,LearnSetLng.get(index));
                //System.out.println(e +" "+ LearnSetCrm.get(index));
                hmap.put(e,LearnSetCrm.get(index));
            }
            
            Map<Double, String> map = new TreeMap<Double, String>(hmap); 
            Set set2 = map.entrySet();
            Iterator iterator2 = set2.iterator();
            
            //REMOVE TO VIEW ALL K POSSIBLE CRIMES
            
            
            System.out.println("K possible Crimes :");
            while(iterator2.hasNext()) 
            {   Map.Entry me2 = (Map.Entry)iterator2.next();
                System.out.print(me2.getKey() + ": ");
                System.out.println(me2.getValue());
            }
            Set set3 = map.entrySet();
            Iterator iterator3 = set3.iterator();
            Map.Entry me3 = (Map.Entry)iterator3.next();
            
            String prediction = me3.getValue()+"";
            
            System.out.print("\nACTUAL     : "+crimeINP);
            System.out.println("\nPREDICTION : "+me3.getValue()+" @ "+me3.getKey());
            if(crimeINP.equals(prediction))    
			{	correct++;
			}
            
            System.out.println();
			System.out.println("====================================================================================");
        }
		totz--;
        System.out.println("CORRECTLY GUESSED : "+correct);
        System.out.println("ACCURACY          : "+(((double)correct/totz)*100));
        
    }
}
/*
FIRST ARGS : TrainingSet
*/