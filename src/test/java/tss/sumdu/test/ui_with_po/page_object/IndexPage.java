package tss.sumdu.test.ui_with_po.page_object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;

public class IndexPage {

@FindBy(how = How.TAG_NAME, using = "body")
public SelenideElement body;

@FindBy(how = How.ID, using = "info")
public SelenideElement info;

@FindBy(how = How.NAME, using = "url")
private SelenideElement url;
@FindBy(how = How.ID, using = "request")
private SelenideElement request;

public void setUrl(String text) {
    fill(this.url, text);
}

public void send() {
    $("#submit").click();
}

public void setRequest(String text) {
    fill(this.request, text);
}

private void fill(SelenideElement element, String text) {
    element.clear();
    element.sendKeys(text);
}
}