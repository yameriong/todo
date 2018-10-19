package sample;

import java.util.OptionalInt;
import org.junit.Test;

public class three {
  @Test
  public void test(){
    LegacyPayment legacyPayment = new LegacyPayment();
    System.out.println("상시할인액 : " + legacyPayment.getDiscount());
    System.out.println("상시할인금액 : " + legacyPayment.payment());
  }

  public class LegacyPayment{
    // 실시간 할인내역 확인
    public Integer getDiscount() {
      // 상품금액
      long productAmt = 10000;
      // 할인코드 (WEMAKEPRICE:위메프검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원)
      String discountCode = "WEMAKEPRICE";

      // 할인금액
      Discountable discountPolicy = getDiscounter(discountCode);
      long discountAmt = discountPolicy.getDiscountAmt(productAmt);
      return OptionalInt.of((int)discountAmt).orElse(0);
    }

    // 결제처리
    public Integer payment() {
      // 상품금액
      long productAmt = 10000;
      // 할인코드 (WEMAKEPRICE:위메프검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원)
      String discountCode = "WEMAKEPRICE";

      // 결제금액
      Discountable discountPolicy = getDiscounter(discountCode);
      long paymentAmt = productAmt - discountPolicy.getDiscountAmt(productAmt);
      return OptionalInt.of((int)paymentAmt).orElse(0);
    }

    private Discountable getDiscounter(String discountCode){
      long discountAmt = 0;
      if ("WEMAKEPRICE".equals(discountCode)) {   // 위메프검색 할인
        return new WemakepriceDiscountPolicy();
      } else if ("DANAWA".equals(discountCode)) { // 다나와검색 할인
        return new DanawaDiscountPolicy();
      } else if ("FANCAFE".equals(discountCode)) {  // 팬카페 할인
        return new FancafeDiscountPolicy();
      } else {
        return Discountable.NONE;
      }
    }
  }
}
