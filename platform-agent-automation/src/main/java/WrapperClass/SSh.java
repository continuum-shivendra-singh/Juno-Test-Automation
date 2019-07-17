//package WrapperClass;
//
//import static org.junit.Assert.fail;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.apache.log4j.Logger;
//
//import org.junit.Assert;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelExec;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//
//import com.jcraft.jsch.Session;
//
//
//public class SSh {
//	static Logger log = Logger.getLogger(SSh.class.getName());
//
//	public String delimiter = "\\|";
//
//	private JSch js = null;
//	private Session session = null;
//	private Channel channel = null;
//	private ChannelSftp channelSftp = null;
//	private ChannelExec channelExec = null;
//	private String hostname = null;
//
//	public JSch getJs() {
//		return js;
//	}
//
//	public void setJs(JSch js) {
//		this.js = js;
//	}
//
//	public Session getS() {
//		return session;
//	}
//
//	public void setS(Session session) {
//		this.session = session;
//	}
//
//	public String getHostname() {
//		return hostname;
//	}
//
//	public void setHostname(String hostname) {
//		this.hostname = hostname;
//	}
//
//	public Channel getC() {
//		return channel;
//	}
//
//	public void setC(Channel channel) {
//		this.channel = channel;
//	}
//
//	public ChannelSftp getChannelSftp() {
//		return channelSftp;
//	}
//
//	public void setChannelSftp(ChannelSftp channelSftp) {
//		this.channelSftp = channelSftp;
//	}
//
//	public ChannelExec getChannelExec() {
//		return channelExec;
//	}
//
//	public void setChannelExec(ChannelExec channelExec) {
//		this.channelExec = channelExec;
//	}
//	/*******************************************
//	 * @param ssh
//	 *            username created on remote ssh client
//	 * @param ssh
//	 *            ip of repmote machine
//	 * @param ssh
//	 *            connected port
//	 * @param ssh
//	 *            connection password
//	 *****************************************/
//	
//	/**
//	 * @param String
//	 *            type specifying what terminal output to parse for example
//	 *            service,registry,Batch output etc.
//	 * @param String
//	 *            Command to execute on remote machine terminal
//	 * @return parsed output according to passed first parameter
//	 */
//	public String SshCommandExecution(String commandType, String command) {
//		String strOutput = null;
//
//		if (this.session != null) {
//			try {
//				this.channel = this.getS().openChannel("exec");
//			} catch (JSchException e) {
//				
//				Assert.fail("Failed : Open Channel Exec for command [ " + command + " ] ");
//			}
//			channelExec= (ChannelExec) this.getC(); // ChannelExec ce = (ChannelExec) c;
//
//			System.out.println("Command :" + command.trim());
//			channelExec.setCommand(command.trim());
//			// CustomWait.sleep(1);
//			channelExec.setErrStream(System.err);
//
//			try {
//				channelExec.connect(10 * 1000);
//
//			} catch (JSchException e) {
//				log.error(GlobalVariables.scenario.getName() + "Failed : Connect Channel Exec for command [ " + command
//						+ " ] ");
//				Assert.fail("Failed : Connect Channel Exec for command [ " + command + " ] ");
//			}
//
//			BufferedReader reader = null;
//			try {
//				reader = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
//			} catch (IOException e) {
//				log.error(GlobalVariables.scenario.getName() + "Failed : Create BufferedReader for command [ " + command
//						+ " ] ");
//			}
//			log.info(GlobalVariables.scenario.getName() + "Command is:" + command);
//
//			if (commandType != "None") {
//				strOutput = ParseTerminalOutput(commandType, reader);
//			}
//			// ce.disconnect();
//			// s.disconnect();
//		}
//		return strOutput;
//	}
//
//	
//}
