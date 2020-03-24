package sebAutoTests.enums;

public enum LeasingType {
    FINANCIAL("0"),OPERATING("1");
    private String action;

    public String chooseType() {
        return this.action;
    }

    LeasingType(String action) {
        this.action = action;
    }
}
