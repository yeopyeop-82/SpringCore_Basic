package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // OCP 위반 => 의존관계가 있으므로 코드를 바꿔야 한다 -> 문제 발생 !!
    // DiscountPolicy 뿐만 아니라 FixDiscountPolicy, RateDiscountPolicy 도 의존한다..
    // DIP 위반 => 인터페이스에만 의존해야하는데, 구현체도 의존하니 망해버림

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice); // 단일체계원칙이 잘 지켜졌다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
