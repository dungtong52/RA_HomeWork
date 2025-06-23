let sum = 0;
const n = +prompt("Nhập vào số n: ");

if (!Number.isInteger(n) || n <= 0) alert(`Dữ liệu nhập vào không hợp lệ`);
else {
  for (let i = 1; i <= n; i++) {
    sum += i;
  }
  alert(sum);
}
