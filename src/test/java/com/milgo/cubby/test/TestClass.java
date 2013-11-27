package com.milgo.cubby.test;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

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

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/cubby-servlet.xml"/*, "/TestClass-context.xml"*/})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class TestClass{
	
	@Autowired 
	private UserDetailsDao userDetailsDao;
	
	private static Validator validator;
	
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
	public void isAddingAndRemovingUser(){
		UserDetails user = new UserDetails();
		user.setLogin("testlogin");
		user.setPassword("testpassword");
		user.setConfirmPassword(user.getPassword());
		userDetailsDao.addUser(user);
		UserDetails user2 = userDetailsDao.getUserByLogin("testlogin");
		assertTrue(user2 != null);
		assertTrue(user2.getLogin().endsWith("testlogin"));
		userDetailsDao.removeUser(user2);
		UserDetails user3 = userDetailsDao.getUserByLogin("testlogin");
		assertTrue(user3 == null);
	}
	//dodanie uzytkownika o tej samej nazwie
	//wymagania odnosnie hasla
	
	@Test(expected=ConstraintViolationException.class)
	public void isCheckingToConfirmPassword(){
		UserDetails user = new UserDetails();
		user.setLogin("testlogin");
		user.setPassword("testpassword");
		userDetailsDao.addUser(user);
	}
	
	//@Test(expected=ConstraintViolationException.class)
	public void isCheckingUniqueLogin(){
		UserDetails user = new UserDetails();
		user.setLogin("testlogin");
		user.setPassword("testpassword");
		userDetailsDao.addUser(user);
		
		try{
			userDetailsDao.addUser(user);//?
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Set<ConstraintViolation<UserDetails>> constraintViolatios =
				validator.validate(user);
	    assertTrue(1 == constraintViolatios.size());
	    assertTrue(constraintViolatios.iterator().next().getMessage().equals("Unique login violation"));
	}
}
