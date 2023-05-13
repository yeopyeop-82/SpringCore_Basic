package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정정보
public class AppConfig {

    // 생성은 AppConfig가 담당한다.
    // 의존관계 주입

    @Bean //각 메서드에 bean .. -> 스프링 컨테이너에 등록이 된다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        // 생성자 주입 !
        // 생성자를 통해 주입
        // 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존하지 않는다 !
        // 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중 !!
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); // 중복 제거 -> 한눈에 역할이 들어온다.
        // return new FixDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // 생성자 두개도 가능
    }
}
