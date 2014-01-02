package com.milgo.cubby.test;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Set;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.milgo.cubby.dao.UserDetailsDao;
import com.milgo.cubby.model.UserDetails;

@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/cubby-servlet.xml", 
		"/TestClass-context.xml" })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@DatabaseSetup(value="/test-database.xml", type=DatabaseOperation.CLEAN_INSERT)
public class TestClass {
	
	@Autowired private UserDetailsDao userDetailsDao;
	@Autowired private WebApplicationContext wac;
	@Autowired private Validator validator;
	@Autowired private DataSource dataSource;
	
	private MockMvc mockMvc;
	
	@Before
    public void setUp() {
		//MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
/*	@Test sprobuj mockowaniem
	public void showHomePageTest() {
		
		MainController mainController = new MainController();
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		String result = mainController.showLoginPage(model);
		assertEquals("login", result);
		//fail("Not yet implemented");
	}*/
	
	//@Test
	public void testHomePage() throws Exception{
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}
	
	@Test
	public void testAddUser() throws Exception{
		UserDetails user = new UserDetails();
		user.setId(1);
		user.setLogin("testlogin123");
		user.setPassword("testpassword");
		user.setConfirmPassword("testpassword");
		
		mockMvc.perform(post("/register")
				.sessionAttr("userDetails", user))
		.andExpect(status().isOk())
		.andExpect(view().name("userAdded"));
	}
	
	//przypomnij sobie o opensession w hibernate
	// @Test
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
	
	//@Test(expected=ConstraintViolationException.class)
	//@Test
	public void isConfirmPasswordConstraintValidation(){
		UserDetails user = new UserDetails();
		user.setLogin("testlogin");
		user.setPassword("testpassword");
		//userDetailsDao.addUser(user);
		Set<ConstraintViolation<UserDetails>> violations = validator.validate(user);
		assertTrue("Expected violation error found", !violations.isEmpty());
		System.out.println(violations.iterator().next().getMessage());
	    assertTrue(violations.iterator().next().getMessage().equals("Unique login violation"));
	}
	
	//@Test(expected=ConstraintViolationException.class)
//	@Test
	public void isUniqueLoginConstraintValidation(){
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
