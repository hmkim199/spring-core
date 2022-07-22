package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {


    private final MemberRepository memberRepository;


    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // final은 무조건 값이 할당되어야 함.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // -> 이런 식으로 하면 OCP, DIP 위반. 배우가 상대 배우 지정하는 꼴.
    private final DiscountPolicy discountPolicy; // private일 때도 의존성 주입 - 필드 주입 됨. 그런데 외부 변경 불가능해서 권장되지 않음. 테스트할 때도 어려움.



    @Autowired // 의존 관계 주입 - 생성자 주입. 불변, 필수인 경우 사용
   public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
