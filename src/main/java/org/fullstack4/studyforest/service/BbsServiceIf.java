package org.fullstack4.studyforest.service;


import org.fullstack4.studyforest.dto.BbsDTO;

public interface BbsServiceIf {
    int regist(BbsDTO boardDTO);
    BbsDTO view(int idx);
    void modify(BbsDTO boardDTO);
    void delete(BbsDTO boardDTO);

}
