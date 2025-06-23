let oddCount = 0;
let evenCount = 0;
let arr = [];

for (let i = 1; i <= 7; i++) {
  arr[i] = +prompt(`Nhập vào số thứ ${i}: `);
}
for (let i = 1; i <= 7; i++) {
  if (!Number.isInteger(arr[i]) || arr[i] <= 0) alert("Error input number");
  else if (arr[i] % 2 === 0) evenCount++;
  else oddCount++;
}
alert(`Số lượng số chẵn: ${evenCount}`);
alert(`Số lượng số lẻ: ${oddCount}`);
