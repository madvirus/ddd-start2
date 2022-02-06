package com.myshop.integration.infra;

import com.myshop.eventstore.api.EventEntry;
import com.myshop.integration.EventSender;
import org.springframework.stereotype.Component;

@Component
public class SysoutEventSender implements EventSender {
    @Override
    public void send(EventEntry event) {
        System.out.println("EventSender send event : " + event);
    }
}
