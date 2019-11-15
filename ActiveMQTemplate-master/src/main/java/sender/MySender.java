package sender;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			Queue queue = (Queue) applicationContext.getBean("queue");
			QueueSession session;
			QueueConnection cnx;
			QueueSender sender;
			TextMessage message;
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
			cnx = (QueueConnection) factory.createConnection();
			System.out.println("Cr�ation de la connexion.");	
			
			// Open a session without transaction and acknowledge automatic
			session = (QueueSession) cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
			System.out.println("Cr�ation de la session.");
			
			// Start the connection
			cnx.start();
			System.out.println("Lancement de la connexion.");	
			
			// Create a sender
			sender = session.createSender(queue);
			System.out.println("Sender Cr��");
					
			// Create a message
			message = session.createTextMessage("This is the first message. Well done ! ");
			System.out.println("Messsage r�dig�.");	
			
			// Send the message
			sender.send(queue, message);
			System.out.println("Message envoy�.");		
			
			// Close the session
			session.close();
			System.out.println("Session termin�.");	
			
			// Close the connection
			cnx.close();
			System.out.println("Connexion ferm�.");	

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
