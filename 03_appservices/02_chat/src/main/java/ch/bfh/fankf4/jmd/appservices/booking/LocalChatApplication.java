package ch.bfh.fankf4.jmd.appservices.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.util.*;


@SuppressWarnings("Duplicates")
@SpringBootApplication
public class LocalChatApplication implements CommandLineRunner {

  @Value("${destination}")
  private String destination;

  @Value("${sender}")
  private String sender;

  private Map history = new LinkedHashMap<Long, String>()
  {
    @Override
    protected boolean removeEldestEntry(Map.Entry<Long, String> eldest)
    {
      return this.size() > 10;
    }
  };

  private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(LocalChatApplication.class, args).close();
	}

	@Autowired
  public LocalChatApplication(JmsTemplate jmsTemplate) {
	  this.jmsTemplate = jmsTemplate;
  }


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Chat Application");

    Message reply = jmsTemplate.sendAndReceive(destination, session -> {
        Message m = session.createMessage();
        m.setJMSType("History");
        return m;
      });
    List<String> history = (List<String>) ((ObjectMessage) reply).getObject();
    for (String h : history) {
      System.out.println(h);
      addToHistory(h);
    }

		Scanner scanner = new Scanner(System.in);
    //System.out.print("Message: ");
		while (true) {
      String text = scanner.nextLine();
      if (text.equals("exit")) {
        break;
      }
      //String[] s = text.split(" ");
      //jmsTemplate.convertAndSend(s[0], s[1]);
      //jmsTemplate.convertAndSend(destination, text);
      jmsTemplate.send(destination, session -> {
        Message m = session.createTextMessage(text);
        m.setJMSType("Chat");
        m.setStringProperty("sender", sender);
        return m;
      });
      //System.out.print("Message: ");
    }
	}

	@JmsListener(destination = "${destination}", selector = "JMSType = 'History'")
  public void onHistoryRequest(Message message) throws JMSException {
	  jmsTemplate.send(message.getJMSReplyTo(), session -> {
      ObjectMessage reply = session.createObjectMessage(new ArrayList(history.values()));
      reply.setJMSCorrelationID(message.getJMSMessageID());
      return reply;
    });
  }

	@JmsListener(destination = "${destination}", selector = "JMSType = 'Chat'")
	//@JmsListener(destination = "OtherQueue")
  public void onMessage(TextMessage message) {
	  try {
      /*// Logging
        System.out.println("LOG> #Received message#");
        Enumeration props = message.getPropertyNames();
        while (props.hasMoreElements()) {
          Object o = props.nextElement();
          System.out.println("LOG>> " + o + ": " + message.getObjectProperty(o.toString()));
        }
      */

      String log;
      String theSender = message.getStringProperty("sender");
      /**
      if (theSender == null) {
        log = "(Anonymous): " + message.getText();
      } else if (theSender.equals(sender)) {
        log = "(" + theSender + ": "  + message.getText() + ")";
      } else {
        log = theSender + ": " + message.getText();
      }
       */
      log = theSender + ": " + message.getText();
      addToHistory(log);
      System.out.print("\r");
      System.out.println(log);


    } catch (Exception e) {
	    System.out.println("Error: " + e.getMessage());
    }
  }

  private void addToHistory(String log) {
    history.put(System.currentTimeMillis(), log);
  }
}
