package org.fullstack4.studyforest.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.domain.QBbsFreeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
public class BbsSearchImpl extends QuerydslRepositorySupport implements BbsSearch {

    public BbsSearchImpl() {
        super(BbsFreeEntity.class);
    }
    @Override
    public Page<BbsFreeEntity> search(Pageable pageable, String[] types, String search_keyword, String category, String category2) {
        if(category.equals("free")) {
            QBbsFreeEntity qBoard = QBbsFreeEntity.bbsFreeEntity;
            JPQLQuery<BbsFreeEntity> query = from(qBoard);
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            if (category2 != null && category2.length() > 0) {
                booleanBuilder.or(qBoard.category.contains(category2));
            }
            if (types != null && types.length > 1 && search_keyword != null && search_keyword.length() > 0) {
                for (String type : types) {
                    switch (type) {
                        case "t":
                            booleanBuilder.and(qBoard.title.contains(search_keyword));
                            break;

                        case "c":
                            booleanBuilder.or(qBoard.content.contains(search_keyword));
                            break;

                        case "u":
                            booleanBuilder.or(qBoard.user_id.contains(search_keyword));
                            break;
                    }
                }
            }
            else if(types != null && types.length == 1 && search_keyword != null && search_keyword.length() > 0) {
                for (String type : types) {
                    switch (type) {
                        case "t":
                            booleanBuilder.and(qBoard.title.contains(search_keyword));
                            break;

                        case "c":
                            booleanBuilder.and(qBoard.content.contains(search_keyword));
                            break;
                        case "u":
                            booleanBuilder.and(qBoard.user_id.contains(search_keyword));
                            break;

                    }
                }
            }
            query.where(booleanBuilder);
            query.orderBy(qBoard.bbsIdx.desc());
            this.getQuerydsl().applyPagination(pageable, query);
            log.info("keyword query : {}", query);
            List<BbsFreeEntity> boards = query.fetch();
            int total = (int) query.fetchCount();
            log.info("keyword board : " + boards);
            log.info("keyword total : " + total);
            log.info("category = " + category);
            return new PageImpl<>(boards,pageable,total);
        }
        return null;
    }

    @Override
    public Page<BbsFreeEntity> searchUserList(Pageable pageable, String[] types, String search_keyword, String category, String category2, String user_id, String orderby, LocalDate reg_date_start, LocalDate reg_date_end) {
        if(category.equals("free")) {
            QBbsFreeEntity qBoard = QBbsFreeEntity.bbsFreeEntity;
            JPQLQuery<BbsFreeEntity> query = from(qBoard);
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            if (user_id != null && user_id.length() > 0) {
                booleanBuilder.and(qBoard.user_id.eq(user_id));
            }
            if (category != null && category.length() > 0) {
                booleanBuilder.and(qBoard.category.contains(category));
            }
            if (category2 != null && category2.length() > 0) {
                booleanBuilder.and(qBoard.category2.contains(category2));
            }
            LocalTime start_time = LocalTime.of(0,0,0);
            LocalTime end_time = LocalTime.of(23,59,59);
            if (reg_date_start != null && reg_date_end != null) {
                LocalDateTime reg_start = LocalDateTime.of(reg_date_start,start_time);
                LocalDateTime reg_end = LocalDateTime.of(reg_date_end,end_time);
                booleanBuilder.and(qBoard.reg_date.between(reg_start,reg_end));
            }
            else if(reg_date_start != null){
                LocalDateTime reg_start = LocalDateTime.of(reg_date_start,start_time);
                booleanBuilder.and(qBoard.reg_date.after(reg_start));
            }
            else if(reg_date_end != null){
                LocalDateTime reg_end = LocalDateTime.of(reg_date_end,end_time);
                booleanBuilder.and(qBoard.reg_date.before(reg_end));
            }

            if (types != null && types.length > 0 && search_keyword != null && search_keyword.length() > 0) {
                for (String type : types) {
                    switch (type) {
                        case "t":
                            booleanBuilder.and(qBoard.title.contains(search_keyword));
                            break;

                        case "c":
                            booleanBuilder.and(qBoard.content.contains(search_keyword));
                            break;

                    }
                }
            }
            query.where(booleanBuilder);
            if(orderby != null && orderby.length() > 0){
                if(orderby.equals("good")){
                    query.orderBy(qBoard.good.desc());
                }else if(orderby.equals("reg_date")) {
                    query.orderBy(qBoard.reg_date.desc());
                }
            }else {
                query.orderBy(qBoard.bbsIdx.desc());
            }
            this.getQuerydsl().applyPagination(pageable, query);
            log.info("keyword query : {}", query);
            List<BbsFreeEntity> boards = query.fetch();
            int total = (int) query.fetchCount();
            log.info("keyword board : " + boards);
            log.info("keyword total : " + total);
            log.info("category = " + category);
            return new PageImpl<>(boards,pageable,total);
        }
        return null;
    }
}
