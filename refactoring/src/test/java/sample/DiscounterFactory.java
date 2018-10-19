package sample;

public interface DiscounterFactory {
  /** 할인 생성 */
  Discountable getDiscounter(String discountCode);
}

class SimpleDiscounterFactory implements DiscounterFactory {
  @Override
  public Discountable getDiscounter(String discountCode) {
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