package org.fullstack4.studyforest.repository.search;


import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BbsSearch {

    Page<BbsFreeEntity> search(Pageable pageable, String[] types, String search_keyword, String category);

}
