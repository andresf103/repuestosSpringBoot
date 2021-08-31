    const button = document.getElementById("buscar");
    const searchBox=document.getElementById("buscador");
    button.addEventListener("click", (ev) => {
        location.href = "/maquina/" + searchBox.value;
    });