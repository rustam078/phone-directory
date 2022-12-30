package com.example.firstapp.Repository;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import com.example.firstapp.modell.Contact;


@Repository
public interface ContactRepository extends DatastoreRepository<Contact,Long>{

  
    public List<Contact>  findByUseremail(String useremail);

    

    public List<Contact> findByPhoneAndUseremail(Long phone,String email);
}
