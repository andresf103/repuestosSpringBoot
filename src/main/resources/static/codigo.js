document.getElementById("submit").onclick = () => document.getElementById("form").submit();

function buscar() {
    const searchBox = document.getElementById("buscar");
    searchBox.addEventListener("click", (ev) => {
        location.href = "maquina/" + document.getElementById("buscador").Value;
    });
}