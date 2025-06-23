function squareRoot() {
  const numberInput = parseFloat(document.getElementById("numberInput").value);

  const result = Math.sqrt(numberInput).toFixed(2);
  document.getElementById("result").innerText = `${result}`;
}
