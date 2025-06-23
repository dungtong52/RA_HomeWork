let arr = [];
let resultArr = [];
let numberInput;

while (arr.length < 6) {
  for (let i = 0; i < 6; i++) {
    numberInput = +prompt(`Nhập số thứ ${i + 1}: `).trim();
    if (isNaN(numberInput)) {
      alert("Nhập số sai định dạng. Nhập lại!");
      i--;
    } else {
      arr[i] = numberInput;
    }
  }
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] > 10) {
      resultArr.push(arr[i]);
    }
  }

  if (resultArr.length === 0) {
    alert("Không có số nào lớn hơn 10");
  } else alert(resultArr.join(", "));
}
