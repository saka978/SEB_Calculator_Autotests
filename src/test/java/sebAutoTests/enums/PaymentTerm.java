package sebAutoTests.enums;

public enum PaymentTerm {
    ONE_YEAR("12"),TWO_YEARS("24"),THREE_YEARS("36"),FOUR_YEARS("48"),FIVE_YEARS("60");
    private String action;

    public String chooseTerm() {
        return this.action;
    }

    PaymentTerm(String action) {
        this.action = action;
    }
}
