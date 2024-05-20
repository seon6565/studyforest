package org.fullstack4.studyforest.repository;

import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.domain.BbsShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BbsShareRepository extends JpaRepository<BbsShareEntity, Integer> {

    List<BbsShareEntity> findAllByBbsIdx(int bbsIdx);
    List<BbsShareEntity> findAllByUserId(String userId);
    List<BbsShareEntity> findAllByToUserId(String userId);
}
