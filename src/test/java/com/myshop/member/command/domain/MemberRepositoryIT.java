package com.myshop.member.command.domain;

import com.myshop.common.model.Email;
import com.myshop.helper.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryIT {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DbHelper dbHelper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        dbHelper.clear();
    }

    @Test
    void findById() {
        jdbcTemplate.update(
                """
                        insert into member values (?, ?, ?, ?, ?)
                        """,
                "member1", "이름", "p1", false, "email1@email.com,email2@email.com"
        );
        Optional<Member> memOpt = memberRepository.findById(MemberId.of("member1"));
        assertThat(memOpt).isPresent();
        Member member = memOpt.get();
        assertThat(member.getEmails().getEmails()).contains(
                Email.of("email1@email.com"), Email.of("email2@email.com")
        );
    }

    @Test
    void save() {
        Member member = new Member(MemberId.of("id1"), "이름2");
        member.changeEmails(Set.of(Email.of("mail1@mail.com"), Email.of("mail2@mail.com")));
        memberRepository.save(member);

        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from member where member_id = ?", "id1");
        assertThat(rs.next()).isTrue();
        assertThat(rs.getString("emails")).contains("mail1@mail.com", "mail2@mail.com");
    }

    @Transactional
    @Test
    void findByIdForUpdate() {
        jdbcTemplate.update(
                """
                        insert into member values (?, ?, ?, ?, ?)
                        """,
                "member1", "이름", "p1", false, "email1@email.com,email2@email.com"
        );
        Optional<Member> member1 = memberRepository.findByIdForUpdate(MemberId.of("member1"));
    }
}