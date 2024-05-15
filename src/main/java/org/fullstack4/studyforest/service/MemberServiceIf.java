package org.fullstack4.studyforest.service;


import org.fullstack4.studyforest.domain.MemberEntity;
import org.fullstack4.studyforest.dto.MemberDTO;
import org.springframework.data.domain.Page;

public interface MemberServiceIf {
    int regist(MemberDTO memberDTO);
    MemberDTO view(int idx);
    MemberDTO modify(MemberDTO memberDTO);
    MemberDTO modifyPassword(MemberDTO memberDTO);
    void leave(int idx);
    int idCheck(String user_id);
}
