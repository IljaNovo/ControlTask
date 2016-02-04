public class RiskGroup implements Cloneable {

    private String name;
    private int min;
    private int max;

    public RiskGroup(String name, int min, int max) {
        if (min > max || max < 0 || min < 0
                || min > Integer.MAX_VALUE
                || max > Integer.MAX_VALUE)
        {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean checkEnteringInterval(int value) {
        if (value < 0) {
            return false;
        }
        return (value >= min) && (value <= max);
    }
}
