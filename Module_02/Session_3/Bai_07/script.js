function divisorPrint(n) {
  let resultString = "";
  for (let i = 1; i <= n; i++) {
    if (n % i === 0) resultString += i + " ";
  }
  return resultString;
}

function resultPrint() {
  const numberInput = Number(document.getElementById("numberInput").value);
  const result = document.getElementById("result");

  if (!Number.isInteger(numberInput) || numberInput <= 0) {
    result.innerText = `Số nhập vào không hợp lệ`;
  } else {
    result.innerText = `Các ước số của ${numberInput} là: ${divisorPrint(
      numberInput
    )}`;
  }
}
document.querySelector(".btn").addEventListener("click", resultPrint);
