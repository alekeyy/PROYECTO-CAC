const box = document.querySelector(".box");
const loginLink = document.querySelector(".login-link");
const registerLink = document.querySelector(".register-link");

registerLink.addEventListener("click", ()=> {
    box.classList.add("active");
});

loginLink.addEventListener("click", ()=> {
    box.classList.remove("active");
});

//----------------------------------------------------------------

const formLogin = document.getElementById("form-login");
const emailLogin = document.getElementById("email-login");
const passwordLogin = document.getElementById("password-login");
const errorLogin = document.getElementById("warning-login");

//Valida los campos del formulario y si el usuario existe o necesita registrarse.
formLogin.addEventListener("submit", (e) => {
    e.preventDefault();
    let user;
    const textError = [];
    if(emailLogin.value == "") {
        textError.push("*Por favor, introduzca su email.");
    } 
    if(passwordLogin.value == "") {
        textError.push("*Por favor, introduzca su contraseña.");
    }
    let data = localStorage.getItem("dataRegister");
    data = data ? JSON.parse(data) : [];

    if(data.length > 0 && textError.length == 0) {
        user = data.find(item => emailLogin.value == item.email && passwordLogin.value == item.password);
        if(user){
            localStorage.setItem("dataLogin", JSON.stringify(user));
            document.location.href = "index.html";
        }
        else{
            textError.push("*Usuario/a inexistente.");
        }
    }

    if(data.length == 0 && textError.length == 0) {
        textError.push("*Usuario/a inexistente.");
    }
    
    if(textError.length > 0){
        errorLogin.innerHTML = textError.join("<br>");
    }
});

//----------------------------------------------------------------

const formRegister = document.getElementById("form-register");
const username = document.getElementById("username");
const emailRegister = document.getElementById("email-register");
const passwordRegister = document.getElementById("password-register");
const errorRegister = document.getElementById("warning-register");

//Valida los campos del formulario y si el usuario ya esta registrado.
formRegister.addEventListener("submit", (e) => {
    e.preventDefault();
    const textError = [];
    if(username.value == "") {
        textError.push("*Por favor, introduzca el usuario.");
    }
    if(emailRegister.value == "") {
        textError.push("*Por favor, introduzca su email.");
    } 
    if(passwordRegister.value == "") {
        textError.push("*Por favor, introduzca su contraseña.");
    }
    if(textError.length > 0){
        errorRegister.innerHTML = textError.join("<br>");
    }
    else{
        let data = localStorage.getItem("dataRegister");
        data = data ? JSON.parse(data) : [];
        let dataRegister = {
            username: username.value,
            email: emailRegister.value,
            password: passwordRegister.value,
        };
        let avatar = data.find(item => item.email == dataRegister.email);
        if(avatar) {
            errorRegister.innerHTML = "Usuario ya registrado.";
        }
        else{
            data.push(dataRegister);
            localStorage.setItem("dataRegister", JSON.stringify(data));
            document.location.href = "login.html";
        }
    }
});