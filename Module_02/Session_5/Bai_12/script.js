let arr = [];
let indexArr = [];
let numberInput;
let arrLength = +prompt("Nhập số phần tử của mảng: ").trim();
if (isNaN(arrLength) || arrLength < 0) {
  alert("Nhập số sai định dạng. Nhập lại!");
} else if (arrLength === 0) alert("Không có số lớn nhất");
else {
  for (let i = 0; i < arrLength; i++) {
    numberInput = +prompt(`Nhập số thứ ${i + 1}: `).trim();
    if (isNaN(numberInput)) {
      alert("Nhập số sai định dạng. Nhập lại!");
      i--;
    } else {
      arr[i] = numberInput;
    }
  }
  let max = arr[0];
  for (let i = 1; i < arr.length; i++) {
    if (arr[i] > max) {
      max = arr[i];
    }
  }
  for (let i = 1; i < arr.length; i++) {
    if (arr[i] === max) {
      indexArr.push(i);
    }
  }
  alert(`Số lớn nhất: ${max}\n Vị trí: ${indexArr.join(", ")}`);
}
//Nếu dùng indexOf thì chỉ tìm được 1 vị trí đầu tiên
