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

import java.util.List;

@Log4j2
public class BbsSearchImpl extends QuerydslRepositorySupport implements BbsSearch {

    public BbsSearchImpl() {
        super(BbsFreeEntity.class);
    }

    @Override
    public Page<BbsFreeEntity> search(Pageable pageable, String[] types, String search_keyword, String category) {
        QBbsFreeEntity qBoard = QBbsFreeEntity.bbsFreeEntity;
        JPQLQuery<BbsFreeEntity> query = from(qBoard);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(category != null && category.length()>0){
            booleanBuilder.or(qBoard.category.contains(category));
        }
        if(types !=null && types.length > 0 && search_keyword != null && search_keyword.length()>0){
            for(String type : types){
                switch (type) {
                    case "t":
                        booleanBuilder.or(qBoard.title.contains(search_keyword));
                        break;

                    case "c":
                        booleanBuilder.or(qBoard.content.contains(search_keyword));
                        break;

                    case "w":
                        booleanBuilder.or(qBoard.user_id.contains(search_keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        this.getQuerydsl().applyPagination(pageable,query);
        log.info("keyword query : {}", query);
        List<BbsFreeEntity> boards = query.fetch();
        int total = (int)query.fetchCount();
        log.info("keyword board : "+boards);
        log.info("keyword total : "+total);
        log.info("category = " +category);
        return new PageImpl<>(boards,pageable,total);
    }
}
