package org.fullstack4.studyforest.repository;

import org.fullstack4.studyforest.domain.BbsFileEntity;
import org.fullstack4.studyforest.domain.BbsGoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BbsFileRepository extends JpaRepository<BbsFileEntity, Integer> {
    BbsFileEntity findByBbsIdx(int bbsIdx);
}
