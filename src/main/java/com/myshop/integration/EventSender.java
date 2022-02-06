package com.myshop.integration;

import com.myshop.eventstore.api.EventEntry;

public interface EventSender {
    void send(EventEntry event);
}
