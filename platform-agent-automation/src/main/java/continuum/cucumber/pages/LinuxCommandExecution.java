package continuum.cucumber.pages;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.Channel;

import continuum.cucumber.remoteConnection.SSHConnection;

public class LinuxCommandExecution {
	
	//executing lenux command
	//reading from channel and getting end point id
	//
	Registration_Installation registration_Installation=new Registration_Installation();
	public List<String> executeCommands(List<String> commands) {
		SSHConnection sshConnection=new SSHConnection();
		List<String> lines = null;
		try {
			Channel channel = sshConnection.getChannel();

			System.out.println("Sending commands...");
			sendCommands(channel, commands);

			lines = registration_Installation.readChannelOutput(channel);
			System.out.println(lines);
			System.out.println("Finished sending commands!");

		} catch (Exception e) {
			System.out.println("An error ocurred during executeCommands: " + e);
		}
		return lines;
	}

	//this method is used for sending command on lenux machine
	private static void sendCommands(Channel channel, List<String> commands) {

		try {
			PrintStream out = new PrintStream(channel.getOutputStream());

			out.println("#!/bin/bash");
			for (String command : commands) {
				out.println(command);
			}
			out.println("exit");

			out.flush();
		} catch (Exception e) {
			System.out.println("Error while sending commands: " + e);
		}

	}
	
}
