document.getElementById("btn").addEventListener("click", evenOdd);

function evenOdd() {
  const numberInput = parseInt(document.getElementById("numberInput").value);
  const textOutput = document.getElementById("result");

  if (numberInput % 2 === 0) {
    textOutput.innerText = `Số ${numberInput} vừa nhập là số chẵn`;
  } else textOutput.innerText = `Số ${numberInput} vừa nhập là số lẻ`;
}
