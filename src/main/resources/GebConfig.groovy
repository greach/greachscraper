import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

environments {

    htmlUnit {
        driver = { new HtmlUnitDriver() }
    }

    firefox {
        driver = { new FirefoxDriver() }
    }
    phantomJs {
        driver = { new PhantomJSDriver() }
    }
    chrome {
        driver = { new ChromeDriver() }
    }
}