package com.example.firstapp.Repository;

import java.util.List;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.example.firstapp.modell.Phone;




public interface PhoneRepository extends DatastoreRepository<Phone,Long> {

    
    List<Phone> findByContactid(Long id);

    

    void deleteByContactid(Long id);



   
    
}
