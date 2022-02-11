//document.getElementById("submit").onclick = () => document.getElementById("form").submit();
//3
function buscar(cantSlashes) {
const dir=window.location.pathname;
let ultimoIndice=dir.length;
    if(dir.split("/").length-1>cantSlashes){
        ultimoIndice=dir.lastIndexOf("/");
    }
    const button = document.getElementById("buscar");
    const searchBox = document.getElementById("buscador");
    button.addEventListener("click", (ev) => {
        location.href = dir.substring(0,ultimoIndice)+ "/" + searchBox.value;
    });
    searchBox.addEventListener("keypress", (ev) => {
        if (ev.key === 'Enter')
     location.href = dir.substring(0,ultimoIndice)+ "/" + searchBox.value;
 });
}

export default buscar;