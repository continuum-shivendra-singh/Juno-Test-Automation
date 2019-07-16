/*
 * package continuum.cucumber.remoteConnection;
 * 
 * 
 * import java.util.ArrayList; import java.util.List; import
 * java.util.Properties;
 * 
 * import com.jcraft.jsch.Channel; import com.jcraft.jsch.ChannelSftp; import
 * com.jcraft.jsch.ChannelShell; import com.jcraft.jsch.JSch; import
 * com.jcraft.jsch.JSchException; import com.jcraft.jsch.Session; import
 * com.jcraft.jsch.SftpException; import java.io.*;
 * 
 * 
 * 
 * public class Ssh {
 * 
 * private static Session session; private static ChannelShell channel; private
 * static String username = "vagrant"; private static String password =
 * "vagrant"; private static String hostname = "192.168.33.10";
 * 
 * public static void main(String[] args) throws IOException {
 * 
 * 
 * String line; List<String> commands = new ArrayList<String>();
 * commands.add("cd /tmp/sample");
 * 
 * commands.add("rm -f ITS247AgentSetup64.bz2.run");
 * 
 * commands.add("rm -f ITS247Mobile.ini");
 * commands.add("sudo unzip SPTTesting_50110212_LINUX64_SILENT_112.zip");
 * commands.add("sudo su");
 * commands.add("chmod 777 ITS247AgentSetup64.bz2.run");
 * commands.add("bash ./ITS247AgentSetup64.bz2.run"); commands.
 * add("cat /opt/ITSPlatform/config/platform_agent_core_cfg.json | grep -i endpointID"
 * );
 * 
 * line =executeCommands(commands); System.out.println(line); String[] list1 =
 * line.split(":"); System.out.println(list1[0]+" "+list1[1]);
 * if(list1[1].isEmpty()) {
 * System.out.println("platform is installed but not registered"); } else {
 * System.out.println("Agent is installed and registered"); }
 * 
 * close(); }
 * 
 * private static Session getSession() { if (session == null ||
 * !session.isConnected()) { session = connect(hostname, username, password); }
 * return session; }
 * 
 * 
 * //connecting to channal public static Channel getChannel() { if (channel ==
 * null || !channel.isConnected()) { try { channel = (ChannelShell)
 * getSession().openChannel("shell"); channel.connect();
 * 
 * } catch (Exception e) { System.out.println("Error while opening channel: " +
 * e); } } return channel; }
 * 
 * Created by - Shivendra Singh Method to connect session with hostname,
 * username and password
 * 
 * public static Session connect(String hostname, String username, String
 * password) {
 * 
 * JSch jSch = new JSch();
 * 
 * try {
 * 
 * session = jSch.getSession(username, hostname, 22); Properties config = new
 * Properties(); config.put("StrictHostKeyChecking", "no");
 * session.setConfig(config); session.setPassword(password);
 * 
 * System.out.println("Connecting SSH to " + hostname +
 * " - Please wait for few seconds... "); session.connect();
 * System.out.println("Connected!"); } catch (Exception e) {
 * System.out.println("An error occurred while connecting to " + hostname + ": "
 * + e); }
 * 
 * return session;
 * 
 * }
 * 
 * //executing lenux command //reading from channel and getting end point id //
 * public static String executeCommands(List<String> commands) { String line =
 * ""; try { Channel channel = getChannel();
 * 
 * System.out.println("Sending commands..."); sendCommands(channel, commands);
 * 
 * line = readChannelOutput(channel); System.out.println(line);
 * System.out.println("Finished sending commands!");
 * 
 * } catch (Exception e) {
 * System.out.println("An error ocurred during executeCommands: " + e); } return
 * line; }
 * 
 * 
 * Created By - Shivendra Singh Method to send command to remote machine
 * 
 * public static void sendCommands(Channel channel, List<String> commands) {
 * 
 * try { PrintStream out = new PrintStream(channel.getOutputStream());
 * 
 * out.println("#!/bin/bash"); for (String command : commands) {
 * out.println(command); } out.println("exit");
 * 
 * out.flush(); } catch (Exception e) {
 * System.out.println("Error while sending commands: " + e); }
 * 
 * }
 * 
 * Created By - Shivendra Singh Method to copy Agent from Linux machine to
 * Windows VM
 * 
 * public static void copyFileFromLinuxToWindows() throws JSchException,
 * SftpException { String copyTo = "D:\\"; String copyFrom =
 * "/home/vagrant/sample.txt";
 * 
 * System.out.println("Sending commands..."); session = getSession(); Channel
 * channel = session.openChannel("sftp"); channel.connect(); ChannelSftp
 * sftpChannel = (ChannelSftp) channel; sftpChannel.get(copyFrom, copyTo);
 * sftpChannel.exit(); channel.disconnect(); }
 * 
 * Created By - Shivendra Singh Method to close channel connection
 * 
 * public static void close() { if (channel != null) channel.disconnect(); if
 * (session != null) session.disconnect();
 * System.out.println("Disconnected channel and session"); }
 * 
 * }
 */