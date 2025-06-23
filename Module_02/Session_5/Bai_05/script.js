let numberInput;
let arrayInput = new Array();
let oddSum = 0;
let evenSum = 0;
let valid = false;

while (!valid) {
  numberInput = Number(prompt("Nhập 1 dãy số bất kỳ: ").trim());
  if (isNaN(numberInput) || numberInput === null) {
    alert("Dãy không hợp lệ. Nhập lại!");
  } else {
    arrayInput = numberInput.toString().split("");
    for (let i = 0; i < arrayInput.length; i++) {
      if (arrayInput[i] % 2 === 0) {
        evenSum += Number(arrayInput[i]);
      } else {
        oddSum += Number(arrayInput[i]);
      }
    }
    alert(`Tổng các số chẵn: ${evenSum}`);
    alert(`Tổng các số lẻ: ${oddSum}`);
    valid = true;
  }
}
