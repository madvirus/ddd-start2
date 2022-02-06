package com.myshop.member.infra;

import com.myshop.member.command.domain.PasswordChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PasswordChangedEventHandler {
    @EventListener(PasswordChangedEvent.class)
    public void handle(PasswordChangedEvent event) {
        // 이메일 발송 코드
    }
}
