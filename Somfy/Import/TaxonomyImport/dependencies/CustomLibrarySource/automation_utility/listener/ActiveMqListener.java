package automation_utility.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ActiveMqListener implements MessageListener
{

    private String message;

    @Override
    public void onMessage (Message message) {
        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {

                System.out.printf("Message received: %s, Thread: %s%n",
                                  tm.getText(),
                                  Thread.currentThread().getName());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getMessage()
    {
        return message;
    }

}
