package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("박민서");

        /*Member member2 = new Member();
        member2.setName("박꼬삼")*/;

        //when
        Long saveId = memberService.join(member);
        /*IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member3));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
       */

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void OvelapTest(){
        //given
        Member member = new Member();
        member.setName("박민서");

        Member member2 = new Member();
        member2.setName("박민서");

        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //when

        //
    }

    @Test
    void findMembers() {
        //given
        List<Member> result  = memberService.findMembers();
        System.out.println(result);
        //when

        //then
    }

    @Test
    void findOne() {
    }
}