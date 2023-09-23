package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 자기 자신을 static final로 가진다. 자바 기본 문법 !
    // 1. static 영역에 객체를 딱 1개만 생성해둔다.

    public static SingletonService getInstance() {
        //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
        return instance;
    }

    private SingletonService() {
        // 3. private 생성자로 외부에서 new 키워드를 사용한 객체를 만들지 못하도록 한다 !!
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {
        SingletonService singletonService = new SingletonService();
    }

}
