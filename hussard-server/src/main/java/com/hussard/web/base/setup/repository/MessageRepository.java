package com.hussard.web.base.setup.repository;

import com.hussard.web.base.domain.Message;

import java.util.List;

/**
 * Created by Matthew on 2014-08-26.
 */
public interface MessageRepository {

    List<Message> getMessages();

    void save(List<Message> messages);

    void clear();
}
