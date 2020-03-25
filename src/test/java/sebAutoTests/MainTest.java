package sebAutoTests;

import org.junit.Before;
import org.junit.Test;
import sebAutoTests.data.LeasingData;
import sebAutoTests.pages.CalculatorPage;

// TODO "PLEASE KEEP IN MIND THAT TESTS FAIL DUE TO TOTAL AMOUNT MISMATCES OF "Total financing costs"
// TODO  in calculator (that assert is commented) and in schedules total leasing payment, total interest and total payment.


public class MainTest {
  CalculatorPage calculatorPage;
  LeasingData leasingData;

  @Before
  public void setUp() {
    leasingData = new LeasingData();
    calculatorPage = new CalculatorPage(leasingData);
    calculatorPage.openCalculatorPage();
  }

  @Test
  //This test fails because totalFinancingCosts is calculated incorrectly in calculator. Please comment totalFinancingCosts
  //assertion in "checkIfFinancialLeasingDataIsCalculatedCorrectly" check if you want the test to pass
  public void checkIfCalculatorCalculatesCorrectValues() {
    calculatorPage
        .enterLeasingData()
        .checkIfFinancialLeasingDataIsCalculatedCorrectly();
  }

  @Test
  //Schedules total leasing payment, total interest and total payment are calculated incorrectly, especially if longer
  //leasing period is set
  public void checkIfFinancialLeasingTotalValuesAreCalculatedCorrectly() {
    calculatorPage
        .enterLeasingData()
        .clickPaymentScheduleButton()
        .checkIfScheduleTotalAmountIsCalculatedCorrectly();
  }

  @Test
  public void checkIfCalculatorUpdatesCalculationsAfterChangingValue(){
    calculatorPage
            .enterLeasingData()
            .checkIfFinancialLeasingDataIsCalculatedCorrectly()
            .updateLeasingPurchaseAmount()
            .checkIfFinancialLeasingDataIsCalculatedCorrectly();
  }
}
