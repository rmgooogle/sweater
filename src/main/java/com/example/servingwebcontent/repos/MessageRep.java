package com.example.servingwebcontent.repos;

import com.example.servingwebcontent.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRep extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}