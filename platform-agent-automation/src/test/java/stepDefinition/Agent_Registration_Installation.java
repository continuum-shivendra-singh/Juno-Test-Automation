package stepDefinition;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.jcraft.jsch.ChannelExec;


import continuum.cucumber.pages.LinuxCommandExecution;
import continuum.cucumber.pages.Registration_Installation;
import continuum.cucumber.remoteConnection.SSHConnection;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Agent_Registration_Installation {
	
	LinuxCommandExecution LinuxCommandExecution=new LinuxCommandExecution();
	
	public static List<String> lines=null;
	SSHConnection sshConenection=new SSHConnection();
	public static String[] list1=null;
	Registration_Installation registrationInstallation=new Registration_Installation();
	public String line;
	public static com.jcraft.jsch.Channel channel;
	//public static Channel Channel;
	
	
	@Given("^Linux agent present on the machine$")
	public void linux_agent_present_on_the_machine() throws Throwable {
		
		
		  List<String> commands = new ArrayList<String>();
		  
		  commands.add("cd /tmp/sample");
		  commands.add("ls |grep SPTTesting_50110212_LINUX64_SILENT_112.zip");
         lines =LinuxCommandExecution.executeCommands(commands);
		  System.out.println(lines);
		  for(int i=0; i<lines.size();i++) {
			  if(lines.get(i).contains("SPTTesting_50110212_LINUX64_SILENT_112.zip")){
				  System.out.println("linux installer is present");
			  }
			  
		  }
	  
		  //Assert.assertTrue(message, condition);
		 
	}

	@When("^User install agent on machine$")
	public void user_install_agent_on_machine() throws Throwable {
		
		List<String> commands = new ArrayList<String>();
	    commands.add("cd /tmp/sample");

		//commands.add("rm -f ITS247AgentSetup64.bz2.run");

		//commands.add("rm -f ITS247Mobile.ini");
		//commands.add("sudo unzip SPTTesting_50110212_LINUX64_SILENT_112.zip");
		commands.add("sudo su");
		commands.add("chmod 777 ITS247AgentSetup64.bz2.run");
		commands.add("./ITS247AgentSetup64.bz2.run");
		
		
		commands.add("cat /opt/ITSPlatform/config/platform_agent_core_cfg.json | grep EndPointID");
			
		lines =LinuxCommandExecution.executeCommands(commands);
		System.out.println(lines);
		System.out.println(lines);
		
	}

	@Then("^Agent install successfully on the machine$")
	public void agent_install_successfully_on_the_machine() throws Throwable {
		
		List<String> endPoint = lines.subList(lines.size()-1,lines.size());
		for(int i=0;i<endPoint.size();i++){
			list1=endPoint.get(i).split(":");
			if(list1[1].contains("")) {
				System.out.println("Linux Agent is installed and Registered");
			}
			else {
				System.out.println("Linux Agent is installed but not Registered");
			}
		}
		 
		 
	}

	

	@Given("^Windwos agent present on the machine$")
	public void windwos_agent_present_on_the_machine() throws Throwable {
		
		  channel = sshConenection.getSessionWindows().openChannel("exec");
		  
		  List<String> commands = new ArrayList<String>();
		  
		 // String cd="cmd /c cd C:\\Juno-Agent2.0\\Downloads";
		  // String dir ="cmd /c dir";
		  commands.add("cd C:\\Juno-Agent2.0\\Downloads"); 
		  commands.add("cmd /c cd\\ && cd C:\\Juno-Agent2.0\\Downloads && dir Platform-Agent-Exe.exe"
		  );
		 // commands.add("cd");
		  //commands.add(dir);
		  
	  
		  for (String command : commands) { ((ChannelExec)channel).setCommand(command);
		  
		  }
		  
		  ((ChannelExec)channel).setErrStream(System.err);
	  
		  
		  channel.connect();
		  
		  lines=registrationInstallation.readChannelOutputWindows(channel);
		  System.out.println(lines);
		  for(int i=0;i<lines.size();i++) {
			  if(lines.get(i).contains("Platform-Agent-Exe.exe")) {
			  System.out.println("windows installer is present");
				  break;
			  }
			  
		  }
		  
		 
		 
	}

	@When("^User install agent on Windows machine$")
	public void user_install_agent_on_Windows_machine() throws Throwable {
		try {
			channel = sshConenection.getSessionWindows().openChannel("exec");
		List<String> commands = new ArrayList<String>();
		commands.add("echo this is sample command");
		//commands.add("mkdir C:\\\\Juno-Agent2.0\\\\Downloads\\\\shape");
		
        commands.add("cmd /c cd C:\\Juno-Agent2.0\\Downloads && .\\Platform-Agent-Exe.exe /s ENV=QA");
        

       
      
      for (String command : commands) {
      ((ChannelExec)channel).setCommand(command);

      }
      
      ((ChannelExec)channel).setErrStream(System.err);


      channel.connect();
      
	 lines=registrationInstallation.readChannelOutputWindows(channel);
	 System.out.println(lines);
	  
    }
    catch(Exception e){
      System.out.println(e);
    }
	}
	@Then("^Agent install successfully on the window machine$")
	public void agent_install_successfully_on_the_window_machine() throws Throwable {
		channel = sshConenection.getSessionWindows().openChannel("exec");
		String command="cmd /c cd C:\\Program Files\\ITSPlatform\\config && C:\\jq-win32.exe -r .EndPointID platform_agent_core_cfg.json";
		 ((ChannelExec)channel).setCommand(command);
		 channel.connect();
		 lines=registrationInstallation.readChannelOutputWindows(channel);
		 System.out.println(lines);
		if(lines.isEmpty()) {
			  System.out.println("Windows agent is installed but not registered");
		  }
		  else {
			  System.out.println("Windows agent is installed and registered");
			
		  }
		  

	      channel.disconnect();
	      sshConenection.getSessionWindows().disconnect();
	}
	


}
