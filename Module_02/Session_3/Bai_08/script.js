function isPrime(n) {
  let count = 0;
  for (let i = 1; i <= n; i++) {
    if (n % i === 0) count++;
  }
  if (count === 2) return true;
  else return false;
}

function checkNumber() {
  const numberInput = Number(document.getElementById("numberInput").value);
  const result = document.getElementById("result");

  if (!Number.isInteger(numberInput) || numberInput <= 0) {
    result.innerText = `Số nhập vào không hợp lệ`;
  } else if (isPrime(numberInput))
    result.innerText = `Số ${numberInput} là số nguyên tố`;
  else result.innerHTML = `Số ${numberInput} <b>KHÔNG PHẢI</b> là số nguyên tố`;
}
document.querySelector(".btn").addEventListener("click", checkNumber);
