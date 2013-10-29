package com.milgo.cubby.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.milgo.cubby.dao.UserDetailsDao;
import com.milgo.cubby.model.UserDetails;

@ContextConfiguration(locations = "/TestClass-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class TestClass{
	
	@Autowired 
	private UserDetailsDao userDetailsDao;
	
	@Before
    public void setUp() {
		//MockitoAnnotations.initMocks(this);
    }
	
/*	@Test
	public void showHomePageTest() {
		
		MainController mainController = new MainController();
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		String result = mainController.showLoginPage(model);
		assertEquals("login", result);
		//fail("Not yet implemented");
	}*/
	
	//przypomnij sobie o opensession w hibernate
	@Test
	public void addAndRemoveUserTest(){
		
		UserDetails user = new UserDetails();
		user.setLogin("testlogin");
		user.setPassword("testpassword");
		
		userDetailsDao.addUser(user);
		
		UserDetails user2 = userDetailsDao.getUser("testlogin");
		assertTrue(user2 != null);
		assertTrue(user2.getLogin().endsWith("testlogin"));
		
		userDetailsDao.removeUser(user2);
		UserDetails user3 = userDetailsDao.getUser("testlogin");
		assertTrue(user3 == null);
		
	}

}
