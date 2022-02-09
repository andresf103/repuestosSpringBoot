   const button = document.getElementById("buscar");
    const searchBox=document.getElementById("buscador");
    button.addEventListener("click", (ev) => {
        location.href = "/maquina/" + searchBox.value;
    });
    searchBox.addEventListener("keypress", (ev) => {
        if (ev.key === 'Enter')
            location.href = "/maquina/" + searchBox.value;
    });