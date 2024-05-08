/* EN CATALOGO INYECTAR 
<div class="item">
            <img src="./img/shop/1.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$4300</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>

          <div class="item">
            <img src="./img/shop/2.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$2000</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>

          <div class="item">  
            <img src="./img/shop/3.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$2700</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>

          <div class="item">
            <img src="./img/shop/4.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$2100</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>

          <div class="item">
            <img src="./img/shop/5.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$1800</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>

          <div class="item">
            <img src="./img/shop/6.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$1500</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>

          <div class="item">
            <img src="./img/shop/7.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$2000</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>

          <div class="item">
            <img src="./img/shop/8.webp" alt="Caja de saquitos de té">
            <h3></h3>
            <p>Precio: <span class="bold">$3100</span></p>
            <a href="" class="hero--button">COMPRAR</a>
          </div>
        </div>
      </div>
*/

let arrItems = [];

function renderItem(arrACopiar){
    const arrNuevo = arrACopiar.map(x=>item(x));

    document.getElementById("catalogo").innerHTML = arrNuevo.join(" ");
}

function item(x){
    return `
    <div class="item">
        <img src="./img/shop/${x.id}.webp" alt="Caja de saquitos de té">
        <h3>${x.title}</h3>
        <p>Precio: <span class="bold">$${x.price}</span></p>
        <a href="" class="hero--button">COMPRAR</a>
    </div>
    `;
}

function main(){
    arrItems.push(
    {
        id: 1,
        title: "Té Finas hierbas",
        price: "4300"
    },  
    {
        id: 2,
        title: "Té Limón",
        price: "2000"
    },  
    {
        id: 3,
        title: "Té Naranja-Mango",
        price: "2700"
    },  
    {
        id: 4,
        title: "Té Menta",
        price: "2100"
    },
    {
        id: 5,
        title: "Té Frutilla",
        price: "1800"
    },  
    {
        id: 6,
        title: "Té Naranja",
        price: "1500"
    },  
    {
        id: 7,
        title: "Té Lavanda",
        price: "2000"
    },  
    {
        id: 8,
        title: "Té Mix",
        price: "3100"
    },   
    );

    renderItem(arrItems);
}

main();