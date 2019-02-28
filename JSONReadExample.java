package ReadTwitter;
//Java program to read JSON from a file 

import java.util.Iterator;
import java.util.Map; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
class Info{
    String nome_user;
	int count_tweet;
    long id;
    String screen_name;
}


public class JSONReadExample {
	public static void main(String[] args) throws Exception{ 
		
		if(args != null || args.length < 1) {
			  throw new IllegalArgumentException("Please provide an argument.");
			}
		
		JSONParser parser = new JSONParser();
		String thisLine;
		boolean trovato = false;
		List<Info> DataList = new ArrayList<Info>();
		Info last_0 = new Info();
   	 	DataList.add(last_0);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));

			
			while ((thisLine = br.readLine()) != null) { 
		         
		    Object obj = parser.parse(thisLine);
		    
		    // typecasting obj to JSONObject 
			JSONObject jo = (JSONObject) obj; 
          
			Map user = ((Map)jo.get("user"));
			String nome = (String) user.get("name");
			
			
			if(nome.equalsIgnoreCase(args[1])){
				String screen_name = (String) user.get("screen_name");
				
				Iterator<Map.Entry> itr1 = user.entrySet().iterator(); 
				while (itr1.hasNext()) { 
					Map.Entry pair = itr1.next(); 
					 if (pair.getKey().equals("id")) {
						 long extract_id = (long) pair.getValue();
						 
						 for (int i = 1; i < DataList.size(); i++) {
							 if (DataList.get(i).id == extract_id) {
								 
								 DataList.get(i).count_tweet++;
						    	 trovato = true;
						    	 
						     }
						  }
						 
						 if(trovato == false) {
						 
							 Info last = new Info();
							 last.id = extract_id;
							 last.count_tweet = 1;
							 last.screen_name = screen_name;
							 last.nome_user = nome;
							 DataList.add(last);
						 }
					 }
					}	
				}
		   }
		}catch (IOException e) {
		       System.err.println("Error: " + e);
		       
	     }
		
		for (int i = 1; i < DataList.size(); i++) {
			System.out.println("id : " + DataList.get(i).id);
			System.out.println("count tweet : " + DataList.get(i).count_tweet);
			System.out.println("screen_name : " + DataList.get(i).screen_name);
			System.out.println("name : " + DataList.get(i).nome_user);
			System.out.println("--------");
			System.out.println("--------");

		     }
		
	}

}