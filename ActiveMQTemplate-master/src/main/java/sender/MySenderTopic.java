package sender;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySenderTopic {

	public static void main(String[] args) {

		try
		{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Topic queue = (Topic) applicationContext.getBean("queue");
			TopicSession session;
			QueueConnection cnx;
			QueueSender sender;
			TextMessage message;
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
			cnx = (QueueConnection) factory.createConnection();
			System.out.println("Création de la connexion.");	
			
			// Open a session without transaction and acknowledge automatic
			session = (TopicSession) cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
			System.out.println("Création de la session.");
			
			// Start the connection
			cnx.start();
			System.out.println("Lancement de la connexion.");	
			
			// Create a sender
			sender = session.createSubscriber(Topic);
			System.out.println("Sender Créé");
					
			// Create a message
			message = session.createTextMessage("This is the first message. Well done ! ");
			System.out.println("Messsage rédigé.");	
			
			// Send the message
			sender.send(queue, message);
			System.out.println("Message envoyé.");		
			
			// Close the session
			session.close();
			System.out.println("Session terminé.");	
			
			// Close the connection
			cnx.close();
			System.out.println("Connexion fermé.");	

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
