let numberInput;
let arr = [];
let max;
let valid = false;

function maxInArray(array) {
  max = array[0];
  for (let i = 1; i < array.length; i++) {
    if (max < array[i]) {
      max = array[i];
    }
  }
  return max;
}

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
  maxInArray(arr);
}

alert(`${max} là số lớn nhất trong dãy số`);
