import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {

  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();



  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Places I've Been!");
  }

  @Test
  public void locations_addsFirstPlace() {
    goTo("http://localhost:4567/");
    fill("#location").with("Canada");
    submit(".btn");
    assertThat(pageSource()).contains("Canada");
  }

  @Test
  public void locations_addsMultiplePlaces() {
    goTo("http://localhost:4567/");
    fill("#location").with("Canada");
    submit(".btn");
    click("a", withText("Go Back"));
    fill("#location").with("Australia");
    submit(".btn");
    assertThat(pageSource()).contains("Canada");
    assertThat(pageSource()).contains("Australia");
  }

}
