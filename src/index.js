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

  const fetchUsers = () => {
    fetch("http://localhost:8080/webapp/UserListController")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => populateTable(data))
      .catch((error) =>
        console.error(
          "There has been a problem with your fetch operation:",
          error
        )
      );
  };

  const populateTable = (users) => {
    const tableBody = document.getElementById("tableBody");
    if (tableBody) {
      tableBody.innerHTML = ""; // Clear the table before populating

      users.forEach((user) => {
        const newRow = document.createElement("tr");
        newRow.innerHTML = `
          <td>${user.id}</td>
          <td><input type="text" value="${user.nombre}" disabled></td>
          <td><input type="text" value="${user.email}" disabled></td>
          <td><input type="text" value="${user.telefono}" disabled></td>
          <td><input type="text" value="${user.comentario}" disabled></td>
          <td>
            <i class="fas fa-edit" onclick="editUser(${user.id}, this)"></i>
            <i class="fas fa-save" onclick="saveUser(${user.id}, this)" style="display:none"></i>
            <i class="fas fa-trash-alt" onclick="deleteUser(${user.id}, this)"></i>
          </td>
        `;
        tableBody.appendChild(newRow);
      });
    } else {
      console.error("tableBody element not found");
    }
  };

  window.editUser = (id, element) => {
    const row = element.closest("tr");
    const inputs = row.querySelectorAll("input");
    inputs.forEach((input) => (input.disabled = false));
    element.style.display = "none";
    row.querySelector(".fa-save").style.display = "inline";
  };

  window.saveUser = (id, element) => {
    const row = element.closest("tr");
    const inputs = row.querySelectorAll("input");
    const updatedUser = {
      id,
      nombre: inputs[0].value,
      email: inputs[1].value,
      telefono: inputs[2].value,
      comentario: inputs[3].value,
    };

    fetch("http://localhost:8080/webapp/UpdateUserController", {
      method: "PUT",
      body: JSON.stringify(updatedUser),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        inputs.forEach((input) => (input.disabled = true));
        element.style.display = "none";
        row.querySelector(".fa-edit").style.display = "inline";
      })
      .catch((error) =>
        console.error(
          "There has been a problem with your fetch operation:",
          error
        )
      );
  };

  window.deleteUser = (id, element) => {
    fetch(`http://localhost:8080/webapp/DeleteUserController?id=${id}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const row = element.closest("tr");
        row.remove();
      })
      .catch((error) =>
        console.error(
          "There has been a problem with your fetch operation:",
          error
        )
      );
  };

  fetchUsers();

  const ApiCall = () => {
    const nombre = document.getElementById("name").value;
    const email = document.getElementById("mail").value;
    const telefono = document.getElementById("phone").value;
    const comentario = document.getElementById("coment").value;

    const jsonRequest = { nombre, email, telefono, comentario };
    console.log(jsonRequest);

    fetch("http://localhost:8080/webapp/CreateUserController", {
      method: "POST",
      body: JSON.stringify(jsonRequest),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.text();
      })
      .then((text) => {
        try {
          const data = JSON.parse(text);
          addUser(data);
          fetchUsers();
        } catch (error) {
          console.error("Failed to parse JSON:", error, text);
        }
      })
      .catch((error) =>
        console.error(
          "There has been a problem with your fetch operation:",
          error
        )
      );
  };

  const addUser = (data) => {
    console.log(data);
  };

  const showError = (message) => {
    const errorElement = document.getElementById('error-message');
    if (errorElement) {
      errorElement.textContent = message;
      errorElement.style.display = 'block';
    } else {
      console.error("error-message element not found");
    }
  };

  const hideError = () => {
    const errorElement = document.getElementById('error-message');
    if (errorElement) {
      errorElement.style.display = 'none';
    }
  };

  const validateForm = () => {
    const nombre = document.getElementById("name").value;
    const email = document.getElementById("mail").value;
    const telefono = document.getElementById("phone").value;
    const comentario = document.getElementById("coment").value;

    if (!nombre || !email || !telefono || !comentario) {
      //const msjError = document.getElementById('error-message');
      showError('Todos los campos son obligatorios.');
      return false;
    }
    hideError();
    return true;
  };
  const clearForm = () => {
    document.getElementById("name").value = "";
    document.getElementById("mail").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("coment").value = "";
  };
  const btnEnviar = document.getElementById("btn-enviar");
  if (btnEnviar) {
    btnEnviar.addEventListener("click", (e) => {
      e.preventDefault();
      if (validateForm()) {
        ApiCall();
        clearForm();
      }
    });
  } else {
    console.error("btn-enviar element not found");
  }
});
