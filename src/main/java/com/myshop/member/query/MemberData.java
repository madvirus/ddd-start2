package com.myshop.member.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class MemberData {
    @Id
    @Column(name = "member_id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "blocked")
    private boolean blocked;

    protected MemberData() {
    }

    public MemberData(String id, String name, boolean blocked) {
        this.id = id;
        this.name = name;
        this.blocked = blocked;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
