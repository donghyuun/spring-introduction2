package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest// 스프링 컨테이너와 테스트를 함께 실행
@Transactional //이걸 TEST 케이스에 달면 TEST메서드 실행행할때 먼저 transaction 을 걸고 쿼리 수행 후 테스트 끝나면 다시 롤백(아에 반영을 x)한다
//따라서 다음 테스트에 영향을 주지 않기 때문에 매번 DB를 다시 지우는 코드를 안넣어도 된다.
//Transaction 은 테스트 메서드마다 일일이 동작한다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    //보통 테스트용 DB를 따로 구축함
    @Test//given when then 패턴 활용
    void 회원가입(){
        //given
        Member member = new Member();
        member.setName("spring100");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //람다식 문장이 호출되면 IllegalAccessException.class 예외가 발생해야 정상동작, 발생한 해당 예외 이벤트를 반환
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }
        */
        //then
    }

    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }
}