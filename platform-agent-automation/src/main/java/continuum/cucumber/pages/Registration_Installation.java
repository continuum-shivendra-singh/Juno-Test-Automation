package continuum.cucumber.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.Channel;

public class Registration_Installation {

	/* Created by - Shivendra Singh
	 * Method to read data from remote machine
	 */
	// fetching data from remote machine
		public static List<String> readChannelOutput(Channel channel) {

			byte[] buffer = new byte[1024];
			List<String> lines = new ArrayList<String>();

			try {
				InputStream in = channel.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				
				String temp;
					while ((temp = reader.readLine()) != null) {
						
						System.out.println(temp);
						lines.add(temp);
						if(temp.endsWith(",")) break;
					}} catch (Exception e) {
					System.out.println("Error while reading channel output: " + e);
				}
			System.out.println(lines);
			return lines;
		
		}
		public List<String> readChannelOutputWindows(Channel channel) throws IOException {
			List<String> windowsLines = new ArrayList<String>();
			InputStream in=channel.getInputStream();
		      BufferedReader reader = new BufferedReader(new InputStreamReader(in)); 
			  String line="";
			 
			while((line= reader.readLine()) !=null) {
			System.out.println(line);
				
			windowsLines.add(line);
			}
			  //System.out.println(line); 
			 // System.out.println(line);
			  byte[] tmp=new byte[1024000];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          if(in.available()>0) continue; 
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		      
			return windowsLines;
		}
		
}
