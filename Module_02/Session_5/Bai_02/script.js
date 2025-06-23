let numbers = new Array(2, 5, 7, 4, 1, 8, 6);
let numberInput;
let valid = false;
while (!valid) {
  numberInput = Number(prompt("Nhập vào số bất kỳ: ").trim());
  if (numbers.includes(numberInput)) {
    alert("Bingo");
    valid = true;
  } else {
    alert("Chúc bạn may mắn lần sau");
  }
}

//Có thể dùng indexOf()
// numbers.indexOf(numberInput) !== -1

//Dùng some(), find() với các element là object phức tạp
