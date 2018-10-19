package sample;

import java.util.OptionalInt;
import org.junit.Test;

public class one {
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
      long discountAmt = 0;
      if ("WEMAKEPRICE".equals(discountCode)) {   // 위메프검색 할인
        discountAmt = (long)(productAmt * 0.1);
      } else if ("DANAWA".equals(discountCode)) { // 다나와검색 할인
        discountAmt = (long)(productAmt * 0.15);
      } else if ("FANCAFE".equals(discountCode)) {  // 팬카페인입 할인
        if (productAmt < 1000)  // 할인쿠폰 금액보다 적은경우
          discountAmt = productAmt;
        else
          discountAmt = 1000;
      }
      return OptionalInt.of((int)discountAmt).orElse(0);
    }

    // 결제처리
    public Integer payment() {
      // 상품금액
      long productAmt = 10000;
      // 할인코드 (WEMAKEPRICE:위메프검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원)
      String discountCode = "WEMAKEPRICE";

      // 결제금액
      long paymentAmt = 0;
      if ("WEMAKEPRICE".equals(discountCode)) {   // 위메프검색 할인
        paymentAmt = (long)(productAmt * 0.9);
      } else if ("DANAWA".equals(discountCode)) { // 다나와검색 할인
        paymentAmt = (long)(productAmt * 0.85);
      } else if ("FANCAFE".equals(discountCode)) {  // 팬카페인입 할인
        if (productAmt < 1000)  // 할인쿠폰 금액보다 적은경우
          paymentAmt = 0;
        else
          paymentAmt = productAmt - 1000;
      } else {
        paymentAmt = productAmt;
      }
      return OptionalInt.of((int)paymentAmt).orElse(0);
    }
  }
}
