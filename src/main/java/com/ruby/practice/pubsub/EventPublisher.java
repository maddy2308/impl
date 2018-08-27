package com.ruby.practice.pubsub;

import java.util.Map;

public class EventPublisher implements Publisher {

  @Override
  public void publishEvent(String topic, final Map map) {
    Broker.getInstance().sendMessage(topic, map);
  }
}
