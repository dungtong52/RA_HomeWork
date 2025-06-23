// Sử dụng hàm Math.abs(), lấy tuyệt đối
let beforeNumber = -5.5;
let afterNumber = Math.abs(beforeNumber);
document.getElementById(
  "line-1"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;

beforeNumber = 10;
afterNumber = Math.abs(beforeNumber);
document.getElementById(
  "line-2"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;

// Sử dụng hàm Math.ceil(), làm tròn lên
beforeNumber = 4.4;
afterNumber = Math.ceil(beforeNumber);
document.getElementById(
  "line-3"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;

beforeNumber = 5.8;
afterNumber = Math.ceil(beforeNumber);
document.getElementById(
  "line-4"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;
//Sử dụng hàm Math.floor(), làm tròn xuống
beforeNumber = 5.7;
afterNumber = Math.floor(beforeNumber);
document.getElementById(
  "line-5"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;

beforeNumber = 6.1;
afterNumber = Math.floor(beforeNumber);
document.getElementById(
  "line-6"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;
//Sử dụng hàm Math.round(), nếu thập phân từ 5 trở lên thì làm tròn lên, nếu thập phân dưới 5 thì làm tròn xuống
beforeNumber = 10.1;
afterNumber = Math.round(beforeNumber);
document.getElementById(
  "line-7"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;

beforeNumber = 20.6;
afterNumber = Math.round(beforeNumber);
document.getElementById(
  "line-8"
).innerText = `- Trước khi chuyển đổi "${beforeNumber}", sau khi chuyển: ${afterNumber}`;
