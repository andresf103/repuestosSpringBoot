const button = document.getElementById("buscar");
const searchBox=document.getElementById("buscador");
const dir=window.location.pathname
const end= "/historial/vehiculo/2"
button.addEventListener("click", (ev) => {
    location.href = dir.substring(0,end.length)+ "/" + searchBox.value;
});
searchBox.addEventListener("keypress", (ev) => {
       if (ev.key === 'Enter')
    location.href = dir.substring(0,end.length)+ "/" + searchBox.value;
});

async function getHistorial(idMaquina)
{
  let response = await fetch(`../historial/${idMaquina}`);
  let historial = await response.json()
  console.log(historial)
  return historial;
}