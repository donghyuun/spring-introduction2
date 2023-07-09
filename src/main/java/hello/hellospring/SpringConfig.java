package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean//스프링 빈에 등록
    public MemberService memberService(){
        return new MemberService(memberRepository());//스프링 빈에 등록된 memberRepository를 넣어줌
    }

    @Bean//스프링 빈에 등록
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
