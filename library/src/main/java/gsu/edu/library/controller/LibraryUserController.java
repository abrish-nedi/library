package gsu.edu.library.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gsu.edu.library.entity.DropDownOption;
import gsu.edu.library.entity.LibraryUser;
import gsu.edu.library.service.LibraryUserService;

@Controller
public class LibraryUserController {
	
	private LibraryUserService userService;
	@Autowired
	public LibraryUserController(LibraryUserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String home() {			
		return "home";
	}
	//////////////////////////////////////////////start signup////////////////////
	@GetMapping("/signup")
	public String showSignupForm(Model model) {
		
		//create model attribute to bind form data
		LibraryUser user = new LibraryUser();
		
		model.addAttribute("libraryuser", user);
		
		//create user type
		List<DropDownOption> typeList = new ArrayList<>();
		typeList.add(new DropDownOption("Admin","Admin"));
		typeList.add(new DropDownOption("Customer","Customer"));

		model.addAttribute("options", typeList);
		model.addAttribute("selectedOption", new String());
		
		return "user/user-form";
	}
	
	@PostMapping("/signup")
	public String saveUser(@RequestParam("selectedOption") String selectedOption, @ModelAttribute("libraryuser") LibraryUser user,Model model) {
		LibraryUser temp=userService.findByEmail(user.getEmail());
		if(null!=temp) {
			model.addAttribute("errormsg", true);
			List<DropDownOption> typeList = new ArrayList<>();
			typeList.add(new DropDownOption("Admin","Admin"));
			typeList.add(new DropDownOption("Customer","Customer"));

			model.addAttribute("options", typeList);
			model.addAttribute("selectedOption", new String());
			model.addAttribute("libraryuser", user);
			return "user/user-form";
		}
		model.addAttribute("errormsg", false);
		user.setUserType(selectedOption);
		//save library user
		userService.signup(user);
		//redirect to home screen
		return "redirect:/";
	}
//////////////////////////////////////////////end signup////////////////////
	
//////////////////////////////////////////////start signin////////////////////
	@GetMapping("/signin")
	public String showSigninForm(Model model) {
		//DO something
		return "user/sign_in";
	}
	
	@PostMapping("/signin")
	public String signinForm(Model model) {
		//DO something
		return "";
	}
//////////////////////////////////////////////end signin////////////////////
}