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

formLogin.addEventListener("submit", (e) => {
    e.preventDefault();
    const textError = [];
    if(emailLogin.value == "") {
        console.log("Error (email)");
        textError.push("*Por favor, introduzca su email.");
    } 
    if(passwordLogin.value == "") {
        console.log("Error (contraseña)");
        textError.push("*Por favor, introduzca su contraseña.");
    }
    errorLogin.innerHTML = textError.join("<br>");
});

//----------------------------------------------------------------

const formRegister = document.getElementById("form-register");
const username = document.getElementById("username");
const emailRegister = document.getElementById("email-register");
const passwordRegister = document.getElementById("password-register");
const errorRegister = document.getElementById("warning-register");

formRegister.addEventListener("submit", (e) => {
    e.preventDefault();
    const textError = [];
    if(username.value == "") {
        console.log("Error (usuario)");
        textError.push("*Por favor, introduzca el usuario.");
    }
    if(emailRegister.value == "") {
        console.log("Error (email)");
        textError.push("*Por favor, introduzca su email.");
    } 
    if(passwordRegister.value == "") {
        console.log("Error (contraseña)");
        textError.push("*Por favor, introduzca su contraseña.");
    }
    errorRegister.innerHTML = textError.join("<br>");
});