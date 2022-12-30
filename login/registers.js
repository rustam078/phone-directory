
const save = document.querySelector('#save');
const search = document.querySelector('#search');
const logoutt = document.querySelector('#logout');
const updatecontact = document.querySelector('#updatecontact');
const dataa=document.cookie.split("=");
const usremail=dataa[1];

const updateData= async()=>{

	let phonevalue=[];
	let emailvalue=[];
                     
	const uid=document.getElementById('uid').value;
	const usernam = document.getElementById('usernames').value;
	const phon = document.getElementsByName('phons');
	const emails = document.getElementsByName('emals');
	const categry = document.getElementById('categorys').value;
	const addres = document.getElementById('addresss').value;
	console.log(uid);
	console.log(usernam);
	console.log(phonevalue);
	console.log(emailvalue);
	console.log(addres);
	
	let abc=[];	
	for (i=0;i<phon.length;i++){  
		abc.push({"phonenumber":phon[i].value});
		} 
		console.log(abc);
		let cde=[];
		for (i=0;i<emails.length;i++){  
			cde.push({"email":emails[i].value});
			} 
     console.log(cde);
      
			
	// for (i=0;i<phon.length;i++){  
	// 	phonevalue[i]=phon[i].value;
	// 	} 
		
	// 	for (i=0;i<emails.length;i++){  
	// 		emailvalue[i]=emails[i].value;
	// 		} 
     
			await fetch(`http://localhost:8085/update/${uid}`,{
			method:'PUT',
			headers:{
				'Accept':'application/json',
				'content-Type':'application/json'
			},
			body:JSON.stringify({
				id:uid,
				name: usernam,
				phone:abc,
				emails:cde,
				category:categry,
				address: addres,
				useremail:usremail
			})
		})
	  document.getElementById('validation').reset();
	  $('#exampleee').modal('toggle');
	  swal({
		  title: "Good job!",
		  text: "Contact added Successfully.........!",
		  icon: "success",
		  button: "ok",
		}).then(function() {
		  window.location = "./home.html";
	  });

}
const saveuserdata = async () => {

	let phonevalue=[];
	let emailvalue=[];
                     

	const usernam = document.getElementById('username').value;
	const phon = document.getElementsByName('phon');
	const emails = document.getElementsByName('emal');
	const categry = document.getElementById('category').value;
	const addres = document.getElementById('address').value;
	
	console.log(phon);
		let abc=[];	
	for (i=0;i<phon.length;i++){  
		abc.push({"phonenumber":phon[i].value});
		} 
		console.log(abc);
		let cde=[];
		for (i=0;i<emails.length;i++){  
			cde.push({"email":emails[i].value});
			} 
     console.log(cde);
      
		await fetch('http://localhost:8085/addContact',{
			method:'POST',
			headers:{
				'Accept':'application/json',
				'content-Type':'application/json'
			},
			body:JSON.stringify(
				{
				name: usernam,
				
				phone:abc,
				emails:cde,
				category:categry,
				address: addres,
				useremail:usremail
			}
			
			)
		})
	  document.getElementById('validation').reset();
	  $('#exampleModal').modal('toggle');
	  swal({
		  title: "Good job!",
		  text: "Contact added Successfully.........!",
		  icon: "success",
		  button: "ok",
		}).then(function() {
		  window.location = "/home.html";
	  });
	  }

	
	
	  const viewalluser = async () => {
		let p=[];
		let url = `http://localhost:8085/allcontact/${usremail}`;
		console.log(url);
	     helper(url);
		  }
	   viewalluser();

		


save.addEventListener('click',saveuserdata);
updatecontact.addEventListener('click',updateData);



function searchuserdata()  {
  const usrnm=document.getElementById("searchbyname").value;
	let url = `http://localhost:8085/search/${usrnm+" "+usremail}`;
	console.log(url);
     helper(url);
	  }

var sortdata="";
	  function helper(url) {
		let p=[];
		fetch(url).then((data)=> {
		  return data.json();
		}).then((objectData)=> {
		   sortdata=objectData;
		//    console.log(objectData);
		   cssAdder(objectData);
		}).catch((error)=>{
			console.log(error);
			document.getElementById("view-all-data").innerHTML="<h1 id='error'>Oops.... Record not found!!!</h1>";
		})
		  }


		  function cssAdder(objectData){
			let tableData="";
		
		console.log(objectData);
		objectData.map((v)=>{
			
			let opt="";
			let emal="";
			if(v.phone!=null){
		   v.phone.map((pv)=>{
			opt+=`<option >${pv.phonenumber}</option>`
		   })
		}
		if(v.emails!=null){
		   v.emails.map((pv)=>{
			emal+=`<option >${pv.email}</option>`
		   })
		}
		
			tableData+=`    <div class="cards">
			<div class="card">
			  <div class="card__content">
				  <div>
			 <label for="">Name:</label> <p>${v.name}</p>
		  </div>
		  <div>
			 <label for="">phone:</label> 
			 <select> ${opt}</select>
		  </div>
	  
		  <div>
			  <label for="">Email:</label> 
			  <select>${emal} </select>
		   </div>
		   <div>
			<label for="">Category:</label> <p>${v.category}</p>
		 </div>
		  <div>
			 <label for="">Address:</label> <p>${v.address}</p>
		  </div>
			  </div>
			  <div class="card__info">
				<div>
				  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleee" onclick="editcontactt('${v.contactid}')">
					Edit
					  </button>
				</div>
				
				<div>
				  <button type="button" class="btn btn-primary" onclick="deleteUser('${v.contactid}')">
					Delete
					  </button>
				</div>
				 
				<div>
				  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
					Share
					  </button>
				</div>
			  </div>
			</div>
			</div> `
		
         
		})
		
			document.getElementById("view-all-data").innerHTML=tableData;
	
		  }
		
		
		  function compareStrings() {
			sortdata.sort((a, b) => {
				let fa = a.name.toLowerCase(),
					fb = b.name.toLowerCase();
				if (fa < fb) {
					return -1;
				}
				if (fa > fb) {
					return 1;
				}
				return 0;
			});
			cssAdder(sortdata);
		  }
		  
		
		  function deleteUser(id){

			let x=document.cookie;
        let y=x.split("=");

           if(y[1]==undefined){
           window.location="/index.html";
        }else{

			console.log(id);
		let url = `http://localhost:8085/delete/${id}`;
		let response=  fetch(url,{method:'DELETE'});
			swal({
				title: "Good job!",
				text: "register sucessfully redirecting to login page....!",
				icon: "success",
				button: "ok",
			  }).then(function() {
				window.location = "/home.html";
			});
		}
		}



		
function logout()  {
 console.log("dfvffffffffffffff");
	sessionStorage.clear();
	document.cookie=`email=${usremail};max-age=${0}`;
	window.location="/index.html";
		}
  