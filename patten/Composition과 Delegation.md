POINT!

1. 위임(deletation)과 포함/구성(composite) 관계는 단일 상속(single inheritance)만 가능한 자바에서 다중상속 개념을 적용할 때 사용한다.

2. 또한 인터페이스를 활용하여 클래스 선언과 구현을 분리하는 작업에서도 수용되는 기법으로(대부분 이 용도로 많이 수용되는 것 같다) 간접적인 관계를 설정하므로써 클래스 간의 결합도를 낮춰줄 수 있다. 

   2.1. 참고로, 결합도가 낮다는 것은 관계가 있는 다른 클래스가 변경되더라도 이를 사용하는 클래스는 영향을 받지 않는다는 것, 독립적인 프로그래밍이 가능하다는 것을 의미한다.  
   
   2.2. 이는 OOP의 목표인 유지 보수 관점이나 코드 재사용성 관점에서 보았을 때도 유용한 구현 방법으로, 실제 자바 언어 기반인 안드로이드 플랫폼 개발에서 흔히 볼 수 있다. Ex) onClickListener 등 이벤트 인터페이스 동작을 구현할 때 사용.
   
I. 위임(delegation)이란?

* 위임이란 '어떤 메서드의 처리를 다른 인스턴스의 메서드에 맡긴다'는 의미이다.

* 즉, 클라이언트 코드에서 구성(composite)하고 있는 참조 객체를 통해 메서드를 호출하는 것(delegation)을 말함.


II. 위임(delegation)과 구성/포함(composite) 개념의 포인트

* 아래 예제의 주석은 Adapter패턴에 대한 내용이지만, 사실 디자인 패턴보다는

* 실제로는 단일 상속(single inheritance)만 지원하는 자바에서 다중 상속 개념을 적용할 때 사용한다. 이게 위임과 구성을 사용하는 이유이다.

III. 출처 및 주석 보충 설명
* 예제는 구글링하여 가져왔슴니다.
* 예제(주석의 내용)은 위임(delegation)과 포함/구성(composite) 외에도 Adapter패턴에 대한 주석과 객체 참조 방법을 다루고 있습니다.
```{.java}
[ 예제 코드 ]
// Adaptee / Adapter / Target : Banner / PrintBanner / Print

public class Main {

	public static void main(String[] args) {

		// Q. PrintBanner클래스 고유의 메서드가 있다면 이를 사용하기 위해서는 어떻게 해야하는가?

		Print p = new PrintBanner("hello");

		System.out.println(p.printString());

		// A.

		System.out.println(((PrintBanner)p).test());

	}

//	[ 실행결과 ]

//	(hello)

//	This is PrintBanner class Method!!

}

// Adaptee

class Banner {

	private String str;
	
	Banner(String str) {

		this.str = str;

	}

	public String showString() { return str; }

}



// Target : 대상 역할 클래스

abstract class Print {

	public abstract String printString();

}


// Adapter : Adaptee를 이용하여 Target역할을 대신함.

class PrintBanner extends Print {

	// 멤버변수 : 객체의 속성

	private Banner banner;	// 포함,구성(Composite)

	// 생성자(Constructor) : 객체의 초기화, 객체 생성 시 실행

	public PrintBanner(String str) {

		this.banner = new Banner(str);

	}
	
	// 메서드 : 객체의 행위 또는 기능

	@Override
	public String printString() {

		// 위임(delegation) : 클라이언트 코드에서 구성(Composite)하고 있는 참조객체를 통해 메서드를 호출하는 것을 말함.
		return "(" + banner.showString() + ")";

	}

	public String test() {
		return "This is PrintBanner class Method!!\n";
	}
}
```    

