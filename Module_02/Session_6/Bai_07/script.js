function squareArray(arr) {
  if (!Array.isArray(arr)) return "Dữ liệu không hợp lệ";
  if (arr.length === 0) return "Mảng không có dữ liệu";

  for (let i = 0; i < arr.length; i++) {
    if (isNaN(arr[i])) return "Dữ liệu không hợp lệ";
  }

  let resultArray = [];
  for (let i = 0; i < arr.length; i++) {
    resultArray.push(Math.pow(arr[i], 2));
  }
  return resultArray;
}

const array1 = [2, 5, 10];
const array2 = [];
const array3 = "abc";
const array4 = ["s", 5, 6];

alert(squareArray(array1));
alert(squareArray(array2));
alert(squareArray(array3));
alert(squareArray(array4));
