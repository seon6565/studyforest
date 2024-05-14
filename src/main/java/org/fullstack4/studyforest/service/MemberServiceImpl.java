package org.fullstack4.studyforest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.domain.MemberEntity;
import org.fullstack4.studyforest.dto.BbsDTO;
import org.fullstack4.studyforest.dto.MemberDTO;
import org.fullstack4.studyforest.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberServiceIf{
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    @Override
    public int regist(MemberDTO memberDTO) {
        MemberEntity memberEntity = modelMapper.map(memberDTO, MemberEntity.class);
        int idx = memberRepository.save(memberEntity).getMember_idx();
        return idx;
    }

    @Override
    public MemberDTO view(int idx) {
        Optional<MemberEntity> result = memberRepository.findById(idx);
        MemberEntity memberEntity = result.orElse(null);
        MemberDTO memberDTO = modelMapper.map(memberEntity,MemberDTO.class);
        return memberDTO;

    }

    @Override
    public void modify(MemberDTO memberDTO) {
        Optional<MemberEntity> result = memberRepository.findById(memberDTO.getMember_idx());
        MemberEntity memberEntity = result.orElse(null);
        if(memberEntity!=null) {
            memberEntity.modify(memberDTO.getPassword(),
                    memberDTO.getEmail(),
                    memberDTO.getPhone(),
                    memberDTO.getBirthday(),
                    memberDTO.getAddr1(),
                    memberDTO.getAddr2(),
                    memberDTO.getAddr_number());
        }
    }

    @Override
    public void delete(int idx) {
        memberRepository.deleteById(idx);
    }

    @Override
    public int idCheck(String user_id) {
        log.info(user_id);
        Optional<MemberEntity> result = memberRepository.findByUserId(user_id);
        if(result.isPresent()){
            return 1;
        }
        return 0;
       // int result = memberRepository.idCheck(user_id);
    }

}
