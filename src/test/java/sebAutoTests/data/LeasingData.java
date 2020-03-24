package sebAutoTests.data;

import sebAutoTests.enums.LeasingType;
import sebAutoTests.enums.PaymentTerm;
import sebAutoTests.enums.PercentOrEur;

import static sebAutoTests.enums.LeasingType.*;
import static sebAutoTests.enums.PaymentTerm.*;
import static sebAutoTests.enums.PercentOrEur.*;

public class LeasingData {
    public double purchaseAmount = 10000;
    public double interestAmount = 5;
    public PaymentTerm paymentTerm = ONE_YEAR;
    public double leasingFirstInstallmentAmount = 10;
    public PercentOrEur firstInstallmentType = PERCENT;
    public LeasingType leasingType = FINANCIAL;
    public double remainingValue = 50;
}