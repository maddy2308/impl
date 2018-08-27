package com.ruby.practice.pubsub;

import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Map<String, String> map = new HashMap<>();
    map.put("OLD","Yes");
    map.put("NEW","No");

    Broker.getInstance().register(Broker.ON_CACHE_RESET, new ExampleSubscriber());

    // after 5 seconds delay i publish an event to which there is a subscriber listening to
    Thread.sleep(5000);

    new EventPublisher().publishEvent(Broker.ON_CACHE_RESET, map);
  }
}
