package com.example.dec2020.persist;

import org.springframework.data.repository.CrudRepository;
public interface MessageRepository extends CrudRepository<Message, Integer>
{
}