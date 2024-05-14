package org.fullstack4.studyforest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.MemberEntity;
import org.fullstack4.studyforest.dto.LoginDTO;
import org.fullstack4.studyforest.dto.MemberDTO;
import org.fullstack4.studyforest.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginServiceIf{
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Override
    public MemberDTO login(LoginDTO loginDTO) {
        Optional<MemberEntity> result = memberRepository.findByUserId(loginDTO.getUser_id());
        MemberEntity memberEntity = result.orElse(null);
        if(memberEntity!=null && memberEntity.getPassword().equals(loginDTO.getPwd())) {
            MemberDTO memberDTO = modelMapper.map(memberEntity, MemberDTO.class);
            return memberDTO;
        }
        return null;
    }

    @Override
    public MemberDTO cookieLogin(String user_id) {
//        MemberVO memberVO = loginXmlMapper.Cookie_login(user_id);
//            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            return null; //memberDTO;
    }

}
