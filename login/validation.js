
const username = document.getElementById('username');
const phone = document.getElementById('phone');
const email = document.getElementById('email');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');



function validateForm() {
	const usernameValue = username.value.trim();
	const phoneValue = phone.value.trim();
	const emailValue = email.value.trim();
	const passwordValue = password.value.trim();
	const password2Value = password2.value.trim();
	console.log(usernameValue);
	var returnval = true;

	if (usernameValue === '') {
		setError(username, 'Username is required');
		returnval = false;
	} else if (!isValidName(usernameValue)) {
		setError(username, 'Username is not valid');
		returnval = false;
	} else {
		setSuccess(username);
		returnval = true;
	}
	if (phoneValue === '') {
		setError(phone, 'phone no is required');
		returnval = false;
	} else if (!isValidPhone(phoneValue)) {
		setError(phone, 'Provide a valid phone no');
		returnval = false;
	} else {
		setSuccess(phone);
		returnval = true;
	}

	if (emailValue === '') {
		setError(email, 'Email is required');
		returnval = false;
	} else if (!isValidEmail(emailValue)) {
		setError(email, 'Provide a valid email address');
		returnval = false;
	} else {
		setSuccess(email);
		returnval = true;
	}


	if (passwordValue === '') {
		setError(password, 'Password is required');
		returnval = false;
	} else if (passwordValue.length < 4) {
		setError(password, 'Password must be at least 4 character.')
		returnval = false;
	} else {
		setSuccess(password);
		returnval = true;
	}

	if (password2Value === '') {
		setError(password2, 'Please confirm your password');
		returnval = false;
	} else if (password2Value.length < 4
		|| password2Value !== passwordValue) {
		setError(password2, "Passwords doesn't match");
		returnval = false;
	} else {
		setSuccess(password2);
		if (usernameValue === '' ||  emailValue === '' || phoneValue === '') {
			returnval = false;
		} else {
			returnval = true;
		}
	}
	if(returnval)
	saveuser();
	return returnval;
};

const setError = (element, message) => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector('.error');

	errorDisplay.innerText = message;
	inputControl.classList.add('error');
	inputControl.classList.remove('success')
}

const setSuccess = element => {
	const inputControl = element.parentElement;
	const errorDisplay = inputControl.querySelector('.error');

	errorDisplay.innerText = '';
	inputControl.classList.add('success');
	inputControl.classList.remove('error');
};

const isValidName = name => {
	const reg = /^[a-zA-Z\s]+$/;
	return reg.test(name);
}
const isValidEmail = email => {
	const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(email).toLowerCase());
}
const isValidPhone = phone => {
	const rex = /^[6-9]\d{9}$/gi;
	return rex.test(phone);
}







const saveuser = async () => {
	

	const usernam = document.getElementById('username').value;
	const phon = document.getElementById('phone').value;
	const emal = document.getElementById('email').value;
	const paswrd = document.getElementById('password').value;
		console.log(usernam);
		let response= await fetch('http://localhost:8085/register',{
			method:'POST',
			headers:{
				'Accept':'application/json',
				'content-Type':'application/json'
			},
			body:JSON.stringify({
				name: usernam,
				phone: phon,
				email: emal,
				password: paswrd
			})
		})
		console.log('response.status: ', response.status); 
		console.log(response);
		if(response.status==200){
			swal({
				title: "Good job!",
				text: "register sucessfully redirecting to login page....!",
				icon: "success",
				button: "ok",
			  }).then(function() {
				window.location = "/index.html";
			});
		}else{
			swal({
				icon:"warning",
				title: {email},
				text: "email already exists....!",
				dangerMode:true,
				button: "ok",
			  });
		}
	
	  }


	  
	  
const loginuser = async () => {
	

	const paswrd = document.getElementById('passwordd').value;
	const emal = document.getElementById('emaill').value;
		console.log(emal);
		let response= await fetch('http://localhost:8085/login',{
			method:'POST',
			headers:{
				'Accept':'application/json',
				'content-Type':'application/json'
			},
			body:JSON.stringify({
				email: emal,
				password: paswrd
			})
		})
		console.log('response.status: ', response.status); // 👉️ 200
		console.log(response);
		if(response.status==200){
			console.log(emal);
		   sessionStorage.setItem('email',emal);
		   
		   document.cookie=`email=${emal}; expires=Wed, 05 Aug 2023 23:00:00 UTC;path=`;
	
			swal({
				title: "Good job!",
				text: "login sucessfully ....!",
				icon: "success",
				button: "ok",
			  }).then(function() {
			
				window.location = "/home.html";
			});
		}else{
			swal({
				icon:"warning",
				title: {email},
				text: "Invalid credential....!",
				dangerMode:true,
				button: "ok",
			  });
		}
	
	  }

	  