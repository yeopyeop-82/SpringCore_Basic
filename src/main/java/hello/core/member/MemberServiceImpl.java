package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 인터페이스 (추상화) 만 의존하고, AppConfig가 주입해준다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}