package ch.bfh.fankf4.jmd.appservices.hello.async;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@EnableAsync
public class AsyncTextReverter {
  @Autowired
  private ThreadPoolTaskExecutor taskExecutor;

  private String text;

  @Scheduled(fixedDelay = 10000)
  public void displayTime() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter a text to be reverted: ");
    text = scanner.next();
    for (int i = 0; i < text.length() -1; i++) {
      taskExecutor.execute(new Reverter(i));
    }
  }

  private class Reverter implements Runnable {
    int start;

    public Reverter(int start) {
      this.start = start;
    }

    @Override
    public void run() {
      String[] chars = text.split("");
      String buff = chars[start];
      chars[start] = chars[start + 1];
      chars[start + 1] = buff;
      text = StringUtils.join(chars, "");
      System.out.print("\r" + text);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
