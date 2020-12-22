package com.example.dec2020.persist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table
@Slf4j
@Getter
@Setter
public class Message
{
  //mark id as primary key
  @Id
//defining id as column name
  @Column
  private long message_id;
  //defining name as column name
  @Column
  private String data;
}
