package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    //인터페이스 변수에 해당 인터페이스를 상속하는 구현객체를 대입할 수 있다. 이렇게 하면 인터페이스 변수로 인터페이스의 추상 메서드를 호출할 수 있게된다.
    public MemberService(MemberRepository memberRepository){//외부에서 넣어주도록 설정함
        this.memberRepository = memberRepository;
    }

    /* 회원 가입 */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);//중복 회원 검증

        memberRepository.save(member);//db(static)에 저장됨
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
             .ifPresent(m -> {//ifPresent는 optional 의 메서드
             throw new IllegalStateException("이미 존재하는 회원입니다.");
             });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
