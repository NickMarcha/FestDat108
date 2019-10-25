
function validateName(name) {
	 if( /^[a-zA-Z]+$/.test(name.value)){
		 name.setAttribute("style", "border-color: green");
//			fornavn.setAttribute("style", "background-color: green");
	 }else {
		 name.setAttribute("style", "border-color: red");
	 }
	}

function validateMB(mb) {
	 if(mb.value.toString().length == 8){
		 mb.setAttribute("style", "border-color: green");
//			fornavn.setAttribute("style", "background-color: green");
	 }else {
		 mb.setAttribute("style", "border-color: red");
	 }
	}

function validatePass(passord) {
	var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
	 if(strongRegex.test(passord.value)){
		 passord.setAttribute("style", "border-color: green");
//			fornavn.setAttribute("style", "background-color: green");
	 }else if(mediumRegex.test(passord.value)){
		 passord.setAttribute("style", "border-color: yellow");
	 }else {
		 passord.setAttribute("style", "border-color: red");
	 }
	}

function validatePassRep(passord,passordRepetert) {
	 if( passord.value.localeCompare(passordRepetert.value) == 0){
		 passordRepetert.setAttribute("style", "border-color: green");
//			fornavn.setAttribute("style", "background-color: green");
	 }else {
		 passordRepetert.setAttribute("style", "border-color: red");
	 }
	}