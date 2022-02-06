package com.myshop.order.infra;

import com.myshop.member.command.domain.MemberId;
import com.myshop.member.query.MemberData;
import com.myshop.member.query.MemberQueryService;
import com.myshop.order.command.domain.Orderer;
import com.myshop.order.command.domain.OrdererService;
import org.springframework.stereotype.Service;

@Service
public class OrdererServiceImpl implements OrdererService {
    private MemberQueryService memberQueryService;

    public OrdererServiceImpl(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @Override
    public Orderer createOrderer(MemberId ordererMemberId) {
        MemberData memberData = memberQueryService.getMemberData(ordererMemberId.getId());
        return new Orderer(MemberId.of(memberData.getId()), memberData.getName());
    }
}
