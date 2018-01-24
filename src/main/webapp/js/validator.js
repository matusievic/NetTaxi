function validateSignIn() {
	var email = document.getElementById('email-sign-in').value;
	var password = document.getElementById('password-sign-in').value;

	var emailLabel = document.getElementById('email-sign-in-label');
	var passwordLabel = document.getElementById('password-sign-in-label');

	var result = true;

	if (!validateEmail(email)) {
		result = false;
		emailLabel.innerHTML = 'Please provide a valid email address';
	} else {
		emailLabel.innerHTML = '';
	}

	if (!validatePassword(password)) {
		result = false;
		passwordLabel.innerHTML = 'Please provide a valid password';
		//$('label[for="password-sign-in"').text("Please provide a valid password");
	} else {
		passwordLabel.innerHTML = '';
	}

	return result;
}

function validateSignUp() {
	var login = document.getElementById('login-sign-up').value;
	var phone = document.getElementById('phone-sign-up').value;
	var email = document.getElementById('email-sign-up').value;
	var age = document.getElementById('age-sign-up').value;
	var firstPassword = document.getElementById('password-sign-up').value;
	var secondPassword = document.getElementById('second-password-sign-up').value;

	var loginLabel = document.getElementById('login-sign-up-label');
	var phoneLabel = document.getElementById('phone-sign-up-label');
	var emailLabel = document.getElementById('email-sign-up-label');
	var ageLabel = document.getElementById('age-sign-up-label');
	var firstPasswordLabel = document.getElementById('password-sign-up-label');
	var secondPasswordLabel = document.getElementById('second-password-sign-up-label');

	var result = true;

	if (!validateLogin(login)) {
		result = false;
		loginLabel.innerHTML = 'Only latin characters, numbers, and "_" are allowed.<br>The first symbol is latin character.<br>At least 5 symbols.';
	} else {
		loginLabel.innerHTML = '';
	}

	if (!validatePhone(phone)) {
		result = false;
		phoneLabel.innerHTML = 'Please provide a valid phone number';
	} else {
		phoneLabel.innerHTML = '';
	}

	if (!validateEmail(email)) {
		result = false;
		emailLabel.innerHTML = 'Please provide a valid email address';
	} else {
		emailLabel.innerHTML = '';
	}

	if (!validateAge(age)) {
		result = false;
		ageLabel.innerHTML = 'Not lower than 7 and not greater than 120';
	} else {
		ageLabel.innerHTML = '';
	}

	if (!validatePassword(firstPassword)) {
		result = false;
		firstPasswordLabel.innerHTML = 'Only latin characters and numbers are allowed.<br>Contains at least one uppercase and one lowercase latin, one number.<br>At least 6 symbols.';
	} else {
		firstPasswordLabel.innerHTML = '';
	}

	if (firstPassword != secondPassword) {
		result = false;
		secondPasswordLabel.innerHTML = 'Please enter a correct password';
	} else {
		secondPasswordLabel.innerHTML = '';
	}

	return result;
}


/* data unit validators */

function validateLogin(login) {
	var loginRegEx = /[A-Za-z]([\w_]{4,})/;

	return loginRegEx.test(login);
}

function validatePhone(phone) {
	var phoneRegEx = /\+375[(29)(33)(44)](\d{7})/

	return phoneRegEx.test(phone);
}

function validateEmail(email) {
	var emailRegEx = /.+@.+\..+/;

	return emailRegEx.test(email);
}

function validatePassword(password) {
	var passwordRegEx = /^(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;

	return passwordRegEx.test(password);
}


function validateAge(age) {
	return age >= 7 && age <= 120;
}