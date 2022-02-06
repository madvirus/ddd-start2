package com.myshop.order.command.domain;

import com.myshop.member.command.domain.MemberId;

public interface OrdererService {
    Orderer createOrderer(MemberId ordererMemberId);
}
