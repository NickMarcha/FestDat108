//validering av navn of etternavn
function validateName(name) {
	if( /^[a-zA-Z]+$/.test(name.value)){
		name.setAttribute("style", "border-color: green");
		return true;
//		ubrukt:		fornavn.setAttribute("style", "background-color: green");
	}else {
		name.setAttribute("style", "border-color: red");
		return false;
	}
}
//validering for mobil nummer
function validateMB(mb) {
	if(mb.value.toString().length == 8){
		mb.setAttribute("style", "border-color: green");
		return true;
	}else {
		mb.setAttribute("style", "border-color: red");
		return false;
	}
}
/*validering av passord strong:
^	The password string will start this way
(?=.*[a-z])	The string must contain at least 1 lowercase alphabetical character
(?=.*[A-Z])	The string must contain at least 1 uppercase alphabetical character
(?=.*[0-9])	The string must contain at least 1 numeric character
(?=.[!@#\$%\^&])	The string must contain at least one special character, but we are escaping reserved RegEx characters to avoid conflict
(?=.{8,})	The string must be eight characters or longer
 */
function validatePass(passord) {
	var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
	/*samme som strong, utenom:
	 * six characters or more and has at least one lowercase and 
	 * one uppercase alphabetical character or 
	 * has at least one lowercase and 
	 * one numeric character or
	 *  has at least one uppercase and
	 *   one numeric character.
	 */
	if(strongRegex.test(passord.value)){
		passord.setAttribute("style", "border-color: green");
		return true;
	}else if(mediumRegex.test(passord.value)){
		passord.setAttribute("style", "border-color: yellow");
		return true;
	}else {
		return false;
		passord.setAttribute("style", "border-color: red");
	}
}

//validering av repetering av passord
function validatePassRep(passord,passordRepetert) {
	if( passord.value.localeCompare(passordRepetert.value) == 0){
		passordRepetert.setAttribute("style", "border-color: green");
		return true;
	}else {
		passordRepetert.setAttribute("style", "border-color: red");
		return false;
	}
}


const Validators = [];

function ValidateAll(theform){
	if(ValidateAllHelper() && confirm("Send info til tjener!")) {
		theform.submit();
	} else {
		document.getElementById("fm").innerHTML = "Sjekk at alle felter er gyldig fyllt inn!";
	}
}
function ValidateAllHelper(){
	var i;
	for (i = 0; i < Validators.length; i++) {
	  switch(Validators[i][1]){
	  case "Navn":
			if(!validateName(Validators[i][0])){
				console.log("A Name failed" + Validators[i][0].value);
				return false;
			}
	    break;
	  case "Tlf":
		  if(!validateMB(Validators[i][0])){
			  console.log("A number failed");
			  return false;
		  }
	    break;
	  case "PassordCombo":
		  if(!validatePass(Validators[i][0][0])){
			  console.log("A password failed");
			  return false;
		  }
		  if(!validatePassRep(Validators[i][0][0],Validators[i][0][1])){
			  console.log("A password rep Failed");
			  return false;
		  }
	  default:
		  break;
	  }
	}	
	return true;
}
function AddValidationListener(element, type){
	switch(type) {
	  case "Navn":
		  element.addEventListener('keyup', function(event) {
				validateName(element);
			});
	    break;
	  case "Tlf":
		  element.addEventListener('keyup', function(event) {
				validateMB(element);
			});
	    break;
	  case "PassordCombo":
		  const passord = element[0];
			const passordRepetert = element[1];
			//passordRep vil valideres også om endringer skjer i passordfeltet
			passord.addEventListener('keyup', function(event) {
				validatePass(passord);
				validatePassRep(passord, passordRepetert)
			});
			passordRepetert.addEventListener('keyup', function(event) {
				validatePassRep(passord, passordRepetert)
			});
	  default:
		  break;
	}
	Validators.push([element,type]);
}