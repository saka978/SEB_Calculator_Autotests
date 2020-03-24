package sebAutoTests;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Base {

  private static String baseUrl = "https://www.seb.lt/";
  private static SelenideElement cookieAcceptBtn = $(".accept-selected");

  protected static void openPage(String pageExtension){
    open(baseUrl+pageExtension);
    cookieAcceptBtn.click();
  }
}
