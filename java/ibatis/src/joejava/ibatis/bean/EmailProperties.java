package joejava.ibatis.bean;

public class EmailProperties {
	private String provider;
	private String transport;
	private String username;
	private String password;
	private String inbox;
	private String sent;
	private String imap_host;
	private String smtp_host;
	private String pop3_host;
	
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
	public String getImap_host() {
		return imap_host;
	}
	public void setImap_host(String imap_host) {
		this.imap_host = imap_host;
	}
	public String getSmtp_host() {
		return smtp_host;
	}
	public void setSmtp_host(String smtp_host) {
		this.smtp_host = smtp_host;
	}
	public String getPop3_host() {
		return pop3_host;
	}
	public void setPop3_host(String pop3_host) {
		this.pop3_host = pop3_host;
	}
}
