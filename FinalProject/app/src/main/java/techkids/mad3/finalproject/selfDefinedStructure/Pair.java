package techkids.mad3.finalproject.selfDefinedStructure;

/**
 * Created by HoangDuong1607 on 6/19/2016.
 */
public class Pair {
    private int firstValue;
    private int secondValue;

    private Pair() {
    }

    public Pair(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    public boolean equals(Pair pair) {
        if (this.firstValue == pair.firstValue && this.secondValue == pair.secondValue) {
            return true;
        }
        if (this.firstValue == pair.secondValue && this.secondValue == pair.firstValue) {
            return true;
        }
        return false;
    }
}
