package sebAutoTests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import sebAutoTests.Base;
import sebAutoTests.data.LeasingData;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.*;
import static java.lang.Integer.*;
import static org.junit.Assert.*;
import static sebAutoTests.enums.LeasingType.*;
import static sebAutoTests.enums.PercentOrEur.*;

public class CalculatorPage extends Base {

  public CalculatorPage(LeasingData leasingData){
    this.leasingData = leasingData;
  }

  LeasingData leasingData;
  //Calculator Elements
  private SelenideElement purchaseValueTxtBox = $("#f-summa"),
      submitBtn = $("[type='submit']"),
      calculatorIFrame = $("#calculator-frame-06"),
      interestRateTxtBox = $("#f-likme"),
      paymentTermDropDown = $("#f-termins"),
      firstInstallmentTxtBox = $("#f-maksa"),
      leasingTypeDropDown = $("#f-type"),
      remainingValueTxtBox = $("#f-rest"),
      firstInstallmentTypeDropDown = $("#f-maksa-type"),
      leasingFinancingAmountResult = $("[data-name='finance_sum']"),
      commisionFeeResult = $("[data-name='commision']"),
      firstInstallmentResult = $("[data-name='maksa']"),
      monthlyPaymentResult = $("[data-name='monthly_payment']"),
      totalFinancingResult = $("[data-name='sum']"),
      paymentScheduleBtn = $(".js-show-payment-schedule");

  //Schedule elements
  private SelenideElement scheduleTotalLeasingPayment = $(".subtotal td:nth-child(4)"),
      scheduleTotalInterest = $(".subtotal td:nth-child(5)"),
      scheduleTotalPayment = $(".subtotal td:nth-child(6)");

  private ElementsCollection scheduleLeasingPayments = $$(".js-results-table tr td:nth-child(4)"),
      scheduleInterests = $$(".js-results-table tr td:nth-child(5)"),
      schedulePayments = $$(".js-results-table tr td:nth-child(6)");

  //--------------------------------------------------------Actions---------------------------------------------------

  public CalculatorPage openCalculatorPage() {
    openPage("/eng/private/calculator-leasing");
    return this;
  }

  public CalculatorPage enterLeasingData() {
    switchTo().frame(calculatorIFrame);
    purchaseValueTxtBox.setValue(String.valueOf(leasingData.purchaseAmount));
    interestRateTxtBox.setValue(String.valueOf(leasingData.interestAmount));
    paymentTermDropDown.selectOptionByValue(leasingData.paymentTerm.chooseTerm());
    firstInstallmentTypeDropDown.selectOptionByValue(leasingData.firstInstallmentType.chooseType());
    firstInstallmentTxtBox.setValue(String.valueOf(leasingData.leasingFirstInstallmentAmount));
    leasingTypeDropDown.selectOptionByValue(leasingData.leasingType.chooseType());
    if (leasingData.leasingType == OPERATING) {
      remainingValueTxtBox.setValue(String.valueOf(leasingData.remainingValue));
    }
    submitBtn.click();
    return this;
  }

  public CalculatorPage checkIfFinancialLeasingDataIsCalculatedCorrectly() {
    // --------------------------------Test data preparation------------------------------------------------------
    double financingAmount = 0;
    double firstInstallment = 0;
    if (leasingData.firstInstallmentType == PERCENT) {
      firstInstallment = leasingData.purchaseAmount / 100 * leasingData.leasingFirstInstallmentAmount;
      financingAmount = leasingData.purchaseAmount - firstInstallment;
    } else {
      firstInstallment = leasingData.leasingFirstInstallmentAmount;
      financingAmount = leasingData.purchaseAmount - firstInstallment;
    }
    double commissionFee = financingAmount / 60;
    String totalFinancingCosts =
        String.format("%.2f", parseDouble(monthlyPaymentResult.getText()) * parseInt(leasingData.paymentTerm.chooseTerm()) + commissionFee);
    // ------------------------------------Assertion---------------------------------------------------------------
    assertEquals(
        leasingFinancingAmountResult.getText().replace(" ", ""),
        String.format("%.2f", financingAmount));
    assertEquals(
        commisionFeeResult.getText().replace(" ", ""), String.format("%.2f", commissionFee));
    assertEquals(
        firstInstallmentResult.getText().replace(" ", ""), String.format("%.2f", firstInstallment));

    //TODO !!!!!THIS ASSERTIONS FAILS, BECAUSE IN CALCULATOR totalFinancingCosts is calculated INCORRECTLY.!!!!!
    assertEquals(totalFinancingResult.getText().replace(" ",""),totalFinancingCosts);
    return this;
  }

  public CalculatorPage clickPaymentScheduleButton(){
    paymentScheduleBtn.click();
    return this;
  }

  public CalculatorPage checkIfScheduleTotalAmountIsCalculatedCorrectly(){

    double totalLeasingPayment=0, totalInterest=0,totalPayment=0;

    for (int i=1; i<= Integer.parseInt(leasingData.paymentTerm.chooseTerm())+1; i++){
       totalLeasingPayment += Double.parseDouble(scheduleLeasingPayments.get(i).getText());
       totalInterest +=Double.parseDouble(scheduleInterests.get(i).getText());
       totalPayment +=Double.parseDouble(schedulePayments.get(i).getText());
    }
    assertEquals(scheduleTotalInterest.getText(),String.format("%.2f",totalInterest));
    assertEquals(scheduleTotalLeasingPayment.getText(),String.format("%.2f",totalLeasingPayment));
    assertEquals(scheduleTotalPayment.getText(),String.format("%.2f",totalPayment));
    return this;
  }

  public CalculatorPage updateLeasingPurchaseAmount(){
    leasingData.purchaseAmount = leasingData.purchaseAmount+100;
    purchaseValueTxtBox.setValue(String.valueOf(leasingData.purchaseAmount));
    submitBtn.click();
    return this;
  }

}
