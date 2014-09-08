package com.datagrid;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import com.datagrid.R;

public class MainActivity extends Activity {
	String result=null;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new InsertOperation().execute();
       
       
    }

    private class InsertOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
 
        	String result = "";
        	
            try {
     
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet("http://10.0.2.2:8080/EmployeeSchedule/employee/schedule"));
              
                BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                String st = "";
                while ((st = rd.readLine()) != null) 
                {
                   result=result+st+"\n";
                }

                            
                
            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
     
            return result;
          
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	int k=0;
        	String res[]=result.split("\n");
        	int len=res.length;
        	 TextView tv2=null;
	        	TableLayout tl = (TableLayout)findViewById(R.id.table1);
	         	
        	while(k<len){
  		    	   TableRow row=new TableRow(getApplicationContext());
  		    	 row.setBackgroundResource(R.drawable.border2);
	        		
  	        	 String res1[]=res[k].split(" ");
  	        	 
  	                  	for(int i=0;i<res1.length; i++){
  	                  	if(i==1){
  	                  		
  	                  		
  	                  	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  	              	String dateInString = res1[i];
  	               
  	              	try {
  	               
  	              		Date date = formatter.parse(dateInString);
  	              	Calendar cal = Calendar.getInstance();
  	              cal.setTime(date);
  	              int day = cal.get(Calendar.DAY_OF_MONTH);
  	              String dd=Integer.toString(day);
                  
  	              	} catch (ParseException e) {
  	              		Log.d("Date Error","Error");
  	              	e.printStackTrace();
  	              	}
  	                  		
  	                  		
  	                  		
  	                  		
  	                  	}else{
  	                  	    tv2=new TextView(getApplicationContext());
  	                  	   
  	        		        if(i==0)
  	        		        {
  	        		         tv2.setWidth(200);
  	    	        		   
  	        		        } 	        		        	
  	        		        else{
  	        		        tv2.setWidth(30);}
  	        		        tv2.setHeight(30);
  	        		      tv2.setTextSize(10);
  	        		    tv2.setPadding(6,6,6,6);
  	        		  tv2.setTextColor(Color.BLACK);
  	        		  
  	        		  tv2.setText(res1[i]);
  	        		tv2.setBackgroundResource(R.drawable.border2);
  	        		
  	                       
  	                      row.addView(tv2);
  	                  	}
  	        	       }
  	        	  tl.addView(row);
  	        	  k++;
        	}
        	      }
    }
    
}
