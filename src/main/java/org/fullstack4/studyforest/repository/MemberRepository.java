package org.fullstack4.studyforest.repository;

import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.domain.MemberEntity;
import org.fullstack4.studyforest.repository.search.BbsSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    public Optional<MemberEntity> findByUserId(String userId);


}
