package com.ruby.practice.pubsub;

import java.util.Map;

public interface Publisher {

  void publishEvent(String topic, Map<String, String> map);
}
