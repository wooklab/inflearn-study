package hellojpa;

public class ValueMain {

    public static void main(String[] args) {
        int a = 10;
        int b = a;
        b = 20; // 값이 복사되지 않는다.

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        Integer c = new Integer(10);
        Integer d = c;  // 참조주소는 복사
        d = 15;         // 변경x

        System.out.println("c = " + c);
        System.out.println("d = " + d);
    }
}
