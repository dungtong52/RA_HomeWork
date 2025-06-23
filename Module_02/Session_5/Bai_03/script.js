let numberInput;
let arrayNumberInput = [];
let numberReverse;
let valid = false;

while (!valid) {
  numberInput = Number(prompt("Nhập 1 dãy số bất kỳ: ").trim());
  if (isNaN(numberInput) || numberInput < 10) {
    alert("Dãy không hợp lệ. Nhập lại!");
  } else {
    arrayNumberInput = numberInput.toString().split("");
    numberReverse = arrayNumberInput.reverse().join("");
    alert(arrayNumberInput);
    alert(numberReverse);
    valid = true;
  }
}
