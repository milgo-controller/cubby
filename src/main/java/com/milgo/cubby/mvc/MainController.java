package com.milgo.cubby.mvc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.milgo.cubby.bo.RoleBo;
import com.milgo.cubby.bo.TrainingBo;
import com.milgo.cubby.bo.UserBo;
import com.milgo.cubby.model.Role;
import com.milgo.cubby.model.Training;
import com.milgo.cubby.model.User;
import com.milgo.cubby.model.UserTraining;


/**
 * Klasa kontrolera. Steruje dzia³aniem aplikacji. 
 *
 */

@Controller
public class MainController {
	
	/** 
	 * Zmienna, do której wstrzykiwana jest instancja klasy implementuj¹ca interfejs UserBo (komponent Spring MVC) 
	 */
	@Autowired
	public UserBo userBo;
	
	/** 
	 * Zmienna, do której wstrzykiwana jest instancja klasy implementuj¹ca interfejs RoleBo (komponent Spring MVC) 
	 */
	@Autowired
	public RoleBo roleBo;
	
	/** 
	 * Zmienna, do której wstrzykiwana jest instancja klasy implementuj¹ca interfejs TrainingBo (komponent Spring MVC) 
	 */
	@Autowired
	public TrainingBo trainingBo;
	
	
	/** 
	 * Metoda mapuj¹ca adres "/admin" (panel administratora) dla metody GET. Pobiera listê u¿ytkowników (wszystkich oprócz administratora) i listê treningów, 
	 * przekierowywyje do widoku admin.
	 * 
	 * @param model lista u¿ytkowników w postaci kolekcji typu Map przekazywana do widoku (w tym przypadku widoku admin)
	 */
	@RequestMapping({"/admin"})
	public String showAdminPage(Map<String, Object> model){
		
		/* Pobieramy dane aktualnie zalogowanego administratora */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User loggedUser = userBo.getUserByLogin(name);
		
	    /* Pobieramy liste wszystkich u¿ytkowników */
	    List<?> userList = userBo.getAllUsers();
	    
	    /* Iterator do listy u¿ytkowników */
		Iterator<?> iterator = userList.iterator();

		/* W pêtli przechodzimy po kazdym u¿ytkowników poprzez iterator */
	    while (iterator.hasNext()) {
			User user = (User)iterator.next();
				
			/* Usuwamy z listy uzytkownika który wyœwietla t¹ liste */
			if(loggedUser.getRole().getRoleName().compareTo("ADMIN") == 0)
			{
				if(user.getRole().getRoleName().compareTo("ADMIN") == 0)
						iterator.remove();
			}
			else
			{
				if((user.getRole().getRoleName().compareTo("ADMIN") == 0) ||
					(user.getRole().getRoleName().compareTo("MODERATOR") == 0)	)
						iterator.remove();
			}
				
			/* Pobieramy nazwê roli u¿ytkownika (poniewa¿ w bazie danych jest ona reprezentowana liczbowo */
			user.setRoleName(user.getRole().getRoleName());
	    }
	    
	    /* Dodajemy liste uzytkownikow do modelu, który zostanie przes³any do widoku */
		model.put("usersList", userList);
		
		/* Dodajemy liste wszystlkich treningow do modelu, który zostanie przeslany do widoku */
		List<?> trList =  trainingBo.getAllTrainings();
		model.put("trainingsList", trList);
		
		/* Przekierowijemy do widoku admin */
		return "admin";
	}
	
	
	/** 
	 * Metoda mapuj¹ca adres "/admin/remove/user/{login}" (panel administratora) dla metody GET. Usuwa u¿ytkownika na podstawie parametru {login} podanego w adresie, 
	 * a nastêpnie przekierowywyje do widoku admin.
	 * 
	 * @param login nazwa u¿ytkownika, który ma zostaæ usuniêty
	 */
	@RequestMapping(value="/admin/remove/user/{login}", method=RequestMethod.GET)
	public String adminRemoveUser(@PathVariable String login){
		
		/* Pobieramy uzytkownika o podanym loginie za pomoc¹ komponentu userBo */
		User deletedUser = userBo.getUserByLogin(login);
		
		/* Jeœli uzytkownik o podanym loginie istnieje usuwamy go z bazy */
		if(deletedUser != null)
			userBo.removeUser(deletedUser);
		
		/* Przekierowujemy spowrotem do strony /admin */
		return "redirect:/admin";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/admin/edit/user/{login}" (panel administratora) dla metody GET. Przygotowywuje dane to wys³ania do formularza w widoku edit_user przeznaczonego do edycji danych uzytkownika, 
	 * a nastêpnie przekierowywyje do widoku edit_user.
	 * 
	 * @param login nazwa u¿ytkownika, który ma zostaæ edytowany
	 * @param model dane biznesowe uzytkownika (przekazywane do widoku training_edit)
	 */
	@RequestMapping(value="/admin/edit/user/{login}", method=RequestMethod.GET)
	public String showAdminEditUser(@PathVariable String login, Model model){		
		
		/* Pobieramy u¿ytkownika o podanym loginie */
		User user = userBo.getUserByLogin(login);
		
		/* Pobieramy listê wszystkich roli u¿ytkowników */
		List<?> roles = roleBo.getAllRoles();

		/* Tworzymy listê dla nazw wszystkich roli u¿ytkowników */
		HashMap<String, String> roleNames = new LinkedHashMap<String, String>();
		
		/* Wpisujemy w listê nazwy wszytkich roli u¿ytkowników (oprócz administratora)*/
		for(Object role: roles){
			String roleName = ((Role)role).getRoleName();
			if(roleName.compareTo("ADMIN") == 0)continue;
			roleNames.put(roleName, roleName);
		}
		
		/* zapisujemy u¿ytkownikowi nazwê jego roli */
		user.setRoleName(user.getRole().getRoleName());
		
		/* zapisujemy u¿ytkownikowi nazwy wszystkich dostêpnych roli (w celu wyœwietlania na formularzu)*/
		user.setRoleNames(roleNames);
		
		/* dodajemy u¿ytkownika do modelu, który zostanie przekazany do widoku */
		model.addAttribute("user", user);
		
		/* pobieramy liste traningów danego u¿ytkownika */
		Set<UserTraining> userTrainings = user.getUserTrainings();
		
		/* dodajemy liste treningów do modelu, który zostanie przekazany do widoku */
		model.addAttribute("userTrainings", userTrainings);
		
		/* przekierowujemy do widoku user_edit */
		return "user_edit";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/admin/edit/user/{login}" (panel administratora) dla metody POST. Zapisuje dana edytowane w widoku user_edit, 
	 * a nastêpnie przekierowywyje do widoku admin.
	 * 
	 * @param login nazwa u¿ytkownika, który ma zostaæ edytowany
	 * @param userForm (dane biznesowe) z formularza uzytkownika
	 * @param bindingResult przechowuje wynik walidacji (b³êdy z formularza)
	 */
	@RequestMapping(value="/admin/edit/user/{login}", method=RequestMethod.POST)
	public ModelAndView adminEditUser(@PathVariable String login, @Valid User userForm, BindingResult bindingResult){
		
		/* Troche inny sposób komunikacji model/widok tworzymy obiekt klasy ModelAndView i odrazu przekazujemy nazwê widoku docelowego */
		ModelAndView mav = new ModelAndView("user_edit");
		
		/* Sprawdzamy czy przes³any formularz zawiera³ b³êdy */
		if(bindingResult.hasErrors()){
			/* Jeœli tak to dodaj do modelu ten sam formularz i przekieruj z powrotem (do poprawy) */
			mav.addObject("user", userForm);
			return mav;
		}
		
		/* Jesli formularz jest poprawny zmieñ decelowy widok na admin */
		mav.setViewName("redirect:/admin");
		
		/* Pobieramy obiekt u¿ytkownika, który bêdzie aktualizowany */
		User updatedUser = userBo.getUserByLogin(login);
		
		/* Zmieniamy niektóre w³aœciwoœci u¿ytkownika na podstawie danych z formularza */
		updatedUser.setAddress(userForm.getAddress());
		updatedUser.setEmail(userForm.getEmail());
		updatedUser.setFirstName(userForm.getFirstName());
		updatedUser.setLastName(userForm.getLastName());
		updatedUser.setBirthDate(userForm.getBirthDate());
		
		/* Odblokowanie/zablokowanie u¿ytkownika do korzystania z mo¿liwoœci logowania */
		if(userForm.getEnabled() == null)
			updatedUser.setEnabled(0);
		else updatedUser.setEnabled(1);
			
		/* Pobieramy obiekt roli u¿ytkowanika na podstawie nazwy */ 
		Role role = roleBo.getRoleByName(userForm.getRoleName());
		
		/* Ustawiamy rolê dla u¿ytkownika */
		updatedUser.setRole(role);
		
		/* Ustawiamy has³o potwierdzaj¹ce (¿eby nie by³o b³êdu podczas sprawdzanie poprawnoœci has³a) */
		updatedUser.setConfirmPassword(updatedUser.getPassword());
		
		/* Zapisujemy zmodyfikowane dane uzytkownika */
		userBo.modifyUser(updatedUser);
		
		/* Zwracamy obiekt ModelAndView, który zawiera informacje o widoku, do którego zostaniemy przekierowani i model danych który zostanie przes³any do tego widoku */
		return mav;
	}
	
	/** 
	 * Metoda mapuj¹ca adres "admin/add/training" (panel administratora) dla metody GET. Przygotowuje dane do widoku training_edit, wykorzystywanego w tym przypadku do dodania nowego treningu 
	 * a nastêpnie przekierowywyje do widoku training_edit.
	 * 
	 * @param model  dane biznesowe dla nowego treningu (przekazywane do widoku training_edit).
	 */
	@RequestMapping({"admin/add/training"})
	public String showAddTrainingPage(ModelMap model){
		/* Dodajemy do modelu now¹ instancjê klasy Training, która bêdzie przekazana do widoku */
		model.addAttribute(new Training());
		/* Przekierowujemy do widoku training_edit */
		return "training_edit";
	}
	
	/** 
	 * Metoda pomocnicza - sprawdzaj¹ca poprawnoœæ danych dla podanej instancji klasy Training
	 * 
	 * @param training instancja klasy Training, któr¹ sprawdzamy
	 * @param bindingResult przechowuje wynik walidacji (b³êdy z formularza)
	 */
	public int validateTraining(Training training, BindingResult bindingResult)
	{
		/* Ustawiamy czy trening jest treningiem online */
		if(training.getOnline() == null)
			training.setOnline(0);
		else training.setOnline(1);
		
		/* Jeœli administarot nie wpisa³ ceny to cena = 0 */
		if(training.getCost() == null)
			training.setCost(0);		
		
		/* Jeœli przes³any formularz zawiera b³êdy zakoñcz funkcje zwracaj¹c 1 (co oznacza b³ad) */
		if(bindingResult.hasErrors())
		{
			/*List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }*/						    		    		    
			return 1;
		}
		
		/* Jesli trening nie jest online */ 
	    if(training.getOnline() == 0){
	    	
	    	/* Sprawdz czy data jest ustawiona. Jeœli nie to zakoñcz funkcje i zwróæ 1 (co oznacza b³¹d) */
	    	if(training.getStartDate() == null){
	    		bindingResult.rejectValue("startDate", "", "Please enter date of training!");
	    		return 1;
	    	}
	    	
	    	/* Sprawdz czy miejsce jest ustawione. Jeœli nie to zakoñcz funkcje i zwróæ 1 (co oznacza b³¹d) */
			if(training.getPlace().isEmpty()){
				bindingResult.rejectValue("place", "", "Please enter place of training!");
				return 1;
	    	}
	    }
	    else{ /* Jeœli trening jest online sprawdŸ czy URL nie jest pusty */
	    	if(training.getUrl().isEmpty()){
				bindingResult.rejectValue("url", "", "Please enter url!");
				return 1;
	    	}
	    }
	    
	    /* Jesli nie by³o b³êdów to zwróæ 0 (co oznacza ze wszystko przebirg³o poprawnie) */
		return 0;
	}
	
	/** 
	 * Metoda mapuj¹ca adres "admin/add/training" (panel administratora) dla metody POST. Sprawdza poprawnoœæ danych przekazanych przez formularz z widoku training_edit,
	 * a nastêpnie jeœli nie wykry³a b³êdów dodaje nowy trening do bazy danych. Na konicu przekierowywuje do widoku admin.
	 * 
	 * @param training instancja klasy Training, któr¹ dodajemy
	 * @param bindingResult przechowuje wynik walidacji (b³êdy z formularza)
	 */
	@RequestMapping(value="admin/add/training", method=RequestMethod.POST)
	public String adminEditTraining(@Valid Training training, BindingResult bindingResult){
		
		/* Sprawdzamy czy dane w obiekcie training s¹ poprawne i czy bindingResult nie zawiera informacji o b³edach w formularzu */
		int res = validateTraining(training, bindingResult);
		
		/* Je¿eli s¹ b³êdy to wracamy do widoku training_edit (¿eby u¿ytkownik poprawi³) */
		if(res > 0){
			return "training_edit";
		}
		else{ /*Je¿li nie ma b³edów to dodajemy trening do bazy */
			trainingBo.addTraining(training);
		}
		
		/* Przekierowujemy do strony /admin */
		return "redirect:/admin";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "admin/training/edit/{id}" (panel administratora) dla metody GET. Przygotowuje dane do przes³ania do formularza w widoku training_edit (w celu edycji tych danych),
	 * a nastêpnie przekierowywuje do widoku training_edit (widok z formularzem edycyjnym).
	 * 
	 * @param id numer id treningu
	 * @param model dane biznesowe treningu (przekazywane do widoku training_edit)
	 */
	@RequestMapping(value="admin/training/edit/{id}", method=RequestMethod.GET)
	public String adminEditTraining(@PathVariable Integer id, Model model)
	{
		/* Dodaj wyszukany trening do modelu, który bedzie przekazany do widoku w celu edycji */
		model.addAttribute("training", trainingBo.getTrainingById(id));
		
		/* PrzejdŸ do widoku training_edit */
		return "training_edit";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "admin/training/edit/{id}" (panel administratora) dla metody POST. Sprawdza poprawnoœæ danych przekazanych przez formularz z widoku training_edit,
	 *  jeœli nie wykry³a b³êdów zapisuje zmiany w trening (dokonane w widoku training_edit) do bazy danych i przekierowywuje do widoku trainin-edit. W razie b³êdów przekierowywuje z powrotem do widoku training_edit, gdzie na formularzu zostan¹ zaznaczone b³êdne informacje do poprawienia.
	 * 
	 * @param id numer id treningu
	 * @param training instancja klasy training, któr¹ edytujemy
	 * @param bindingResult przechowuje wynik walidacji (b³êdy z formularza)
	 */
	@RequestMapping(value="admin/training/edit/{id}", method=RequestMethod.POST)
	public String adminModifyTraining(@PathVariable Integer id, @Valid Training training, BindingResult bindingResult)
	{
		/* Sprawdzamy czy dane w obiekcie training s¹ poprawne i czy bindingResult nie zawiera informacji o b³edach w formularzu */
		int res = validateTraining(training, bindingResult);
		
		/* Je¿eli s¹ b³êdy to wracamy do widoku training_edit (¿eby u¿ytkownik poprawi³) */
		if(res > 0){
			return "training_edit";
		}
		else{ /*Je¿li nie ma b³edów to dodajemy trening do bazy */
			trainingBo.modifyTraining(training);
		}
		/* Przekierowujemy do widoku training_edit */
		return "training_edit";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "admin/training/remove/{id}" (panel administratora) dla metody GET. Usuwa training na podstawie parametru {id} podanego w adresie, 
	 * a nastêpnie przekierowywyje do widoku admin.
	 * 
	 * @param id id treningu, który ma zostaæ usuniêty
	 */
	@RequestMapping(value="admin/training/remove/{id}", method=RequestMethod.GET)
	public String adminRemoveTraining(@PathVariable Integer id){
		
		/* Usuwamy z bazy trening o podanym id */
		trainingBo.removeTraining(id);
		
		/* Przekierowujemy do strony /admin */
		return "redirect:/admin";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "admin/edit/user/{login}/training/activate/{id}" (panel administratora) dla metody GET. Aktywuje trening o numere {id} dla u¿ytkownika o loginie {login}, 
	 * a nastêpnie przekierowywyje do widoku admin/edit/user/{login} (czyli do strony na której administrator widzi dane u¿ytkownika o danym loginie). 
	 * 
	 * @param login nazwa uzytkownika, dla którego aktywujemy trening
	 * @param id id treningu, którego aktywujemy dla uzytkownika
	 */
	@RequestMapping(value="admin/edit/user/{login}/training/activate/{id}", method=RequestMethod.GET)
	public String activateUserTraining(@PathVariable("login") String login, @PathVariable("id") Integer id)
	{
		/* Pobieramy dane u¿ytkownika na podstawie loginu z adresu*/
		User user = userBo.getUserByLogin(login);

		/* W pêtli przeszukujemy trening o danym numerze id za pomoc¹ iteratora */
		Iterator<?> i = user.getUserTrainings().iterator();
		while(i.hasNext())
		{
			/* Nastêpny trening z listy */
			UserTraining ut = (UserTraining)i.next();
			
			/* Po odnalezieniu aktywujemy trening */
			if(ut.getPk().getTraining().getId() == id){
				ut.setActive(1);
			}
		}
		
		/* Zapisujemy zmiany */
		userBo.modifyUser(user);
		
		/* Przekierowujemy do strony /admin/edit/user/{login} */
		return "redirect:/admin/edit/user/"+login;
	}
	
	/** 
	 * Metoda mapuj¹ca adres "admin/edit/user/{login}/training/deactivate/{id}" (panel administratora) dla metody GET. Dezaktywuje trening o numere {id} dla u¿ytkownika o loginie {login}, 
	 * a nastêpnie przekierowywyje do widoku admin/edit/user/{login} (czyli do strony na której administrator widzi dane u¿ytkownika o danym loginie). 
	 * 
	 * @param login nazwa uzytkownika, dla którego aktywujemy trening
	 * @param id id treningu, którego dezaktywujemy dla uzytkownika
	 */
	@RequestMapping(value="admin/edit/user/{login}/training/deactivate/{id}", method=RequestMethod.GET)
	public String deactivateUserTraining(@PathVariable("login") String login, @PathVariable("id") Integer id, Model model)
	{
		/* Pobieramy dane u¿ytkownika na podstawie loginu z adresu*/
		User user = userBo.getUserByLogin(login);

		/* W pêtli przeszukujemy trening o danym numerze id za pomoc¹ iteratora */
		Iterator<?> i = user.getUserTrainings().iterator();
		while(i.hasNext())
		{
			/* Nastêpny trening z listy */
			UserTraining ut = (UserTraining)i.next();
			
			/* Po odnalezieniu deaktywujemy trening */
			if(ut.getPk().getTraining().getId() == id){
				ut.setActive(0);
			}
		}
		
		/* Zapisujemy zmiany */
		userBo.modifyUser(user);
		
		/* Przekierowujemy do strony /admin/edit/user/{login} */
		return "redirect:/admin/edit/user/"+login;
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/login" dla metody GET. Przekierowywuje do widoku login (formularz logowania). 
	 * 
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage(Map<String, Object> model){
		/* Przekierowujemy do widoku login */
		return "login";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/logout" dla metody GET. Przekierowywuje do widoku login (formularz logowania). 
	 * 
	 */
	@RequestMapping({"/logout"})
	public String logout(Map<String, Object> model){
		/* Przekierowujemy do widoku login */
		return "login";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/loginfailed" dla metody GET. Przekierowywuje do widoku login (formularz logowania). 
	 * 
	 */
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String showLoginFailedPage(ModelMap model) {
		/* Dodajemy do modelu pomocnicz¹ informacje o b³êdzie, aby widok wiedzia³ ¿e */
		model.addAttribute("error", "true");
		
		/* Przekierowujemy do widoku login */
		return "login";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/home" (panel uzytkownika) dla metody GET. Przygotowuje dane do przes³ania do widoku home (przygotowuje listê treningów). 
	 * Nastêpnie przekierowywuje do widoku home.
	 * 
	 * @param model zawieraæ bêdzie liste treningów u¿ytkownika która zostanie przekazana do widoku home
	 */
	@RequestMapping(value="/home")
	public String showHomePage(Map<String, Object> model)
	{
		/* Pobieramy dane zalogowanego u¿ytkownika */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User loggedUser = userBo.getUserByLogin(name);
	    
		/* Pobieramy listê treningów zalogowanego u¿ytkownika i dodajemy do modelu, który bedzie przekazany do widoku */
		List<Training> userTrainings = loggedUser.getTrainings();
		model.put("userTrainings", userTrainings);
	
		/* Tworzymy listê wszystkich treningów z wykluczeniem tych na które u¿ytkownik juz jest zapisany */
		/* Najpierw pobieramy wszystkie treningi */
		List<?> trList =  trainingBo.getAllTrainings();
		
		/* Iterator wskazuje na pierwszy trening u¿ytkownika */
		Iterator<?> iterator = userTrainings.iterator();

		/* W pêtli przelatujemy treningi u¿ytkownika */
	    while (iterator.hasNext()) {
	    	
	    	/* t wstazuje na nastêpny trening u¿ytkownika */
	    	Training t = (Training)iterator.next();
	    	
	    	/* Teraz w pêtli przeszukujemy czy taki trening jest na liœci wszystkich treningów */
	    	Iterator<?> i = trList.iterator();
	    	while (i.hasNext()){
	    		Training tr = (Training)i.next();
	    		
	    		/* Jesli znajdziemy trening o tym samym id co trening do którego jest zapisany u¿ytkownik, usuwamy go z listy*/
	    		if(t.getId() == tr.getId())
	    			i.remove();
	    	}
	    }
		
	    /* Listê wszystkich treningów z wykluczeniem tych na które u¿ytkownik juz jest zapisany dodajemy do modelu */
		model.put("trainingsList", trList);
		
		/* Przekierowujemy do widoku home */
		return "home";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/home/training/join/{id}" (panel uzytkownika) dla metody GET. U¿ytkownik zostaje przy³¹czony do treningu o numerze {id} 
	 * Nastêpnie medota przekierowywuje do widoku home.
	 * 
	 * @param id id treningu do którego u¿ytkownik zostaje przypisany
	 */
	@RequestMapping(value="/home/training/join/{id}", method=RequestMethod.GET)
	public String homeTrainingJoin(@PathVariable Integer id){
		
		/* Pobieramy dane zalogowanego u¿ytkownika */
		String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();

	    User loggedUser = userBo.getUserByLogin(userName);
	    
	    /* Pobieramy dane o treningu na podstawie numeru id */
		Training training = trainingBo.getTrainingById(id);

		/* £¹czymy u¿ytkownika z treningiem, ale ustawiamy active na 0 bo administrator musi jeszcze aktywowaæ */
		UserTraining userTraining = new UserTraining();
		userTraining.setActive(0);
		userTraining.setTraining(training);
		userTraining.setUser(loggedUser);
		
		/* Dodajemy po³¹czenie (u¿ytkownik<->trening) do bazy u¿ytkownika */
		loggedUser.getUserTrainings().add(userTraining);		

		/* Zapisujemy zmiany u u¿ytkownika */
		userBo.modifyUser(loggedUser);
		
		/* Przekierowujemy do strony home */
		return "redirect:/home";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/home/training/quit/{id}" (panel uzytkownika) dla metody GET. U¿ytkownik zostaje od³¹czony do treningu o numerze {id} 
	 * Nastêpnie medota przekierowywuje do widoku home.
	 * 
	 * @param id id treningu do którego u¿ytkownik zostaje wykluczony
	 */
	@RequestMapping(value="/home/training/quit/{id}", method=RequestMethod.GET)
	public String homeTrainingQuit(@PathVariable Integer id){

		/* Pobieramy dane zalogowanego u¿ytkownika */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User loggedUser = userBo.getUserByLogin(name);
	    
	    /* Przeszukujemy wszystkie treningi u¿ytkownika za pomoc¹ iteratora */
	    Iterator<?> i = loggedUser.getUserTrainings().iterator();
	    while(i.hasNext()){
	    	UserTraining ut = (UserTraining)i.next();
	    	
	    	/* Jeœli znajdziemy trening o podanym id usuwamy go z listy treningów u¿ytkownika */
	    	if(ut.getPk().getTraining().getId() == id){
	    		userBo.removeUserTraining(loggedUser, ut);
	    	}
	    }
	    /* Zapisujemy zmiany u u¿ytkownika */
	    userBo.modifyUser(loggedUser);
	    
	    /* Przekierowujemy do strony home */
		return "redirect:/home";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/register" dla metody GET. Przekierowywuje do widoku register (formularz rejestracyjny)
	 * 
	 * @param model obiekt, do którego zostaje dodana nowa instancja klasy User (dane u¿ytkownika), który zostanie zarejestrowany 
	 */
	@RequestMapping({"/register"})
	public String showRegisterPage(ModelMap model)
	{
		/* Wylogowujemy zalogowanego u¿ytkownika */
		SecurityContextHolder.getContext().setAuthentication(null);			

		/* Dodajemy nowego u¿ytkownika do modelu, które bedzie przekazany do widoku */
		model.addAttribute(new User());
		
		/* Przekierowujemy do widoku register */
		return "register";
	}
	
	/** 
	 * Metoda mapuj¹ca adres "/register" dla metody POST. Sprawdza poprawnoœæ danych z widoku register (formularza rejestracyjnego)
	 * Jeœli nie wykryje b³êdów dodaje nowego u¿ytkownika do bazy. W razie b³êdów przekierowywuje z powrotem do formularza gdzie zaznaczone bêd¹ b³êdne informacje do poprawienia.
	 * 
	 * @param userForm dane u¿ytkownika z formularza
	 * @param bindingResult przechowuje wynik walidacji (b³êdy z formularza)
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@Valid User userForm, BindingResult bindingResult){
		
		/* Sprawdzamy czy przes³any formularz zawiera³ b³êdy */
		if(bindingResult.hasErrors()){
			/* Jeœli tak to przekierowujemy spowrotem do formularza (do widoku register) */
			return "register";
		}
		
		/* Sprawdzamy czy login nie jest zajêty*/
		if(userBo.isLoginUsed(userForm.login)){
			/* Jeœli tak to dodajemy komunikat o b³êdzie i przekierowujemy spowrotem do formularza (do widoku register) */
			bindingResult.rejectValue("login", "loginUsedErrorMessage", "Login used!");
			return "register";
		}
		
		/* Jeœli nie by³o b³êdów nadajemy nowemu u¿ytkownikowi role USER, ustawiamy enable na 0 bo administrator musi potwierdziæ */
		userForm.setRole(roleBo.getRoleByName("USER"));
		userForm.setEnabled(0);
		
		/* Dodajemy nowego u¿ytkownika do bazy danych */
		userBo.addUser(userForm);
		
		/* Przekierowujemy do widoku user_added */
		return "user_added";
	}
}
