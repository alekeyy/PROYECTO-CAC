const elements = document.getElementsByClassName("logged");
let userLogged = localStorage.getItem("dataLogin");
userLogged = userLogged ? JSON.parse(userLogged) : null;

if(elements){
    const elementsArray = Array.from(elements);
    elementsArray.forEach(element => {
        if(userLogged == null) {
            element.style.display = "none";
        }
    });
}

const elementButton = document.getElementById("button-logged");
if(userLogged){
    elementButton.innerHTML = "Cerrar sesion";
    elementButton.addEventListener("click", () => {
        localStorage.removeItem("dataLogin");
    })
}
else{
    elementButton.innerHTML = "Iniciar sesion";
}