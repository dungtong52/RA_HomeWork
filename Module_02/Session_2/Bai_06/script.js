const a = Number(prompt("Mời bạn nhập vào số a:"));
const b = Number(prompt("Mời bạn nhập vào số b:"));
const operations = prompt("Mời bạn nhập các phép tính : (+, -, *, /)");

switch (operations) {
  case "+":
    alert(`Kết quả của phép tính trên: a + b = ${a + b}`);
    break;
  case "-":
    alert(`Kết quả của phép tính trên: a - b = ${a - b}`);
    break;
  case "*":
    alert(`Kết quả của phép tính trên: a * b = ${a * b}`);
    break;
  case "/":
    if (b != 0) {
      alert(`Kết quả của phép tính trên: a / b = ${a / b}`);
    } else alert(`Kết quả không hợp lệ. Không chia được cho 0`);
    break;
}
