function divideArray(arr, rows) {
  if (
    Array.isArray(arr) &&
    arr.length !== 0 &&
    !isNaN(rows) &&
    Number.isInteger(rows) &&
    rows > 0
  ) {
    let resultArray = [];
    let cols = Math.ceil(arr.length / rows);
    for (let i = 0; i < rows; i++) {
      resultArray[i] = [];
      for (let j = 0; j < cols; j++) {
        resultArray[i][j] = arr[i * cols + j];
      }
    }
    return resultArray;
  }
}

function arrPrint(arr) {
  if (!Array.isArray(arr)) alert("Dữ liệu không hợp lệ");
  if (arr.length === 0) alert("Mảng không có dữ liệu");

  let result = "";
  for (let i = 0; i < arr.length; i++) {
    result += arr[i].join(" ") + "\n";
  }
  return alert(result);
}

const array1 = [1, 2, 3, 4, 5, 6, 7, 8];
const array2 = "abc";

arrPrint(divideArray(array1, 3));
arrPrint(divideArray(array1, 2));
arrPrint(divideArray(array2, 3));
