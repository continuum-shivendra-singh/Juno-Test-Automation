package continuum.cucumber.remoteConnection;

import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
public class SSHConnection {
	//connecting to channal
	public static Session session;
	public static ChannelShell channel;
	public static String username = "vagrant";
	public static String password = "vagrant";
	public static String hostname = "192.168.33.10";
		public Channel getChannel() {
		
			if (channel == null || !channel.isConnected()) {
				try {
					channel = (ChannelShell) getSession().openChannel("shell");
					channel.connect();

				} catch (Exception e) {
					System.out.println("Error while opening channel: " + e);
				}
			}
			return channel;
		}
		public static Session getSession() {
			if (session == null || !session.isConnected()) {
				session = connect(hostname, username, password);
			}
			return session;
		}
		public static void close() {
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();
			System.out.println("Disconnected channel and session");
		}
		public static Session connect(String hostname, String username, String password) {

			JSch jSch = new JSch();

			try {

				session = jSch.getSession(username, hostname, 22);
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.setPassword(password);

				System.out.println("Connecting SSH to " + hostname + " - Please wait for few seconds... ");
				session.connect();
				System.out.println("Connected!");
			} catch (Exception e) {
				System.out.println("An error occurred while connecting to " + hostname + ": " + e);
			}

			return session;

		}
		
		public  Session getSessionWindows() throws JSchException {
			JSch jsch=new JSch();  

		      String host="10.2.114.83";	      
		      String user="Administrator";
		      String password="Agent@123";
		     
		      java.util.Properties config = new java.util.Properties(); 
		      config.put("StrictHostKeyChecking", "no");
		      
		      
		      Session session=jsch.getSession(user, host, 22);
		      session.setConfig(config);
		      
		      

		     
		      session.setPassword(password);
		     
				session.connect();
			
			return session;
		}
}
