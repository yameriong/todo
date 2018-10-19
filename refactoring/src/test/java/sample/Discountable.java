package sample;

public interface Discountable {
  /** 할인없음 */
  Discountable NONE = new Discountable() {
    @Override
    public long getDiscountAmt(long originAmt) {
      return 0;
    }
  };

  long getDiscountAmt(long originAmt);
}

class WemakepriceDiscountPolicy implements Discountable {
  @Override
  public long getDiscountAmt(long originAmt) {
    return (long)(originAmt * 0.1);
  }
}

class DanawaDiscountPolicy implements Discountable {
  @Override
  public long getDiscountAmt(long originAmt) {
    return (long)(originAmt * 0.15);
  }
}

class FancafeDiscountPolicy implements Discountable {
  private long discountAmt = 1000L;

  @Override
  public long getDiscountAmt(long originAmt) {
    if (originAmt < discountAmt)
      return originAmt;
    return discountAmt;
  }
}