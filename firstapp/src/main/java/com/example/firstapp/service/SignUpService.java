package com.example.firstapp.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.firstapp.Repository.SignupRepository;
import com.example.firstapp.error.EmailExistsException;
import com.example.firstapp.modell.Login;
import com.example.firstapp.modell.SignUp;


@Service
public class SignUpService {
    
    @Autowired
    private SignupRepository signupRepository;
    @Autowired
	private HttpServletRequest request;
    private HttpSession session;

    public ResponseEntity<?> userRegistration(SignUp entity) throws EmailExistsException {
        if(entity.getEmail().isEmpty()){
            throw new EmailExistsException("Email is required...");
        }
        else if(signupRepository.existsById(entity.getEmail())){
            throw new EmailExistsException("Email is already exists...");
    }else{
        String email= signupRepository.save(entity).getEmail();
         return new ResponseEntity<>(email,HttpStatus.OK);
    }
     
    }



    public ResponseEntity<?> getAllSignupDetails(){

       
		if(signupRepository.count()==0){
             return new ResponseEntity<>("No data available",HttpStatus.NOT_FOUND);
		}else{
            return  new ResponseEntity<>(signupRepository.findAll(), HttpStatus.OK);
    }
}



    public void deleteAllSignUPDetails() {
        signupRepository.deleteAll();;
    }



	public ResponseEntity<String> SigninValidation( Login loginentity) {
		Optional<SignUp> entity = signupRepository.findById(loginentity.getEmail());
        if(entity.isPresent()){
		if ( entity.get().getPassword().equals(loginentity.getPassword())) {
		    session = request.getSession(true);
			session.setAttribute("name", entity.get().getName());
			session.setAttribute("email", entity.get().getEmail());
			session.setMaxInactiveInterval(10*10);
			System.out.println(session);
            System.out.println(session.getAttribute("email"));
			return new ResponseEntity<>(entity.get().getEmail(),HttpStatus.OK);
		}
    }
		return new ResponseEntity<>("invalid credential",HttpStatus.BAD_REQUEST);
	}

    public HttpSession  getSession(){
        HttpSession sessiion=  request.getSession(false);
        System.out.println(sessiion);
        return sessiion;
    }
}

