package joejava.util;

import joejava.util.bean.EmailProperties;

import org.apache.commons.mail.SimpleEmail;

public class EmailUtility {
	
	/*
	public static void sendSimpleEmail(String to, String from, String subj, String body)
			throws Exception{
		//Properties of sender(gmail acct)
		EmailProperties props = (EmailProperties)DAO.client.queryForObject("getEmailProperties","Gmail");
		System.out.println(StringUtility.printBean(props));
		SimpleEmail email = new SimpleEmail();
		email.setTLS(true);
		email.setDebug(true);
		email.setHostName(props.getSmtpHost());
		email.setAuthentication(props.getUsername(),props.getPassword());
		email.addTo(to);
		email.setFrom(from);
		email.setSubject(subj);
		email.setMsg(body);

		email.getMailSession().getProperties().put("mail.smtp.auth", "true");
		email.getMailSession().getProperties().put("mail.smtp.starttls","true");
		email.send();
	}
	*/
}
