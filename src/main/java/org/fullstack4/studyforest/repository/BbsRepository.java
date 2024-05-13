package org.fullstack4.studyforest.repository;

import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.repository.search.BbsSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BbsRepository extends JpaRepository<BbsFreeEntity, Integer>, BbsSearch {

}
