package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("하이루");


        //when
        Long saveId = memberService.join(member);


        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복검사(){
        //given
        Member member = new Member();
        member.setName("박민서1");

        Member member2 = new Member();
        member2.setName("박민서1");

        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //when

        //
    }

    @Test
    void 회원검색() {
        //given
        List<Member> result  = memberService.findMembers();
        System.out.println(result);
        //when

        //then
    }

}