package automation_package;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerActiveMq {
	 private String url;
	 static int count=0;
		    // Connection object
		    private Connection connection;

		    // Session Object
		    private Session session;

		    public ConsumerActiveMq(String urlParam)
		    {
		        url = urlParam;
		    }

		    public void initiateConnection() throws JMSException
		    {
		        // Getting JMS connection from the server
		        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		        connection = connectionFactory.createConnection();
		        connection.start();
		        // Creating session for sending messages
		        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    }

		    public String getMessageFromQueue(String subject) throws JMSException
		    {
		        Destination destination = session.createQueue(subject);

		        // MessageConsumer is used for receiving (consuming) messages
		        MessageConsumer consumer = session.createConsumer(destination);

		        // Here we receive the message.
		        // By default this call is blocking, which means it will wait
		        // for a message to arrive on the queue.
		        Message message = consumer.receive();
		        //Message message = consumer.receiveNoWait();
		        // There are many types of Message and TextMessage
		        // is just one of them. Producer sent us a TextMessage
		        // so we must cast to it to get access to its .getText()
		        // method.
		        String queueMessage = null;
		        if (message instanceof TextMessage)
		        {
		            TextMessage textMessage = (TextMessage) message;
		            queueMessage = textMessage.getText();
	                count++;	        
		        }
		        consumer.close();
		        return queueMessage;
		    }

		    public void closeConnection() throws JMSException
		    {
		        session.close();
		        connection.close();
		    }
	        public int returnCount() 
	        {
	        	return count;
	        }
	        public void setCount()
	        {
	        	count=0;
	        	
	        }
}
