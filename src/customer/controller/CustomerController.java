package customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import customer.entity.Customer;
import customer.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	private String listCustomers(Model model) {
		
		List<Customer> customers = customerService.getCustomers();
		
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/addCustomer")
	private String showAddForm(Model model) {
		
		var customer = new Customer();
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	private String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/updateCustomer")
	private String updateCustomer(@RequestParam("customerId") int id,Model model) {
		
		var customer = customerService.getCustomer(id);
		
		model.addAttribute("updateCustomer", customer);
		
		return "customer-update";
	}
	
	@GetMapping("/deleteCustomer")
	private String deleteCustomer(@RequestParam("customerId") int id,Model model) {
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
}








