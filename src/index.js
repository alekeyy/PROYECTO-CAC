let arrItems = [];

function renderItem(arrACopiar){
    const arrNuevo = arrACopiar.map(x=>item(x));
    console.log("asdasd");

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
    // console.log(TeaData);

    fetch('src/apiData.json')
        .then(res => res.json())
        .then(data => {
            console.log(data)
            for(let teaItem of data.TeaData){ //teaItem debido a que item es una funcion
                arrItems.push(teaItem)
            }
            console.log(arrItems);
            renderItem(arrItems); //Lo movi aca por que si esta fuera del then, se ejecuta primero sin que suceda la insercion de datos.
        })
}

main();