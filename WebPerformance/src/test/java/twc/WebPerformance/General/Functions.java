package twc.WebPerformance.General;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import twc.WebPerformance.ReadFileData.read_XML_File;

@SuppressWarnings("unused")
public class Functions extends Drivers {

	public static void charlesOpen() throws Exception{
		String[] openCharles_str ={"/bin/bash", "-c", "open -a charles"};
		Runtime.getRuntime().exec(openCharles_str);	
		Thread.sleep(5000);
	}

	public static void charlesClose() throws Exception{
		String[] quitCharles ={"/bin/bash", "-c","osascript -e 'quit app \"charles\"'"};
		Runtime.getRuntime().exec(quitCharles);
		Thread.sleep(5000);
		System.out.println("Charles was quit successfully");
	}

	public static void firefoxDriversClose() throws Exception{
		driver.close();
	}

	public static void chromeDriverClose() throws Exception{
		chromedriver.quit();
	}
	public static void openCharlesBrowser() throws IOException, Exception{

		File index = new File("/Users/monocept/Documents/workspace_luna/WebPerformance/CapturedFile/");

		if (!index.exists()) {
			//System.out.println("Specified folder is not exist and creating the same folder now");
			index.mkdir();
		} else {
			//System.out.println("Specified folder is exist and deleting the same folder");
			FileUtils.cleanDirectory(index);
		}

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", "/Users/monocept/Documents/workspace_luna/WebPerformance/CapturedFile/");
		profile.setPreference("browser.helperApps.neverAsk.openFile","text/xml,text/csv,application/x-msexcel,application/octet-stream,application/vnd.android.package-archive,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/apk");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/xml,text/csv,application/x-msexcel,application/octet-stream,application/vnd.android.package-archive,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/apk");

		driver = new FirefoxDriver(profile);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);

		driver.get("http://mohantestengg:123456@control.charles");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Session")).click();
		Thread.sleep(1000);
	}

	public static void startSessionBrowserData() throws Exception{

		Thread.sleep(1000);
		driver.findElement(By.linkText("Clear Session")).click();
		System.out.println("Clear Session Data");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Back")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Recording")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Start")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Back")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Session")).click();
	}
	
	public static void deleteFile() throws Exception{
		File index = new File("/Users/monocept/Documents/workspace_luna/WebPerformance/CapturedFile/");

		if (!index.exists()) {
			//System.out.println("Specified folder is not exist and creating the same folder now");
			index.mkdir();
		} else {
			//System.out.println("Specified folder is exist and deleting the same folder");
			FileUtils.cleanDirectory(index);
		}
	}
	public static void clearSession() throws Exception{

		Thread.sleep(1000);
		driver.findElement(By.linkText("Clear Session")).click();
		System.out.println("Clear Session Data");
		
	}
	public static void openChromeBrowser() throws IOException, Exception{

//		ChromeOptions options = new ChromeOptions();
//		options.addExtensions(new File("/Users/monocept/Documents/workspace_luna/WebPerformance/AdBlockCRXFile/extension_3_1.crx"));

		chromedriver = new ChromeDriver();
		Thread.sleep(2000);
		//startSessionBrowserData();
		deleteFile();
		clearSession();
		chromedriver.get("https://weather.com/weather/today/l/USNY1899:1:US");
		final JavascriptExecutor js = (JavascriptExecutor) chromedriver;
		// time of the process of navigation and page load
		double loadTime = (Double) js.executeScript(
				"return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
		System.out.print(loadTime + " seconds"); 
		ExportSessionXMLFile();
		
	}

	public static void ExportSessionXMLFile() throws Exception{


		Thread.sleep(1000);
		System.out.println("Exporting The Session Data Into XML File");
		driver.findElement(By.linkText("Export Session as XML")).click();
		Thread.sleep(2000);
		
	}

	/* --- Start Get File Names from Folder  --- */
	public static List<String> listFiles(String directoryName){

		//String file_name = null;
		List<String> filelist = new ArrayList<String>();
		File directory = new File(directoryName);
		//get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList){
			if (file.isFile()){
				filelist.add(file.getName());
				//file_name = file.getName();
				//break;
			}
		}
		return filelist;
	}/* --- End Get File Names from Folder  --- */

	public static void ReadXMLfile() throws Exception{

		read_XML_File result = new read_XML_File();
		String values = result.read_xml_into_buffer_string();
		System.out.println("Requests Count : "+values);
	}
}


