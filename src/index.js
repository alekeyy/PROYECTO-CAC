window.addEventListener("load", (e) => {
  e.preventDefault();
  let arrItems = [];

  function renderItem(arrACopiar) {
    const arrNuevo = arrACopiar.map((x) => item(x));
    console.log("asdasd");

    document.getElementById("catalogo").innerHTML = arrNuevo.join(" ");
  }

  function item(x) {
    return `
    <div class="item">
        <img src="./img/shop/${x.id}.webp" alt="Caja de saquitos de té">
        <h3>${x.title}</h3>
        <p>Precio: <span class="bold">$${x.price}</span></p>
        <a href="" class="hero--button">COMPRAR</a>
    </div>
    `;
  }

  function main() {
    // console.log(TeaData);

    fetch("src/apiData.json")
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        for (let teaItem of data.TeaData) {
          //teaItem debido a que item es una funcion
          arrItems.push(teaItem);
        }
        console.log(arrItems);
        renderItem(arrItems); //Lo movi aca por que si esta fuera del then, se ejecuta primero sin que suceda la insercion de datos.
      });
  }

  main();

  // @**** Función guarga la información suministrada por el usuario en el localStorage ****
  const formulario = document.getElementById("form-storage");

  formulario.addEventListener("submit", (event) => {
    event.preventDefault();

    const nombre = formulario.nombre.value;
    const email = formulario.email.value;
    const telefono = formulario.telefono.value;
    const comentario = formulario.comentario.value;

    // Crea un objeto para almacenar los valores con sus claves
    let storage = {
      nombre,
      email,
      telefono,
      comentario,
    };

    // Guarda el objeto en el localStorage
    localStorage.setItem("storage", JSON.stringify(storage));

    // Limpia los campos de entrada después de guardar la información
    formulario.reset();
  });
});
