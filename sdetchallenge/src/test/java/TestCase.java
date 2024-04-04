import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Alert;
import java.util.ArrayList;
import java.util.List;

public class TestCase {
    public static void main(String[] args) throws InterruptedException {

            // Creating an Object to access the list created
            Inputdata_DynamicGeneration obj =new Inputdata_DynamicGeneration();
            List<ArrayList<Integer>> allList = new ArrayList<>();
            allList = obj.randomInput();
            //Diving the list into three groups for weighing
            ArrayList<Integer> part1 = allList.get(0);
            ArrayList<Integer> part2 = allList.get(1);
            ArrayList<Integer> part3 = allList.get(2);

            // Setting the path to the FirefoxDriver
            System.setProperty("webdriver.chrome.driver", "./resources/geckodriver.exe");
            // Create a new instance of the Firefox driver
            WebDriver driver = new FirefoxDriver();
            // Maximizing the browser window
            driver.manage().window().maximize();
            // Open the browser and navigate to a URL
            driver.get("http://sdetchallenge.fetch.com/");
            Thread.sleep(3000);

            // Calling the method initialGroup by passing two groups of weighing 1 group in left bowl and 1 group in right bowl and firefox driver
            String result=initialGroup(part1,part2,driver);
            //Based on the above outcome making decision to pick which group has lesser bar
            Integer value;
            if (result.equals("<"))
            {
               value=FinalGroup(part1,driver);
            }
            else if(result.equals(">"))
            {
                value=FinalGroup(part2,driver);
            }
            else
            {
                value=FinalGroup(part3,driver);
            }
            Thread.sleep(5000);
            // Reading the text to display the weighings made
            String weighing = driver.findElement(By.className("game-info")).getText();
            //Clicking on Fake bar or lesser weight
            driver.findElement(By.id("coin_"+value)).click();
            //Moving to alert and capturing the text on the alert
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            //Displaying the output in the console
            System.out.println("***********************************");
            System.out.println("Alert message: " + alertText);
            System.out.println("The Fake Bar Number is : "+ value);
            System.out.println("Number of weighing is : 2");
            System.out.println("List of "+weighing);
            System.out.println("***********************************");
            Thread.sleep(10000);
            //Close the browser
            driver.quit();
    }


    //Method accepting the Group 1 and Group 2 and returning the result one of them(< or > or =)
    private static String initialGroup(ArrayList<Integer> part1, ArrayList<Integer> part2, WebDriver driver) throws InterruptedException {
        for(int i = 0; i < part1.size(); i++) {
            //Entering the bars in left bowl or pan
            WebElement left_bowl = driver.findElement(By.id("left_" + part1.get(i)));
            left_bowl.sendKeys(String.valueOf(part1.get(i)));
        }
        for(int i = 0; i < part2.size(); i++) {
            //Entering the bars in right bowl or pan
            WebElement right_bowl = driver.findElement(By.id("right_" + part2.get(i)));
            right_bowl.sendKeys(String.valueOf(part2.get(i)));
        }
        //Clicking on Weigh button
        WebElement button_weigh = driver.findElement(By.id("weigh"));
        button_weigh.click();
        Thread.sleep(5000);
        //Reading the result of the weighings made
        String result = driver.findElement(By.xpath("//div[@class='result']//button[@id='reset']")).getText();
        return result;
    }


    //Method accepting the last three weights and returning the fault weight for printing in the console
    private static Integer FinalGroup(ArrayList<Integer> test, WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//div[4]//button[1]")).click();
        //Entering the bars in left bowl or pan
        WebElement left_bowl = driver.findElement(By.id("left_" + test.get(0)));
        left_bowl.sendKeys(String.valueOf(test.get(0)));
        //Entering the bars in right bowl or pan
        WebElement right_bowl = driver.findElement(By.id("right_" + test.get(1)));
        right_bowl.sendKeys(String.valueOf(test.get(1)));
        //Clicking on Weigh button
        WebElement button_weigh1 = driver.findElement(By.id("weigh"));
        button_weigh1.click();
        Thread.sleep(5000);
        //Reading the result of the weighings made and returning the fault bar
        String result1 = driver.findElement(By.xpath("//div[@class='result']//button[@id='reset']")).getText();
        if (result1.equals("<")) {
            return test.get(0);
        } else if (result1.equals(">")) {
            return test.get(1);
        } else {
            return test.get(2);
        }
    }

}
