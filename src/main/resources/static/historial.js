async function getHistorial(idMaquina)
{
  let response = await fetch(`../historial/${idMaquina}`);
  let historial = await response.json()
  console.log(historial)
  return historial;
}