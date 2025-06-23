let string = "";
const n = +prompt("Nhập vào số n: ");

if (!Number.isInteger(n) || n <= 0) alert(`Dữ liệu nhập vào không hợp lệ`);
else {
  for (let i = 1; i <= n; i++) {
    if (i % 5 === 0) string += i + " ";
  }
  alert(`Các số chia hết cho 5 từ 1 đến ${n} là: ${string}`);
}
