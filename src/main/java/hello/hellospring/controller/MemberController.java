package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller//스프링이 올라올때 컨테이너에 스프링 빈으로 등록됨
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){//생성자 DI, memberSerivce 객체가 스프링 빈으로 등록되어 있어야 Autowired 사용 가능
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){//스프링이 input 태그의 name 속성의 값을 MemberForm 객체의 name 필드에 저장하는데, 이때 MemberForm 객체의 setName 함수를 사용(=setName 함수가 있어야 함)
        Member member = new Member();
        member.setName(form.getName());//name 필드의 값을 꺼냄
        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }
}
