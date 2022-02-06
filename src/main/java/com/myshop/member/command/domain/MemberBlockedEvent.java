package com.myshop.member.command.domain;

import com.myshop.common.event.Event;

public class MemberBlockedEvent extends Event {
    private String memberId;

    public MemberBlockedEvent(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
