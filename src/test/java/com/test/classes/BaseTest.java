package com.test.classes;

import java.io.IOException;

import org.testng.annotations.AfterSuite;

public class BaseTest {

	CommandPrompt cp = new CommandPrompt();
	
	@AfterSuite()
	public void finalEnd() throws InterruptedException, IOException{
		cp.runCommand("killall -9 node");
	}
}
