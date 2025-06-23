let arr = [];
let numberInput;
let count = 0;

const arrLength = +prompt("Nhập số phần tử của mảng: ").trim();

if (isNaN(arrLength)) {
  alert("Nhập số sai định dạng. Nhập lại!");
} else if (arrLength < 0) alert("Số lượng phần tử không hợp lệ");
else if (arrLength === 0) alert("Mảng không có phần tử");
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

  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < 0) {
      count++;
    }
  }
  alert(count);
}
