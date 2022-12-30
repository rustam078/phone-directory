package com.example.firstapp.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.firstapp.Repository.ContactRepository;
import com.example.firstapp.Repository.EmailRepository;
import com.example.firstapp.Repository.PhoneRepository;
import com.example.firstapp.error.ContactNotFoundException;
import com.example.firstapp.modell.Contact;
import com.example.firstapp.modell.Email;
import com.example.firstapp.modell.Phone;


@Service
public class ContactService {
    

    private ContactRepository contactRepository;
    private PhoneRepository phoneRepository;
    private EmailRepository emailRepository;
   

	public ContactService(PhoneRepository phoneRepository, EmailRepository emailRepository,
			ContactRepository contactRepository) {
		this.phoneRepository = phoneRepository;
		this.emailRepository = emailRepository;
		this.contactRepository = contactRepository;
	}
   

    public void saveContact(Contact entity) {
        contactRepository.save(entity);
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
    public List<Contact> getAllUserDetails(String useremail) {
        List<Contact> contact=  contactRepository.findByUseremail(useremail);
        List<Contact> al=new ArrayList<>();
        if(!contact.isEmpty()){
            Iterator<Contact> itr=contact.iterator();
            while(itr.hasNext()){
             Long id= itr.next().getContactid();
              Contact cont=  contactRepository.findById(id).get();
            List<Phone> phone =getPhones(id);
            List<Email> email =getEmails(id);
            cont.setPhone(phone);
            cont.setEmails(email);
            al.add(cont);
            }
            return al;
          }else{
            System.out.println("id not found");
            return null;
          }
    }


    // public void deleteAllcontact() {
    //     contactRepository.deleteAll();
    // }



    // public void deleteContact(Long id) {
    //      contactRepository.deleteById(id);
    // }



    public ResponseEntity<Contact> updateByIdContact(Long id,Contact contact) throws ContactNotFoundException,NoSuchElementException {
        Optional<Contact> contat= contactRepository.findById(id);
      
        if(!contat.isPresent()){
            throw new ContactNotFoundException("contact not found..");
        }
           
           deleteByContactid(id);
           ResponseEntity<Contact> contt=   userRegistration(contact,id);
      return contt;
 
     }


    public Contact findbyidC(Long id) throws ContactNotFoundException {
        Optional<Contact> contactbyid= contactRepository.findById(id);
        if(!contactbyid.isPresent()){
            throw new ContactNotFoundException("contact is not available");
        }else{
                  Contact cont=  contactbyid.get();
                List<Phone> phone =getPhones(id);
                List<Email> email =getEmails(id);
                cont.setPhone(phone);
                cont.setEmails(email);
                return cont;
                }
        }
      
    


    // public Iterable<Contact> getAllUserDetails() {
    //     return contactRepository.findAll();
    // }


    // public ResponseEntity<?> getUserDetails(String phone) {
    //     String s[]=phone.split(" ");
    //     Long phon= Long.parseLong(s[0]);
    //     String email=s[1];
    //     List<Contact> contacts=  contactRepository.findByPhoneAndUseremail(phon,email);
    //     if(contacts.size()>0){
    //         return new ResponseEntity<>(contacts, HttpStatus.OK);
    //     }
    //     else{
    //         return new ResponseEntity<>("No Record found",HttpStatus.NOT_FOUND);
    //     }
    // }


    public ResponseEntity<Contact> userRegistration(Contact con,Long idd) {
        System.out.println(con);
        if(idd!=null){
            con.setContactid(idd);
        }
		Long id=  contactRepository.save(con).getContactid();
		System.out.println(id);
		List<Phone> phone = con.getPhone();
        System.out.println(phone);
        if(phone!=null){
		phone.stream().forEach(emp->{
			 emp.setContactid(id);
		});
        phoneRepository.saveAll(phone);
    }
	
	
  
		List<Email> email = con.getEmails();
        if(email!=null){
		email.stream().forEach(emp->{
			 emp.setContactid(id);
		});
        emailRepository.saveAll(email);
    }
	
	
  
		   return new ResponseEntity<>(con,HttpStatus.OK);
    }


    public void deleteByContactid(Long id) {
        System.out.println("jkbjhbfhvbfvf\n\n\n\n\n");
        phoneRepository.deleteByContactid(id);
        emailRepository.deleteByContactid(id);
        contactRepository.deleteById(id);
    }
}
