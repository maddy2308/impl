package com.ruby.practice.pubsub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Broker {

  static final String ON_CACHE_RESET = "onCacheReset";

  private static Broker brokerInstance;
  private final Object mutex = new Object();
  private Map<String, Set<Subscriber>> subscribers;

  private Broker(){
    subscribers = new HashMap<>();
  }

  static Broker getInstance(){
    if(null== brokerInstance){
      brokerInstance = new Broker();
    }
    return brokerInstance;
  }

  boolean register(String topic, Subscriber subscriber) {
    boolean retValue;
    synchronized (mutex) {
      if (subscribers.containsKey(topic)) {
        retValue = subscribers.get(topic).add(subscriber);
      } else {
        Set<Subscriber> set = new HashSet<>();
        retValue = set.add(subscriber);
        subscribers.put(topic, set);
      }
    }
    return retValue;
  }

  public boolean deRegister(String topic, Subscriber subscriber) {
    synchronized (mutex) {
      final Set<Subscriber> subs = this.subscribers.get(topic);
      return subs.remove(subscriber);
    }
  }

  void sendMessage(String topic, Map map) {
    final Set<Subscriber> set = subscribers.get(topic);
    set.parallelStream().forEach(subscriber -> subscriber.update(map));
  }

}
