package joejava.util.bean;

public class EmailProperties {
	private String provider;
	private String transport;
	private String username;
	private String password;
	private String inbox;
	private String sent;
	private String imapHost;
	private String smtpHost;
	private int smtpPort;
	private String pop3Host;
	
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInbox() {
		return inbox;
	}
	public void setInbox(String inbox) {
		this.inbox = inbox;
	}
	public String getSent() {
		return sent;
	}
	public void setSent(String sent) {
		this.sent = sent;
	}
	public String getImapHost() {
		return imapHost;
	}
	public void setImapHost(String imapHost) {
		this.imapHost = imapHost;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getPop3Host() {
		return pop3Host;
	}
	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
}
