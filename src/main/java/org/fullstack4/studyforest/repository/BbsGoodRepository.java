package org.fullstack4.studyforest.repository;

import org.fullstack4.studyforest.domain.BbsGoodEntity;
import org.fullstack4.studyforest.domain.BbsShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BbsGoodRepository extends JpaRepository<BbsGoodEntity, Integer> {
    List<BbsGoodEntity> findAllByUserId(String userId);

    BbsGoodEntity findByBbsIdxAndUserId(int bbsIdx, String userId);

    int countAllByBbsIdx(int bbsIdx);
}
