package org.fullstack4.studyforest.repository.search;


import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BbsSearch {

    Page<BbsFreeEntity> search(Pageable pageable, String[] types, String search_keyword, String table, String category);

    Page<BbsFreeEntity> searchUserList(Pageable pageable, String[] types, String search_keyword, String table, String category, String user_id, String orderby, LocalDate reg_date_start, LocalDate reg_date_end);

}
