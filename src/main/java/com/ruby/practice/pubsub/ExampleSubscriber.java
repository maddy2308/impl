package com.ruby.practice.pubsub;

import java.util.Map;

public class ExampleSubscriber implements Subscriber {

  @Override
  public void update(final Map map) {
    System.out.println("Old -> " + map.get("OLD"));
    System.out.println("New -> " + map.get("NEW"));
  }
}
