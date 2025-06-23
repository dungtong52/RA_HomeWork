let arr = [];
let sum = 0;
let i = 0;

while (i < 7) {
  arr[i] = +prompt(`Nhập vào số thứ ${i + 1}: `);
  i++;
}
for (let i = 0; i < 7; i++) {
  if (!Number.isInteger(arr[i]) || arr[i] <= 0)
    alert(`Số thứ ${i + 1} không hợp lệ`);
  else if (arr[i] % 2 === 0) sum += arr[i];
}
alert(`Tổng: ${sum}`);
