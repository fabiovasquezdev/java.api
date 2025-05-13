package com.vazcode.application.usecases.member;

import com.vazcode.domain.entities.Member;
import com.vazcode.domain.repositories.MemberRepository;
import com.vazcode.presentation.dtos.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateMemberUseCase {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDTO execute(MemberDTO memberDTO) {
        if (memberRepository.existsByEmail(memberDTO.getEmail())) {
            throw new IllegalArgumentException("Email já registrado");
        }

        Member member = new Member();
        BeanUtils.copyProperties(memberDTO, member);
        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        member = memberRepository.save(member);
        
        MemberDTO dto = new MemberDTO();
        BeanUtils.copyProperties(member, dto);
        return dto;
    }
}