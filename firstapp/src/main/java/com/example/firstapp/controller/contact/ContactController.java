package com.example.firstapp.controller.contact;




import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstapp.Repository.ContactRepository;
import com.example.firstapp.Repository.EmailRepository;
import com.example.firstapp.Repository.PhoneRepository;
import com.example.firstapp.error.ContactNotFoundException;
import com.example.firstapp.error.EmployeeInsertResponse;
import com.example.firstapp.modell.Contact;
import com.example.firstapp.modell.Email;
import com.example.firstapp.modell.Phone;
import com.example.firstapp.service.ContactService;
import com.example.firstapp.service.SignUpService;

@RestController
public class ContactController {

	@Autowired
	private ContactService eService;

	@Autowired
	private SignUpService  sservice;
	private PhoneRepository phoneRepository;
    private EmailRepository emailRepository;
    private ContactRepository contactRepository;

	public ContactController(PhoneRepository phoneRepository, EmailRepository emailRepository,
			ContactRepository contactRepository) {
		this.phoneRepository = phoneRepository;
		this.emailRepository = emailRepository;
		this.contactRepository = contactRepository;
	}


	@CrossOrigin
	@PostMapping("/addContact")
	public ResponseEntity<Contact> userRegistration(@RequestBody Contact con) {
		Long id=null;
	  return eService.userRegistration(con,id);
	
	}

	private List<Email> getEmails(Long id){
        List<Email> email=  emailRepository.findByContactid(id);
        if(!email.isEmpty()){
            return email;
        }else{
            System.out.println("record not found");
            return null;
        }
    }

    private List<Phone> getPhones(Long id){
        List<Phone> phone= phoneRepository.findByContactid(id);
        if(!phone.isEmpty()){
            return phone;
        }else{
            return null;
        }
    }
	@CrossOrigin
    @GetMapping("/getcontact/{id}")
    public ResponseEntity<Contact> customcontact(@PathVariable("id") Long id){
      Optional<Contact> getContactbyid=  contactRepository.findById(id);
      if(getContactbyid.isPresent()){
        Contact cont=  getContactbyid.get();
        List<Phone> phone =getPhones(id);
        List<Email> email =getEmails(id);
        cont.setPhone(phone);
        cont.setEmails(email);
        return new ResponseEntity<>(cont,HttpStatus.OK);
      }else{
        System.out.println("id not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public void deletebycontactid(@PathVariable("id") Long id){
		System.err.println("rjgfnjtng\n\n");
       eService.deleteByContactid(id);
	}

	

	
	// @CrossOrigin
	// @GetMapping("/search/{phone}")
	// public ResponseEntity<?>  getUserDetails(@PathVariable("phone")  String phone) {
	//    return eService.getUserDetails(phone);
	// }
	

	@CrossOrigin
	@GetMapping("/find/{id}")
	public Contact findbyidC(@PathVariable("id") Long id) throws ContactNotFoundException{
         return eService.findbyidC(id);
	}

	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<Contact> updateByIdContact(@PathVariable("id") Long id,@RequestBody Contact contact) throws ContactNotFoundException{
		System.out.println(contact);
         return eService.updateByIdContact(id,contact);
	}

	// @CrossOrigin
	// @DeleteMapping("/delete/{id}")
	// public ResponseEntity<?> deleteContact(@PathVariable("id") Long id){
	// 	eService.deleteContact(id);
    // return new ResponseEntity<>(HttpStatus.OK);
	// }

	@CrossOrigin
	@GetMapping("/allcontact/{useremail}")
	public List<Contact>  getAllUserDetails(@PathVariable("useremail") String useremail) {
		return  eService.getAllUserDetails(useremail);
	
	}


	// @CrossOrigin
	// @GetMapping("/allcontact")
	// public Iterable<Contact>  getAllUserDetails() {
	// 	return  eService.getAllUserDetails();
	
	// }

    // @GetMapping("/deleteAll")
    // public void deleteAllcontact(){
    //         eService.deleteAllcontact();
    // }


	
    public HttpSession getsession(){
         HttpSession session=   sservice.getSession();
		 System.err.println(session);
		 return session;
    }

	@GetMapping("/logout")
	public void logout(){
		HttpSession session = getsession();
		
		System.out.println("hghfffffffffffffffffff");
		if(session!=null){
		session.invalidate();
		}
		System.out.println(session);
	}
}
