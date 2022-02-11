import buscar from '../codigo.js';
const cantSlashOfPathName=3;
buscar(cantSlashOfPathName);

async function getHistorial(idMaquina)
{
  let response = await fetch(`../historial/${idMaquina}`);
  let historial = await response.json();
  console.log(historial);
  return historial;
}