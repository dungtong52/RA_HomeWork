let numberInput;
let arrayNumberInput = [];
let max;
let valid = false;

while (!valid) {
  numberInput = Number(prompt("Nhập 1 dãy số bất kỳ: ").trim());
  if (isNaN(numberInput) || numberInput === null) {
    alert("Dãy không hợp lệ. Nhập lại!");
  } else {
    arrayNumberInput = numberInput.toString().split("");
    max = arrayNumberInput[0];
    for (let i = 1; i < arrayNumberInput.length; i++) {
      if (max < arrayNumberInput[i]) {
        max = arrayNumberInput[i];
      }
    }
    alert(`${max} là số lớn nhất trong dãy số`);
    valid = true;
  }
}
