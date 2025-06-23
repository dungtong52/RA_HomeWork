let arr = [];
let resultArr = [];
let numberInput;
let count = 0;

function searchMax(array) {
  let max = array[0];
  for (let i = 1; i < array.length; i++) {
    if (array[i] > max) {
      max = array[i];
    }
  }
  return max;
}

const arrLength = +prompt("Nhập số phần tử của mảng: ").trim();

if (isNaN(arrLength)) {
  alert("Nhập số sai định dạng. Nhập lại!");
} else if (arrLength < 0) alert("Số lượng phần tử không được nhỏ hơn 0");
else if (arrLength === 0) alert("Mảng không có phần tử nào");
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
  let max = searchMax(arr);
  for (let i = 1; i < arr.length; i++) {
    if (arr[i] < max) {
      resultArr.push(arr[i]);
    }
  }
  let secondMax = searchMax(resultArr);
  alert(secondMax);
}
