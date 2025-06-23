function fibonacci(n) {
  if (n === 0 || n === 1) return 1;
  return fibonacci(n - 1) + fibonacci(n - 2);
}

function fibonacciPrint(n) {
  let stringPrint = "";
  if (n < 0) stringPrint = "Không hợp lệ";
  else if (n === 0) stringPrint = "Không có số nào";
  else {
    for (let i = 0; i < n; i++) {
      stringPrint += fibonacci(i) + " ";
    }
  }
  return stringPrint;
}

function numberDisplay() {
  const numberInput = Number(document.getElementById("numberInput").value);
  const result = document.getElementById("result");

  result.innerText = fibonacciPrint(numberInput);
}

document.querySelector(".btn").addEventListener("click", numberDisplay);
