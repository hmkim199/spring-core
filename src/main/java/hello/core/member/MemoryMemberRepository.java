package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 실무에선 동시성 이슈로 cuncurrent hashmap 씀

    @Override
    public void save(Member member) {
        store.put(member.getId(), member); // 실제론 null 체크 등 해야하는데 일단 이렇게 함!
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
