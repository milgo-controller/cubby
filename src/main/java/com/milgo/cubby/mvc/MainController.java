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
 * Klasa kontrolera. Steruje dzia�aniem aplikacji. 
 *
 */

@Controller
public class MainController {
	
	/** 
	 * Zmienna, do kt�rej wstrzykiwana jest instancja klasy implementuj�ca interfejs UserBo (komponent Spring MVC) 
	 */
	@Autowired
	public UserBo userBo;
	
	/** 
	 * Zmienna, do kt�rej wstrzykiwana jest instancja klasy implementuj�ca interfejs RoleBo (komponent Spring MVC) 
	 */
	@Autowired
	public RoleBo roleBo;
	
	/** 
	 * Zmienna, do kt�rej wstrzykiwana jest instancja klasy implementuj�ca interfejs TrainingBo (komponent Spring MVC) 
	 */
	@Autowired
	public TrainingBo trainingBo;
	
	
	/** 
	 * Metoda mapuj�ca adres "/admin" (panel administratora) dla metody GET. Pobiera list� u�ytkownik�w (wszystkich opr�cz administratora) i list� trening�w, 
	 * przekierowywyje do widoku admin.
	 * 
	 * @param model lista u�ytkownik�w w postaci kolekcji typu Map przekazywana do widoku (w tym przypadku widoku admin)
	 */
	@RequestMapping({"/admin"})
	public String showAdminPage(Map<String, Object> model){
		
		/* Pobieramy dane aktualnie zalogowanego administratora */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User loggedUser = userBo.getUserByLogin(name);
		
	    /* Pobieramy liste wszystkich u�ytkownik�w */
	    List<?> userList = userBo.getAllUsers();
	    
	    /* Iterator do listy u�ytkownik�w */
		Iterator<?> iterator = userList.iterator();

		/* W p�tli przechodzimy po kazdym u�ytkownik�w poprzez iterator */
	    while (iterator.hasNext()) {
			User user = (User)iterator.next();
				
			/* Usuwamy z listy uzytkownika kt�ry wy�wietla t� liste */
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
				
			/* Pobieramy nazw� roli u�ytkownika (poniewa� w bazie danych jest ona reprezentowana liczbowo */
			user.setRoleName(user.getRole().getRoleName());
	    }
	    
	    /* Dodajemy liste uzytkownikow do modelu, kt�ry zostanie przes�any do widoku */
		model.put("usersList", userList);
		
		/* Dodajemy liste wszystlkich treningow do modelu, kt�ry zostanie przeslany do widoku */
		List<?> trList =  trainingBo.getAllTrainings();
		model.put("trainingsList", trList);
		
		/* Przekierowijemy do widoku admin */
		return "admin";
	}
	
	
	/** 
	 * Metoda mapuj�ca adres "/admin/remove/user/{login}" (panel administratora) dla metody GET. Usuwa u�ytkownika na podstawie parametru {login} podanego w adresie, 
	 * a nast�pnie przekierowywyje do widoku admin.
	 * 
	 * @param login nazwa u�ytkownika, kt�ry ma zosta� usuni�ty
	 */
	@RequestMapping(value="/admin/remove/user/{login}", method=RequestMethod.GET)
	public String adminRemoveUser(@PathVariable String login){
		
		/* Pobieramy uzytkownika o podanym loginie za pomoc� komponentu userBo */
		User deletedUser = userBo.getUserByLogin(login);
		
		/* Je�li uzytkownik o podanym loginie istnieje usuwamy go z bazy */
		if(deletedUser != null)
			userBo.removeUser(deletedUser);
		
		/* Przekierowujemy spowrotem do strony /admin */
		return "redirect:/admin";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/admin/edit/user/{login}" (panel administratora) dla metody GET. Przygotowywuje dane to wys�ania do formularza w widoku edit_user przeznaczonego do edycji danych uzytkownika, 
	 * a nast�pnie przekierowywyje do widoku edit_user.
	 * 
	 * @param login nazwa u�ytkownika, kt�ry ma zosta� edytowany
	 * @param model dane biznesowe uzytkownika (przekazywane do widoku training_edit)
	 */
	@RequestMapping(value="/admin/edit/user/{login}", method=RequestMethod.GET)
	public String showAdminEditUser(@PathVariable String login, Model model){		
		
		/* Pobieramy u�ytkownika o podanym loginie */
		User user = userBo.getUserByLogin(login);
		
		/* Pobieramy list� wszystkich roli u�ytkownik�w */
		List<?> roles = roleBo.getAllRoles();

		/* Tworzymy list� dla nazw wszystkich roli u�ytkownik�w */
		HashMap<String, String> roleNames = new LinkedHashMap<String, String>();
		
		/* Wpisujemy w list� nazwy wszytkich roli u�ytkownik�w (opr�cz administratora)*/
		for(Object role: roles){
			String roleName = ((Role)role).getRoleName();
			if(roleName.compareTo("ADMIN") == 0)continue;
			roleNames.put(roleName, roleName);
		}
		
		/* zapisujemy u�ytkownikowi nazw� jego roli */
		user.setRoleName(user.getRole().getRoleName());
		
		/* zapisujemy u�ytkownikowi nazwy wszystkich dost�pnych roli (w celu wy�wietlania na formularzu)*/
		user.setRoleNames(roleNames);
		
		/* dodajemy u�ytkownika do modelu, kt�ry zostanie przekazany do widoku */
		model.addAttribute("user", user);
		
		/* pobieramy liste traning�w danego u�ytkownika */
		Set<UserTraining> userTrainings = user.getUserTrainings();
		
		/* dodajemy liste trening�w do modelu, kt�ry zostanie przekazany do widoku */
		model.addAttribute("userTrainings", userTrainings);
		
		/* przekierowujemy do widoku user_edit */
		return "user_edit";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/admin/edit/user/{login}" (panel administratora) dla metody POST. Zapisuje dana edytowane w widoku user_edit, 
	 * a nast�pnie przekierowywyje do widoku admin.
	 * 
	 * @param login nazwa u�ytkownika, kt�ry ma zosta� edytowany
	 * @param userForm (dane biznesowe) z formularza uzytkownika
	 * @param bindingResult przechowuje wynik walidacji (b��dy z formularza)
	 */
	@RequestMapping(value="/admin/edit/user/{login}", method=RequestMethod.POST)
	public ModelAndView adminEditUser(@PathVariable String login, @Valid User userForm, BindingResult bindingResult){
		
		/* Troche inny spos�b komunikacji model/widok tworzymy obiekt klasy ModelAndView i odrazu przekazujemy nazw� widoku docelowego */
		ModelAndView mav = new ModelAndView("user_edit");
		
		/* Sprawdzamy czy przes�any formularz zawiera� b��dy */
		if(bindingResult.hasErrors()){
			/* Je�li tak to dodaj do modelu ten sam formularz i przekieruj z powrotem (do poprawy) */
			mav.addObject("user", userForm);
			return mav;
		}
		
		/* Jesli formularz jest poprawny zmie� decelowy widok na admin */
		mav.setViewName("redirect:/admin");
		
		/* Pobieramy obiekt u�ytkownika, kt�ry b�dzie aktualizowany */
		User updatedUser = userBo.getUserByLogin(login);
		
		/* Zmieniamy niekt�re w�a�ciwo�ci u�ytkownika na podstawie danych z formularza */
		updatedUser.setAddress(userForm.getAddress());
		updatedUser.setEmail(userForm.getEmail());
		updatedUser.setFirstName(userForm.getFirstName());
		updatedUser.setLastName(userForm.getLastName());
		updatedUser.setBirthDate(userForm.getBirthDate());
		
		/* Odblokowanie/zablokowanie u�ytkownika do korzystania z mo�liwo�ci logowania */
		if(userForm.getEnabled() == null)
			updatedUser.setEnabled(0);
		else updatedUser.setEnabled(1);
			
		/* Pobieramy obiekt roli u�ytkowanika na podstawie nazwy */ 
		Role role = roleBo.getRoleByName(userForm.getRoleName());
		
		/* Ustawiamy rol� dla u�ytkownika */
		updatedUser.setRole(role);
		
		/* Ustawiamy has�o potwierdzaj�ce (�eby nie by�o b��du podczas sprawdzanie poprawno�ci has�a) */
		updatedUser.setConfirmPassword(updatedUser.getPassword());
		
		/* Zapisujemy zmodyfikowane dane uzytkownika */
		userBo.modifyUser(updatedUser);
		
		/* Zwracamy obiekt ModelAndView, kt�ry zawiera informacje o widoku, do kt�rego zostaniemy przekierowani i model danych kt�ry zostanie przes�any do tego widoku */
		return mav;
	}
	
	/** 
	 * Metoda mapuj�ca adres "admin/add/training" (panel administratora) dla metody GET. Przygotowuje dane do widoku training_edit, wykorzystywanego w tym przypadku do dodania nowego treningu 
	 * a nast�pnie przekierowywyje do widoku training_edit.
	 * 
	 * @param model  dane biznesowe dla nowego treningu (przekazywane do widoku training_edit).
	 */
	@RequestMapping({"admin/add/training"})
	public String showAddTrainingPage(ModelMap model){
		/* Dodajemy do modelu now� instancj� klasy Training, kt�ra b�dzie przekazana do widoku */
		model.addAttribute(new Training());
		/* Przekierowujemy do widoku training_edit */
		return "training_edit";
	}
	
	/** 
	 * Metoda pomocnicza - sprawdzaj�ca poprawno�� danych dla podanej instancji klasy Training
	 * 
	 * @param training instancja klasy Training, kt�r� sprawdzamy
	 * @param bindingResult przechowuje wynik walidacji (b��dy z formularza)
	 */
	public int validateTraining(Training training, BindingResult bindingResult)
	{
		/* Ustawiamy czy trening jest treningiem online */
		if(training.getOnline() == null)
			training.setOnline(0);
		else training.setOnline(1);
		
		/* Je�li administarot nie wpisa� ceny to cena = 0 */
		if(training.getCost() == null)
			training.setCost(0);		
		
		/* Je�li przes�any formularz zawiera b��dy zako�cz funkcje zwracaj�c 1 (co oznacza b�ad) */
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
	    	
	    	/* Sprawdz czy data jest ustawiona. Je�li nie to zako�cz funkcje i zwr�� 1 (co oznacza b��d) */
	    	if(training.getStartDate() == null){
	    		bindingResult.rejectValue("startDate", "", "Please enter date of training!");
	    		return 1;
	    	}
	    	
	    	/* Sprawdz czy miejsce jest ustawione. Je�li nie to zako�cz funkcje i zwr�� 1 (co oznacza b��d) */
			if(training.getPlace().isEmpty()){
				bindingResult.rejectValue("place", "", "Please enter place of training!");
				return 1;
	    	}
	    }
	    else{ /* Je�li trening jest online sprawd� czy URL nie jest pusty */
	    	if(training.getUrl().isEmpty()){
				bindingResult.rejectValue("url", "", "Please enter url!");
				return 1;
	    	}
	    }
	    
	    /* Jesli nie by�o b��d�w to zwr�� 0 (co oznacza ze wszystko przebirg�o poprawnie) */
		return 0;
	}
	
	/** 
	 * Metoda mapuj�ca adres "admin/add/training" (panel administratora) dla metody POST. Sprawdza poprawno�� danych przekazanych przez formularz z widoku training_edit,
	 * a nast�pnie je�li nie wykry�a b��d�w dodaje nowy trening do bazy danych. Na konicu przekierowywuje do widoku admin.
	 * 
	 * @param training instancja klasy Training, kt�r� dodajemy
	 * @param bindingResult przechowuje wynik walidacji (b��dy z formularza)
	 */
	@RequestMapping(value="admin/add/training", method=RequestMethod.POST)
	public String adminEditTraining(@Valid Training training, BindingResult bindingResult){
		
		/* Sprawdzamy czy dane w obiekcie training s� poprawne i czy bindingResult nie zawiera informacji o b�edach w formularzu */
		int res = validateTraining(training, bindingResult);
		
		/* Je�eli s� b��dy to wracamy do widoku training_edit (�eby u�ytkownik poprawi�) */
		if(res > 0){
			return "training_edit";
		}
		else{ /*Je�li nie ma b�ed�w to dodajemy trening do bazy */
			trainingBo.addTraining(training);
		}
		
		/* Przekierowujemy do strony /admin */
		return "redirect:/admin";
	}
	
	/** 
	 * Metoda mapuj�ca adres "admin/training/edit/{id}" (panel administratora) dla metody GET. Przygotowuje dane do przes�ania do formularza w widoku training_edit (w celu edycji tych danych),
	 * a nast�pnie przekierowywuje do widoku training_edit (widok z formularzem edycyjnym).
	 * 
	 * @param id numer id treningu
	 * @param model dane biznesowe treningu (przekazywane do widoku training_edit)
	 */
	@RequestMapping(value="admin/training/edit/{id}", method=RequestMethod.GET)
	public String adminEditTraining(@PathVariable Integer id, Model model)
	{
		/* Dodaj wyszukany trening do modelu, kt�ry bedzie przekazany do widoku w celu edycji */
		model.addAttribute("training", trainingBo.getTrainingById(id));
		
		/* Przejd� do widoku training_edit */
		return "training_edit";
	}
	
	/** 
	 * Metoda mapuj�ca adres "admin/training/edit/{id}" (panel administratora) dla metody POST. Sprawdza poprawno�� danych przekazanych przez formularz z widoku training_edit,
	 *  je�li nie wykry�a b��d�w zapisuje zmiany w trening (dokonane w widoku training_edit) do bazy danych i przekierowywuje do widoku trainin-edit. W razie b��d�w przekierowywuje z powrotem do widoku training_edit, gdzie na formularzu zostan� zaznaczone b��dne informacje do poprawienia.
	 * 
	 * @param id numer id treningu
	 * @param training instancja klasy training, kt�r� edytujemy
	 * @param bindingResult przechowuje wynik walidacji (b��dy z formularza)
	 */
	@RequestMapping(value="admin/training/edit/{id}", method=RequestMethod.POST)
	public String adminModifyTraining(@PathVariable Integer id, @Valid Training training, BindingResult bindingResult)
	{
		/* Sprawdzamy czy dane w obiekcie training s� poprawne i czy bindingResult nie zawiera informacji o b�edach w formularzu */
		int res = validateTraining(training, bindingResult);
		
		/* Je�eli s� b��dy to wracamy do widoku training_edit (�eby u�ytkownik poprawi�) */
		if(res > 0){
			return "training_edit";
		}
		else{ /*Je�li nie ma b�ed�w to dodajemy trening do bazy */
			trainingBo.modifyTraining(training);
		}
		/* Przekierowujemy do widoku training_edit */
		return "training_edit";
	}
	
	/** 
	 * Metoda mapuj�ca adres "admin/training/remove/{id}" (panel administratora) dla metody GET. Usuwa training na podstawie parametru {id} podanego w adresie, 
	 * a nast�pnie przekierowywyje do widoku admin.
	 * 
	 * @param id id treningu, kt�ry ma zosta� usuni�ty
	 */
	@RequestMapping(value="admin/training/remove/{id}", method=RequestMethod.GET)
	public String adminRemoveTraining(@PathVariable Integer id){
		
		/* Usuwamy z bazy trening o podanym id */
		trainingBo.removeTraining(id);
		
		/* Przekierowujemy do strony /admin */
		return "redirect:/admin";
	}
	
	/** 
	 * Metoda mapuj�ca adres "admin/edit/user/{login}/training/activate/{id}" (panel administratora) dla metody GET. Aktywuje trening o numere {id} dla u�ytkownika o loginie {login}, 
	 * a nast�pnie przekierowywyje do widoku admin/edit/user/{login} (czyli do strony na kt�rej administrator widzi dane u�ytkownika o danym loginie). 
	 * 
	 * @param login nazwa uzytkownika, dla kt�rego aktywujemy trening
	 * @param id id treningu, kt�rego aktywujemy dla uzytkownika
	 */
	@RequestMapping(value="admin/edit/user/{login}/training/activate/{id}", method=RequestMethod.GET)
	public String activateUserTraining(@PathVariable("login") String login, @PathVariable("id") Integer id)
	{
		/* Pobieramy dane u�ytkownika na podstawie loginu z adresu*/
		User user = userBo.getUserByLogin(login);

		/* W p�tli przeszukujemy trening o danym numerze id za pomoc� iteratora */
		Iterator<?> i = user.getUserTrainings().iterator();
		while(i.hasNext())
		{
			/* Nast�pny trening z listy */
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
	 * Metoda mapuj�ca adres "admin/edit/user/{login}/training/deactivate/{id}" (panel administratora) dla metody GET. Dezaktywuje trening o numere {id} dla u�ytkownika o loginie {login}, 
	 * a nast�pnie przekierowywyje do widoku admin/edit/user/{login} (czyli do strony na kt�rej administrator widzi dane u�ytkownika o danym loginie). 
	 * 
	 * @param login nazwa uzytkownika, dla kt�rego aktywujemy trening
	 * @param id id treningu, kt�rego dezaktywujemy dla uzytkownika
	 */
	@RequestMapping(value="admin/edit/user/{login}/training/deactivate/{id}", method=RequestMethod.GET)
	public String deactivateUserTraining(@PathVariable("login") String login, @PathVariable("id") Integer id, Model model)
	{
		/* Pobieramy dane u�ytkownika na podstawie loginu z adresu*/
		User user = userBo.getUserByLogin(login);

		/* W p�tli przeszukujemy trening o danym numerze id za pomoc� iteratora */
		Iterator<?> i = user.getUserTrainings().iterator();
		while(i.hasNext())
		{
			/* Nast�pny trening z listy */
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
	 * Metoda mapuj�ca adres "/login" dla metody GET. Przekierowywuje do widoku login (formularz logowania). 
	 * 
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage(Map<String, Object> model){
		/* Przekierowujemy do widoku login */
		return "login";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/logout" dla metody GET. Przekierowywuje do widoku login (formularz logowania). 
	 * 
	 */
	@RequestMapping({"/logout"})
	public String logout(Map<String, Object> model){
		/* Przekierowujemy do widoku login */
		return "login";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/loginfailed" dla metody GET. Przekierowywuje do widoku login (formularz logowania). 
	 * 
	 */
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String showLoginFailedPage(ModelMap model) {
		/* Dodajemy do modelu pomocnicz� informacje o b��dzie, aby widok wiedzia� �e */
		model.addAttribute("error", "true");
		
		/* Przekierowujemy do widoku login */
		return "login";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/home" (panel uzytkownika) dla metody GET. Przygotowuje dane do przes�ania do widoku home (przygotowuje list� trening�w). 
	 * Nast�pnie przekierowywuje do widoku home.
	 * 
	 * @param model zawiera� b�dzie liste trening�w u�ytkownika kt�ra zostanie przekazana do widoku home
	 */
	@RequestMapping(value="/home")
	public String showHomePage(Map<String, Object> model)
	{
		/* Pobieramy dane zalogowanego u�ytkownika */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User loggedUser = userBo.getUserByLogin(name);
	    
		/* Pobieramy list� trening�w zalogowanego u�ytkownika i dodajemy do modelu, kt�ry bedzie przekazany do widoku */
		List<Training> userTrainings = loggedUser.getTrainings();
		model.put("userTrainings", userTrainings);
	
		/* Tworzymy list� wszystkich trening�w z wykluczeniem tych na kt�re u�ytkownik juz jest zapisany */
		/* Najpierw pobieramy wszystkie treningi */
		List<?> trList =  trainingBo.getAllTrainings();
		
		/* Iterator wskazuje na pierwszy trening u�ytkownika */
		Iterator<?> iterator = userTrainings.iterator();

		/* W p�tli przelatujemy treningi u�ytkownika */
	    while (iterator.hasNext()) {
	    	
	    	/* t wstazuje na nast�pny trening u�ytkownika */
	    	Training t = (Training)iterator.next();
	    	
	    	/* Teraz w p�tli przeszukujemy czy taki trening jest na li�ci wszystkich trening�w */
	    	Iterator<?> i = trList.iterator();
	    	while (i.hasNext()){
	    		Training tr = (Training)i.next();
	    		
	    		/* Jesli znajdziemy trening o tym samym id co trening do kt�rego jest zapisany u�ytkownik, usuwamy go z listy*/
	    		if(t.getId() == tr.getId())
	    			i.remove();
	    	}
	    }
		
	    /* List� wszystkich trening�w z wykluczeniem tych na kt�re u�ytkownik juz jest zapisany dodajemy do modelu */
		model.put("trainingsList", trList);
		
		/* Przekierowujemy do widoku home */
		return "home";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/home/training/join/{id}" (panel uzytkownika) dla metody GET. U�ytkownik zostaje przy��czony do treningu o numerze {id} 
	 * Nast�pnie medota przekierowywuje do widoku home.
	 * 
	 * @param id id treningu do kt�rego u�ytkownik zostaje przypisany
	 */
	@RequestMapping(value="/home/training/join/{id}", method=RequestMethod.GET)
	public String homeTrainingJoin(@PathVariable Integer id){
		
		/* Pobieramy dane zalogowanego u�ytkownika */
		String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();

	    User loggedUser = userBo.getUserByLogin(userName);
	    
	    /* Pobieramy dane o treningu na podstawie numeru id */
		Training training = trainingBo.getTrainingById(id);

		/* ��czymy u�ytkownika z treningiem, ale ustawiamy active na 0 bo administrator musi jeszcze aktywowa� */
		UserTraining userTraining = new UserTraining();
		userTraining.setActive(0);
		userTraining.setTraining(training);
		userTraining.setUser(loggedUser);
		
		/* Dodajemy po��czenie (u�ytkownik<->trening) do bazy u�ytkownika */
		loggedUser.getUserTrainings().add(userTraining);		

		/* Zapisujemy zmiany u u�ytkownika */
		userBo.modifyUser(loggedUser);
		
		/* Przekierowujemy do strony home */
		return "redirect:/home";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/home/training/quit/{id}" (panel uzytkownika) dla metody GET. U�ytkownik zostaje od��czony do treningu o numerze {id} 
	 * Nast�pnie medota przekierowywuje do widoku home.
	 * 
	 * @param id id treningu do kt�rego u�ytkownik zostaje wykluczony
	 */
	@RequestMapping(value="/home/training/quit/{id}", method=RequestMethod.GET)
	public String homeTrainingQuit(@PathVariable Integer id){

		/* Pobieramy dane zalogowanego u�ytkownika */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User loggedUser = userBo.getUserByLogin(name);
	    
	    /* Przeszukujemy wszystkie treningi u�ytkownika za pomoc� iteratora */
	    Iterator<?> i = loggedUser.getUserTrainings().iterator();
	    while(i.hasNext()){
	    	UserTraining ut = (UserTraining)i.next();
	    	
	    	/* Je�li znajdziemy trening o podanym id usuwamy go z listy trening�w u�ytkownika */
	    	if(ut.getPk().getTraining().getId() == id){
	    		userBo.removeUserTraining(loggedUser, ut);
	    	}
	    }
	    /* Zapisujemy zmiany u u�ytkownika */
	    userBo.modifyUser(loggedUser);
	    
	    /* Przekierowujemy do strony home */
		return "redirect:/home";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/register" dla metody GET. Przekierowywuje do widoku register (formularz rejestracyjny)
	 * 
	 * @param model obiekt, do kt�rego zostaje dodana nowa instancja klasy User (dane u�ytkownika), kt�ry zostanie zarejestrowany 
	 */
	@RequestMapping({"/register"})
	public String showRegisterPage(ModelMap model)
	{
		/* Wylogowujemy zalogowanego u�ytkownika */
		SecurityContextHolder.getContext().setAuthentication(null);			

		/* Dodajemy nowego u�ytkownika do modelu, kt�re bedzie przekazany do widoku */
		model.addAttribute(new User());
		
		/* Przekierowujemy do widoku register */
		return "register";
	}
	
	/** 
	 * Metoda mapuj�ca adres "/register" dla metody POST. Sprawdza poprawno�� danych z widoku register (formularza rejestracyjnego)
	 * Je�li nie wykryje b��d�w dodaje nowego u�ytkownika do bazy. W razie b��d�w przekierowywuje z powrotem do formularza gdzie zaznaczone b�d� b��dne informacje do poprawienia.
	 * 
	 * @param userForm dane u�ytkownika z formularza
	 * @param bindingResult przechowuje wynik walidacji (b��dy z formularza)
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@Valid User userForm, BindingResult bindingResult){
		
		/* Sprawdzamy czy przes�any formularz zawiera� b��dy */
		if(bindingResult.hasErrors()){
			/* Je�li tak to przekierowujemy spowrotem do formularza (do widoku register) */
			return "register";
		}
		
		/* Sprawdzamy czy login nie jest zaj�ty*/
		if(userBo.isLoginUsed(userForm.login)){
			/* Je�li tak to dodajemy komunikat o b��dzie i przekierowujemy spowrotem do formularza (do widoku register) */
			bindingResult.rejectValue("login", "loginUsedErrorMessage", "Login used!");
			return "register";
		}
		
		/* Je�li nie by�o b��d�w nadajemy nowemu u�ytkownikowi role USER, ustawiamy enable na 0 bo administrator musi potwierdzi� */
		userForm.setRole(roleBo.getRoleByName("USER"));
		userForm.setEnabled(0);
		
		/* Dodajemy nowego u�ytkownika do bazy danych */
		userBo.addUser(userForm);
		
		/* Przekierowujemy do widoku user_added */
		return "user_added";
	}
}
