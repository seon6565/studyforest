package org.fullstack4.studyforest.service;


import org.fullstack4.studyforest.domain.MemberEntity;
import org.fullstack4.studyforest.dto.MemberDTO;
import org.springframework.data.domain.Page;

public interface MemberServiceIf {
    int regist(MemberDTO memberDTO);
    MemberDTO view(int idx);
    void modify(MemberDTO memberDTO);
    void modifyPassword(MemberDTO memberDTO);
    void delete(int idx);
    int idCheck(String user_id);
}
