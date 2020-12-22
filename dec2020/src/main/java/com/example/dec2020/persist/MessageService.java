package com.example.dec2020.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  public MessageRepository messageRepository;

  public void saveOrUpdate(Message message)
  {
    messageRepository.save(message);
  }

}
