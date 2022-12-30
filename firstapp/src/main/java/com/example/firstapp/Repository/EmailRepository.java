package com.example.firstapp.Repository;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.example.firstapp.modell.Email;



public interface EmailRepository extends DatastoreRepository<Email,Long> {
;

    List<Email> findByContactid(Long id);



    void deleteByContactid(Long id);
    
}
