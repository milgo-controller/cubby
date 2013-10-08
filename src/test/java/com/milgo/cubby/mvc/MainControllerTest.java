package com.milgo.cubby.mvc;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class MainControllerTest {
	
	@Test
	public void testShowHomePage() {
		
		MainController mainController = new MainController();
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		String result = mainController.showLoginPage(model);
		assertEquals("login", result);
		//fail("Not yet implemented");
	}

}
