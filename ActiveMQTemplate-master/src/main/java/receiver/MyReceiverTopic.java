package receiver;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiverTopic {

	public static void main(String[] args) {
		
		try
		{
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");			
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Topic topic = (Topic) applicationContext.getBean("topic");
			TopicConnection cnx;
			TopicPublisher receiver;
			TopicSession session;
			Message receivedMessage;
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			cnx = (TopicConnection) factory.createConnection();
			System.out.println("Création de la connexion.");	
			
			// Open a session
			session = (TopicSession) cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
			System.out.println("Session ouverte.");
			
			// start the connection	
			cnx.start();
			System.out.println("Connexion ouverte.");
			
			// Create a receive	
			receiver = session.createPublisher(topic);
			System.out.println("Receiver créé.");
			
			// Receive the message
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
