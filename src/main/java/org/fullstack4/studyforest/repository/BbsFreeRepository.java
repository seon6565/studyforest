package org.fullstack4.studyforest.repository;

import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.repository.search.BbsSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BbsFreeRepository extends JpaRepository<BbsFreeEntity, Integer>, BbsSearch {
}
