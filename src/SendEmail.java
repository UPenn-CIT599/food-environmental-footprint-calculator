import java.io.File;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.sun.mail.util.MailSSLSocketFactory;
/**
 *@author Xiaolu
 * 
 */
public class SendEmail {
	
	 public static void main(String [] args) throws GeneralSecurityException {
		 SendEmail e = new SendEmail("selphie.dou@icloud.com");//my email for testing.
	 }
	
	public SendEmail(String mailaddress) throws GeneralSecurityException {
		// Sent to
        String to = mailaddress;

        // From
        String from = "50505632@qq.com";//this is my qq email address

        // the host of sender's email address(now is my qq mail server)
        String host = "smtp.qq.com"; 

        // get the system properties
        Properties properties = System.getProperties();

        // set the host server
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        //set the SSL encryption
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // get default session
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
        public PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication("50505632@qq.com", "yvbgqwvodarebifc"); //sender's username and password
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            //set from, to and subject
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your Personal Report of Eco-Diet.");
            // set bodypart
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Dear" + "userinputname");
            //set muti body part to add attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);//add the text part
            //add the attachment
            messageBodyPart = new MimeBodyPart();
            // the PDF absolute address 
            File file = new File("/Users/hastuko/eclipse-workspace/Final Project 591/GreenfoodSuggestion.pdf");//add the address of the pdf(generated)
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            String filename = "report";//set the name for the attachment
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
         
            // set whole message part
            message.setContent(multipart );
 
            // send message
            Transport.send(message);
            System.out.println("Sent message successfully!");
         }catch (MessagingException mex) {
            mex.printStackTrace();
         }

	}

   
        
}
