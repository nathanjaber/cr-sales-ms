package br.com.colegiorealengo.sales.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Service
public class MessageCollectorCustom {

  @Autowired private MessageCollector collector;
  @Autowired private ObjectMapper mapper;

  public BrokerResponse forChannel(MessageChannel channel) {
    Message<?> poll = collector.forChannel(channel).poll();

    if (poll == null) return null;

    String payload = poll.getPayload().toString();
    MessageHeaders headers = poll.getHeaders();

    return new BrokerResponse(payload, headers, mapper);
  }
}
