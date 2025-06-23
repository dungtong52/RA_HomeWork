let numbers = [2, 5, 7, 4, 1, 8, 6, 2, 5, 7];
let numberInput;
let count = 0;

numberInput = Number(prompt("Nhập 1 số bất kỳ: ").trim());
for (let i = 0; i < numbers.length; i++) {
  if (numbers[i] === numberInput) {
    count++;
  }
}

if (count !== 0) alert(`Số ${numberInput} xuất hiện ${count} lần trong mảng`);
else alert(`Số ${numberInput} không tồn tại trong mảng`);
