package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {


    private MemberRepository memberRepository;


    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // final은 무조건 값이 할당되어야 함.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // -> 이런 식으로 하면 OCP, DIP 위반. 배우가 상대 배우 지정하는 꼴.
    private DiscountPolicy discountPolicy;

    @Autowired // 의존 관계 주입 - 수정자 주입. 선택, 변경 가능성 있는 경우 사용. 선택적인 경우 @Autowired(required=false)로 설정
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
//
//    @Autowired // 의존 관계 주입 - 생성자 주입. 불변, 필수인 경우 사용
//   public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


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
