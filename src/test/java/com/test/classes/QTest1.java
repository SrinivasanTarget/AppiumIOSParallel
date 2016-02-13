package com.test.classes;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.IOSServerFlag;

public class QTest1{

	private IOSDriver<MobileElement> driver;
	private AppiumDriverLocalService service;
	
	
	AvailabelPorts avlport = new AvailabelPorts();
	@BeforeClass(alwaysRun = true)
	public void startApp() throws Exception {
		System.out.println("Starting Appium Server1");
		File classPathRoot = new File(System.getProperty("user.dir"));
		int port = avlport.getPort();
		int bootstrapPort=avlport.getPort();
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"/usr/local/lib/node_modules/appium/bin/appium.js"))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
				.withArgument(IOSServerFlag.USE_NATIVE_INSTRUMENTS)
				.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(bootstrapPort))
				.withArgument(GeneralServerFlag.TEMP_DIRECTORY,
						new File(String.valueOf(classPathRoot)).getAbsolutePath() + "/target/" + "tmp_" + port)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE).usingPort(port);
		service = builder.build();
		service.start();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Iphone");
//		capabilities.setCapability("autoLaunch", true);
		capabilities.setCapability("newCommandTimeout", 90);
		capabilities.setCapability("udid", "3ea8e5503948cfce2076352f7a0881f76b4e7935");
		capabilities.setCapability("bundleId", "");
		capabilities.setCapability("noReset", true);
		driver = new IOSDriver<MobileElement>(service.getUrl(),capabilities);
		Thread.sleep(10000L);
	}

	@AfterClass()
	public void killServer() throws InterruptedException, IOException {
		driver.quit();
	}
	

	@Test
	public void testMethodFive_5() throws Exception {
		System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@name='action_bar_up_navigation'][1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@name='Store locator']")).click();
		Thread.sleep(3000);

	}
}
