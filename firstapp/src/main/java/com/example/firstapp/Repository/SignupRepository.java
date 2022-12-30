package com.example.firstapp.Repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import com.example.firstapp.modell.SignUp;


public interface SignupRepository extends DatastoreRepository<SignUp,String>{

  
    
}
