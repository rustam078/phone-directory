

let edittableData="";

  

function editcontactt(id){
  let x=document.cookie;
let y=x.split("=");

if(y[1]==undefined){
 window.location="/index.html";
}

  let url = `http://localhost:8085/find/${id}`;
  fetch(url).then((data)=> {
    return data.json();
  }).then((objectData)=> {
    console.log(objectData);
  
      var editopt="";
      let editemal=""; 
      if(objectData.phone!=null){
      objectData.phone.map((pv)=>{
        editopt+= `<div class="input-control">\
                  <label >your phone no</label> <div id="addon">\
                   <input id="pho"\
                     type="text"name="phons" value="${pv.phonenumber}"required/>\
                    <a href="javascript:void(0)" class="remove-btn float-end btn btn-danger">Remove</a>\
                    </div>
                </div>`
      
      
     
      })}
      if(objectData.emails!=null){
      objectData.emails.map((pv)=>{
        editemal+= `<div class="input-control">\
                  <label >your Email</label><div id="addon">\
                   <input id="email"\
                     type="email" name="emals" value="${pv.email}" required/>\
                    <a href="javascript:void(0)" class="remove-btn float-end btn btn-danger">Remove</a>\
                    </div>\
                </div>`
      
      
     
      })}
      console.log(objectData.contactid);
  
edittableData=`  <input id="uid" type='hidden' value="${objectData.contactid}"/> 
 <div class="input-control">
      <label >Username</label> 
      <input id="usernames"	path="name" type="text" value="${objectData.name}"required/>

    </div>
    <div class="acc">
      <label >your phone no</label> 
        <a href="javascript:void(0)" class="add-more-phones float-end btn btn-primary">Add More</a>
    </div>
    <div class="paste-new-field2" id="aaa"></div>

    <div class="acc">
      <label >Email</label>
        <a href="javascript:void(0)" class="add-more-emails float-end btn btn-primary">Add More</a>
    </div>
    <div class="paste-new-field3" id='bbb'></div>
    
    <div class="input-control">
      <label >Category</label> 
      <input id="categorys" path="category" type="text"value="${objectData.category}"required/>
    </div>

    <div class="input-control">
      <label >Address</label> 
      <input id="addresss" path="address" type="text"value="${objectData.address}"required/>
    </div>`	
    document.getElementById("model-edit").innerHTML=edittableData;	
    document.getElementById("aaa").innerHTML=editopt;	
    document.getElementById("bbb").innerHTML=editemal;	
  })
}

	

$(document).ready(function(){

  $(document).on('click','.add-more-phones',function(){

    $('.paste-new-field2').append('<div class="input-control">\
          <label >your phone no</label>  <div id="addon">\
          <input id="pho"\
            path="phone" name="phons" type="text" required/>\
            <a href="javascript:void(0)" class="remove-btn float-end btn btn-danger">Remove</a>\
            </div>\
        </div>');

  });
});



  $(document).ready(function(){

$(document).on('click','.add-more-emails',function(){

  $('.paste-new-field3').append('<div class="input-control">\
        <label >Email</label> <div id="addon">\
        <input id="email"\
          path="email" name="emals" type="email" required/>\
          <a href="javascript:void(0)" class="remove-btn float-end btn btn-danger">Remove</a>\
          </div>\
      </div>');

});
});

  $(document).ready(function(){

$(document).on('click','.remove-btn',function(){

  $(this).closest('.input-control').remove();

});
});

 
