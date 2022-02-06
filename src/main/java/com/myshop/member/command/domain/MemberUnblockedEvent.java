package com.myshop.member.command.domain;

public class MemberUnblockedEvent {
    private String memberId;

    public MemberUnblockedEvent(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
