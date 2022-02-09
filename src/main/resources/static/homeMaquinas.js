   const button = document.getElementById("buscar");
    const searchBox=document.getElementById("buscador");
    const dir=window.location.pathname;
    const end="/maquina";
    button.addEventListener("click", (ev) => {
        location.href = dir.substring(0,end.length)+ "/" + searchBox.value;
    });
    searchBox.addEventListener("keypress", (ev) => {
        if (ev.key === 'Enter')
            location.href = dir.substring(0,end.length)+ "/" + searchBox.value;
    });