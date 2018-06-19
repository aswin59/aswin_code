package twc.Automation.HandleWithAppium;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import twc.Automation.Driver.Drivers;
import twc.Automation.ReadDataFromFile.read_excel_data;
import twc.Automation.General.DeviceStatus;

public class AppiumFunctions extends Drivers{
	static int startY;
	static int endY;
	public static void Swipe_Up() throws Exception{
		Thread.sleep(2000);
		Dimension dimensions = Ad.manage().window().getSize();
		Double startY1 = dimensions.getHeight() * 0.7;  
		startY = startY1.intValue();
		Double endY1 = (double) (dimensions.getHeight()/6);  //  dimensions.getHeight()  0.2;  == 512.0
		endY = endY1.intValue();
		System.out.println("endY  - "+endY);
		System.out.println("startY  - "+startY);
		Ad.swipe(0, endY, 0, startY,2000);
		Thread.sleep(2000);
	}
	public static void killADB() throws IOException{
		String[] command ={"/usr/bin/killall","-KILL","adb"};
        Runtime.getRuntime().exec(command); 
        
        String[] command1 ={"/usr/bin/killall","-KILL","-9 adb"}; 
        Runtime.getRuntime().exec(command1);
	}
	
	public static void UnInstallApp() throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] paths = read_excel_data.exceldataread("Paths");
		String adbPath = paths[15][Cap];

		//System.out.println("adbPath "+ adbPath);

		System.out.println("Uninstall the APP and Installing");

		String[] uninstall ={"/bin/bash", "-c",  "adb uninstall com.weather.Weather"};
		try{
			Runtime.getRuntime().exec(uninstall);
			System.out.println("Uninstall completed");
			Thread.sleep(5000);
		}catch(Exception e){
			System.out.println("Build not available in the device / not able to uninstall");
		}
	}
	
	public static void AppiumServerStop() throws InterruptedException{
		
		String[] command ={"/usr/bin/killall","-KILL","node"};  
        
        try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			System.out.println("Appium Server Not Yet Killed At This Time");
		}  
        Thread.sleep(5000);
	}
	
	public static void clearTWCLogs() throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] paths = read_excel_data.exceldataread("Paths");
		System.out.println("Clear Logcat Logs for TWC App");	
		String[] clearLogcatdata ={"/bin/bash", "-c",  paths[15][Cap]+" logcat -c"};
		Runtime.getRuntime().exec(clearLogcatdata);	
		Thread.sleep(4000);
	}
	
	public static void AppiumServerStart() throws InterruptedException{
		
		CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
		command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js", false);
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument("4723");	
		command.addArgument("--no-reset", false);
		command.addArgument("--log-level", false);
		command.addArgument("error");
		
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
			Thread.sleep(5000);
		} catch (ExecuteException e) {
			System.out.println("Appium Server Not Yet Started");
		} catch (IOException e) {
			System.out.println("Appium Server Not Yet Started");
		}
	}
	
	public static void ReLaunchApp() throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		clearTWCLogs();
		
		String[][] paths = read_excel_data.exceldataread("Paths");
		String adbPath = paths[15][Cap];
		
		String[] str ={"/bin/bash", "-c", adbPath+" shell pm disable com.weather.Weather"};
		Runtime.getRuntime().exec(str);
		Thread.sleep(2000);
		
		String[] str1 ={"/bin/bash", "-c", adbPath+" shell pm enable com.weather.Weather"};
		Runtime.getRuntime().exec(str1);
		
		Ad.closeApp();
		Ad.launchApp();
	}
	
	public static void clearCache() throws Exception{
		Thread.sleep(3000);
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		String[][] paths = read_excel_data.exceldataread("Paths");
		String adbPath = paths[15][Cap];
		
		String[] str ={"/bin/bash", "-c", adbPath+" shell pm clear com.weather.Weather"};
		Runtime.getRuntime().exec(str);
		System.out.println("Cleared The App Data Successfully");
		Thread.sleep(2000);
		
	}
	
	//Kill and relaunch the app
	public static void Kill_launch() throws Exception{
		try{
			Thread.sleep(5000);
			Ad.closeApp();
			Thread.sleep(5000);
			Ad.launchApp();
			Thread.sleep(5000);
			After_launch();
		}catch(Exception e){

		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void LaunchApp() throws InterruptedException, IOException{
			
//			killADB();
//			AppiumServerStop();
//			AppiumServerStart();
			
			logStep("Launch The App");
			DeviceStatus device_status = new DeviceStatus();
			int Cap = device_status.Device_Status();
			
			try {
				
				String[][] capabilitydata = read_excel_data.exceldataread("Capabilities");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				
				/* --- Start Android Device Capabilities --- */
				if(Cap == 2){
				capabilities.setCapability(capabilitydata[1][0], capabilitydata[1][Cap]);
				capabilities.setCapability(capabilitydata[2][0], capabilitydata[2][Cap]); 
				capabilities.setCapability(capabilitydata[3][0], capabilitydata[3][Cap]);
				capabilities.setCapability(capabilitydata[7][0], capabilitydata[7][Cap]); 
				capabilities.setCapability(capabilitydata[10][0],capabilitydata[10][Cap]);
				capabilities.setCapability(capabilitydata[12][0],capabilitydata[12][Cap]);
				capabilities.setCapability(capabilitydata[13][0],capabilitydata[13][Cap]);
				capabilities.setCapability(capabilitydata[14][0],capabilitydata[14][Cap]);
				Ad = new AndroidDriver(new URL(capabilitydata[15][Cap]), capabilities);
				}
				/* ---End Android Device Capabilities --- */
				Ad.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				try{
					if((Ad.findElementByName("OK")).isDisplayed()){
						Ad.findElementByName("OK").click();
					}else if((Ad.findElementByName("Continue")).isDisplayed()){
						Ad.findElementByName("Continue").click();
					}else if((Ad.findElementByName("Allow")).isDisplayed()){
						Ad.findElementByName("Allow").click();
					}
				}catch(Exception e){

				}
				
				System.out.println("Capabilities have been launched");
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Unable To Launch The Appium Capabilities");
			}
	}

	
	//Handle popups after launch the app
	public static void After_launch(){
		try{
			if((Ad.findElementByName("Allow")).isDisplayed()){
				Thread.sleep(3000);
				Ad.findElementByName("Allow").click();
			}
		}catch(Exception e){
			System.out.println("Location already set");
		}
		try{
				if((Ad.findElementByName("OK")).isDisplayed()){
					Ad.findElementByName("OK").click();
				}else if((Ad.findElementByName("Continue")).isDisplayed()){
					Ad.findElementByName("Continue").click();
				}else if((Ad.findElementByName("Allow")).isDisplayed()){
					Ad.findElementByName("Allow").click();
				}
			}catch(Exception e){

			}
		
		try{
			Thread.sleep(3000);
			
			if(Ad.findElementByName("Please Search For A Location").isDisplayed()){
				System.out.println("Address not found, Entering Manually");
				Ad.findElementByName("Search").click();
				Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.view.ViewGroup[1]/android.support.v7.widget.LinearLayoutCompat[1]").sendKeys("30339");
				Thread.sleep(8000);
				Ad.tap(1, 380, 265, 1000);
				Thread.sleep(5000);
				
			}
			
		}catch(Exception e){
			
		}
		
	}
	
	
	//launch app with full reset
	
	
	@SuppressWarnings("rawtypes")
	public static void LaunchAppWithFullReset(String Reset) throws Exception{
		
		
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		try {
			
			String[][] capabilitydata = read_excel_data.exceldataread("Capabilities");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			/* --- Start Android Device Capabilities --- */
			if(Cap == 2){
			capabilities.setCapability(capabilitydata[1][0], capabilitydata[1][Cap]);
			capabilities.setCapability(capabilitydata[2][0], capabilitydata[2][Cap]); 
			capabilities.setCapability(capabilitydata[3][0], capabilitydata[3][Cap]);
			capabilities.setCapability(capabilitydata[7][0], capabilitydata[7][Cap]); 
			capabilities.setCapability(capabilitydata[9][0], Reset);
			capabilities.setCapability(capabilitydata[10][0],capabilitydata[10][Cap]);
			capabilities.setCapability(capabilitydata[12][0],capabilitydata[12][Cap]);
			capabilities.setCapability(capabilitydata[13][0],capabilitydata[13][Cap]);
			capabilities.setCapability(capabilitydata[14][0],capabilitydata[14][Cap]);
			Thread.sleep(20000); 
			Ad = new AndroidDriver(new URL(capabilitydata[15][Cap]), capabilities);
			Thread.sleep(20000); 
			}
			/* ---End Android Device Capabilities --- */
			Ad.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Thread.sleep(20000);
			After_launch();
			
			
			System.out.println("Capabilities have been launched  with fullreset with - "+Reset);
			Thread.sleep(5000);
		} 
			catch (Exception e) {
			System.out.println("Unable To Launch The Appium Capabilities");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void LaunchAppWithFullReset() throws InterruptedException, IOException{
		
		killADB();
		AppiumServerStop();
		AppiumServerStart();
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		
		try {
			
			String[][] capabilitydata = read_excel_data.exceldataread("Capabilities");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			/* --- Start Android Device Capabilities --- */
			if(Cap == 2){
			capabilities.setCapability(capabilitydata[1][0], capabilitydata[1][Cap]);
			capabilities.setCapability(capabilitydata[2][0], capabilitydata[2][Cap]); 
			capabilities.setCapability(capabilitydata[3][0], capabilitydata[3][Cap]);
			capabilities.setCapability(capabilitydata[7][0], capabilitydata[7][Cap]); 
			capabilities.setCapability(capabilitydata[9][0], capabilitydata[9][Cap]);
			capabilities.setCapability(capabilitydata[10][0],capabilitydata[10][Cap]);
			capabilities.setCapability(capabilitydata[12][0],capabilitydata[12][Cap]);
			capabilities.setCapability(capabilitydata[13][0],capabilitydata[13][Cap]);
			capabilities.setCapability(capabilitydata[14][0],capabilitydata[14][Cap]);
			Ad = new AndroidDriver(new URL(capabilitydata[15][Cap]), capabilities);
			}
			/* ---End Android Device Capabilities --- */
			Ad.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			//####added ths to handle allow button
			Thread.sleep(20000);
			try{
				if((Ad.findElementById("com.android.packageinstaller:id/permission_allow_button").isDisplayed())){
                    Ad.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
				}
			}catch(Exception e){
				System.out.println("Location already set");
			}
			
			
			//######
			try{
					if((Ad.findElementByName("OK")).isDisplayed()){
						Ad.findElementByName("OK").click();
					}else if((Ad.findElementByName("Continue")).isDisplayed()){
						Ad.findElementByName("Continue").click();
					}else if((Ad.findElementById("com.android.packageinstaller:id/permission_allow_button").isDisplayed())){
	                    Ad.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
					}
				}catch(Exception e){

				}
			System.out.println("Capabilities have been launched  with fullreset ");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Unable To Launch The Appium Capabilities");
		}
	}
	
}
