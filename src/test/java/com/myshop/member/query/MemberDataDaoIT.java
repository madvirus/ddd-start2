package com.myshop.member.query;

import com.myshop.common.jpa.Rangeable;
import com.myshop.common.jpa.SpecBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Sql("classpath:shop-init-test.sql")
class MemberDataDaoIT {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberDataDao memberDataDao;

    @Test
    void findByBlocked() {
        Page<MemberData> page = memberDataDao.findByBlocked(false, PageRequest.of(2, 3));
        logger.info("blocked result: {}", page.getContent().size());
        List<MemberData> content = page.getContent();
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        int number = page.getNumber();
        int numberOfElements = page.getNumberOfElements();
        int size = page.getSize();
        logger.info("content.size()={}, totalElements={}, totalPages={}, number={}, numberOfElements={}",
                content.size(), totalElements, totalPages, number, numberOfElements);
    }

    @Test
    void findByNameLike() {
        Sort sort = Sort.by("name").descending();
        PageRequest pageReq = PageRequest.of(1, 2, sort);
        List<MemberData> user = memberDataDao.findByNameLike("사용자%", pageReq);
        logger.info("name llike result: {}", user.size());
    }

    @Test
    void findAll() {
        Specification<MemberData> spec = MemberDataSpecs.nonBlocked();
        List<MemberData> result = memberDataDao.findAll(spec, PageRequest.of(1, 2));
        logger.info("spec result: {}", result.size());
    }

    @Test
    void getRange() {
        Specification<MemberData> spec = MemberDataSpecs.nonBlocked();
        List<MemberData> result = memberDataDao.getRange(spec, Rangeable.of(2, 4));
        logger.info("spec result: {}", result.size());
    }

    @Test
    void findFirst() {
        List<MemberData> result = memberDataDao.findFirst3ByNameLikeOrderByName("사용자%");
        logger.info("result: {}", result.size());

        Optional<MemberData> member = memberDataDao.findFirstByNameLikeOrderByName("없음");
        logger.info("result: {}", member);
        MemberData data = memberDataDao.findFirstByBlockedOrderById(false);
        logger.info("result: {}", data);
    }


    @Test
    void compositeSpec() {
        SearchRequest searchRequest = new SearchRequest();
        Specification<MemberData> spec = Specification.where(null);
        if (searchRequest.isOnlyNotBlocked()) {
            spec = spec.and(MemberDataSpecs.nonBlocked());
        }
        if (StringUtils.hasText(searchRequest.getName())) {
            spec = spec.and(MemberDataSpecs.nameLike(searchRequest.getName()));
        }
        List<MemberData> result = memberDataDao.findAll(spec, PageRequest.of(0, 5));
        logger.info("result: {}", result.size());
    }

    @Test
    void specBuilder() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setOnlyNotBlocked(true);
        Specification<MemberData> spec = SpecBuilder.builder(MemberData.class)
                .ifTrue(
                        searchRequest.isOnlyNotBlocked(),
                        () -> MemberDataSpecs.nonBlocked())
                .ifHasText(
                        searchRequest.getName(),
                        name -> MemberDataSpecs.nameLike(searchRequest.getName()))
                .toSpec();
        List<MemberData> result = memberDataDao.findAll(spec, PageRequest.of(0, 5));
        logger.info("result: {}", result.size());
    }

}