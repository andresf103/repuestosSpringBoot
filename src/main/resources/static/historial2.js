function toggle(className){
console.log(className)
let divColapsables = document.getElementsByClassName(className);
for (let i = 0; i < divColapsables.length; i++) {
    if(divColapsables[i].style.display==="none"){
    divColapsables[i].style.display="flex";}else{
    divColapsables[i].style.display="none";
    }
    }
}