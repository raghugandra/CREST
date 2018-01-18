import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This class will contain a series of test skeletons for exercising the
 * following Github features.
 *
 * 1. A test to see if a registered user can successfully log in 2. A test to
 * see if an unregistered user cannot log in 3. A test to see if a registered
 * user can create a new repository 4. A test to see if a registered user cannot
 * create a new repository when it has the same name as an existing repository.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GitHubTest {

	private WebDriver driver;

	private static final String GIT_HUB_URL = "https://github.com";

	// Enter the username for the Github account you create here
	private static final String USERNAME = "raghugandra";

	// Enter the password for the Github account you create here.
	// Note: Do not use any of your real-life passwords!
	private static final String PASSWORD = "13691369g";

	@Before
	public void setup() {
		driver = new ChromeDriver();

		// And now use this to visit Github
		driver.get(GIT_HUB_URL);
		// Sign in page
		driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/span/div/a[1]")).click();

	}

	/**
	 * Test to see if an user can successfully login to Github.
	 *
	 * @TODO - Complete this test
	 */
	@Test
	public void testGitHubLoginSuccess() {
		// Call the below code for Successful login
		successfulLogin();
		System.out.println("Test case 1/4 login : SUCCESS");
	}

	/**
	 * Test to see if an unknown user cannot login to Github.
	 *
	 * @TODO - Complete this test
	 */
	@Test
	public void testGitHubLoginFailure() {

		WebElement element = null;

		driver.findElement(By.xpath("//*[@id=\"login_field\"]")).sendKeys(USERNAME);
		// PASSWORD + "randomTest" is used to create incorrect password
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PASSWORD + "randomText");
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[4]/input[3]")).click();

		try {
			// finding error container
			element = driver.findElement(By.xpath("//*[@id=\"js-flash-container\"]/div/div"));
		} catch (Exception e) {

		}
		Assert.assertNotNull(element);
		// making sure the right error displayed
		Assert.assertEquals("Incorrect username or password.", element.getText());
		System.out.println("Test case 2/4 login failure : SUCCESS");
	}

	/**
	 * Test to see that a registered user can successfully create a new repository.
	 *
	 * @TODO - Complete this test
	 */
	@Test
	public void testStartAProjectSuccess() {
		WebElement element = null;
		successfulLogin();
		driver.findElement(By.xpath("//*[@id=\"your_repos\"]/div/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"repository_name\"]")).sendKeys("test");
		driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button")).submit();
		try {
			// Trying to find the repository name after its creation
			element = driver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/div[1]/div/h1/strong/a"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		Assert.assertEquals("test", element.getText());
		System.out.println("Test case 3/4 Repo created successfully : SUCCESS");

	}

	//
	// /**
	// * Test to see that a registered user cannot create a new repository
	// * when it has the same name as an existing repository.
	// *
	// * @TODO - Complete this test
	// */
	@Test
	public void testStartAProjectFailure() {
		WebElement element = null;
		successfulLogin();
		driver.findElement(By.xpath("//*[@id=\"your_repos\"]/div/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"repository_name\"]")).sendKeys("test");

		try {
			// Finding the disable button
			element = driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		boolean isDisabled = !(element.isEnabled());
		Assert.assertTrue(isDisabled);
		System.out.println("Test case 4/4 Repo already exists : SUCCESS");

	}

	@After
	public void tearDown() {
		driver.close();
	}

	/**
	 * Reusable code to successfully log-in to GitHub
	 */
	private void successfulLogin() {
		WebElement element = null;
		driver.findElement(By.xpath("//*[@id=\"login_field\"]")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[4]/input[3]")).click();

		try {
			element = driver.findElement(By.xpath("//*[@id=\"user-links\"]/li[3]/details/ul/li[9]/form/button"));
		} catch (Exception e) {

		}
		Assert.assertNotNull(element);
	}
}
