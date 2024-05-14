package org.fullstack4.studyforest.service;


import org.fullstack4.studyforest.dto.LoginDTO;
import org.fullstack4.studyforest.dto.MemberDTO;

public interface LoginServiceIf {
    MemberDTO login(LoginDTO loginDTO);

    MemberDTO cookieLogin(String user_id);

}
