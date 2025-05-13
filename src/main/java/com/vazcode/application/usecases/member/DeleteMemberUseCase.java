package com.vazcode.application.usecases.member;

import com.vazcode.domain.entities.Member;
import com.vazcode.domain.repositories.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMemberUseCase {
    private final MemberRepository memberRepository;

    @Transactional
    public void execute(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado com id: " + id));
        memberRepository.delete(member);
    }
}