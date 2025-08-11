package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        //같은 이름이 잇는 회원은 x
        /*Optional<Member> result = memberRepository.findByName(member.getName()); //command option v => optional : null 방지
        //result.get() or .orElseGet
        
        result.ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        바로 꺼내는건 권장하지 않는다.
         */
        vaildateDuplicateMemeber(member); //중복 회원 검증


        memberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMemeber(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
