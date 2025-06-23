//chuyển đổi thành số
let beforeCasting = "5";
let afterCasting = Number(beforeCasting);
document.getElementById(
  "line-1"
).innerText = `- chuyển đổi giá trị "${beforeCasting}" thành số: ${afterCasting}`;

beforeCasting = "-1";
afterCasting = +beforeCasting;
document.getElementById(
  "line-2"
).innerText = `- chuyển đổi giá trị "${beforeCasting}" thành số: ${afterCasting}`;

beforeCasting = "Hello";
afterCasting = Number(beforeCasting);
document.getElementById(
  "line-3"
).innerText = `- chuyển đổi giá trị "${beforeCasting}" thành số: ${afterCasting}`;

// chuyển đổi thành chuỗi
beforeCasting = 55;
afterCasting = String(beforeCasting);
document.getElementById(
  "line-4"
).innerText = `- chuyển đổi giá trị ${beforeCasting} thành chuỗi: "${afterCasting}"`;

beforeCasting = -10;
afterCasting = `${beforeCasting}`;
document.getElementById(
  "line-5"
).innerText = `- chuyển đổi giá trị ${beforeCasting} thành chuỗi: "${afterCasting}"`;

beforeCasting = 0;
afterCasting = beforeCasting + "";
document.getElementById(
  "line-6"
).innerText = `- chuyển đổi giá trị ${beforeCasting} thành chuỗi: "${afterCasting}"`;
