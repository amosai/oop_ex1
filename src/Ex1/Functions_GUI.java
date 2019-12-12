package Ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.ui.RefineryUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Functions_GUI extends ArrayList<function> implements functions {

	@Override
	public void initFromFile(String nameFile) throws IOException {
		File file = new File(nameFile); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		ComplexFunction stam= new ComplexFunction(Operation.None);//do that beacose initFromString not static
		
		String st; 
		while ((st = br.readLine()) != null) {
			String []arr =st.split(";");
			for (int i = 0; i < arr.length; i++) {
				function cf = stam.initFromString(arr[i]);
				this.add(cf);
			}
		}	
	} 




	@Override
	public void saveToFile(String nameFile) throws IOException {
		String s=""; 
		s+=this.get(0);
		
		for (int i = 1; i < this.size(); i++) {
			s+=";";
			s+=this.get(i);
			
		}


		//credit to https://www.baeldung.com/java-write-to-file
	    BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile));
	    writer.write(s);
	     
	    writer.close();
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		final XYSeriesDemo demo = new XYSeriesDemo(this,width,height,rx,ry,resolution);
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);

	}

	@Override
	public void drawFunctions(String json_file) {
		
		
         
		try {
			// parsing file "JSONExample.json" 
			Object obj = new JSONParser().parse(new FileReader(json_file));
			
			 // typecasting obj to JSONObject 
	        JSONObject jo = (JSONObject) obj; 
	          
	        // getting firstName and lastName 
	        long width = (long) jo.get("Width"); 
	        long height = (long) jo.get("Height"); 
	        long  resolution = (long) jo.get("Resolution"); 
	        
	        JSONArray range_X  = (JSONArray) jo.get("Range_X"); 
	        long range_X_min = (long)range_X.get(0);
	        long range_X_max = (long)range_X.get(1);
	        
	        JSONArray range_Y  = (JSONArray) jo.get("Range_Y"); 
	        long range_y_min = (long)range_Y.get(0);
	        long range_y_max = (long)range_Y.get(1);
	          
	        drawFunctions((int)width, (int)height, new Range(range_X_min, range_X_max), new Range(range_y_min, range_y_max) , (int)resolution);
	        
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
          
       
          
        
		
	}


}
