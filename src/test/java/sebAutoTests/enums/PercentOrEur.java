package sebAutoTests.enums;

public enum PercentOrEur {
    PERCENT("p"),EUR("v");
    private String action;

    public String chooseType() {
        return this.action;
    }

    PercentOrEur(String action) {
        this.action = action;
    }
}