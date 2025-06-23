function isPrimeAndOdd(number) {
  if (!Number.isInteger(number)) return false;
  if (number < 3) return false;
  for (let i = 2; i < number; i++) {
    if (number % i === 0) return false;
  }
  return true;
}

function primeAndOddArray(arr) {
  if (!Array.isArray(arr)) return "Dữ liệu không hợp lệ";
  if (arr.length === 0) return "Mảng không có dữ liệu";

  for (let i = 0; i < arr.length; i++) {
    if (isNaN(arr[i])) return "Dữ liệu không hợp lệ";
  }

  let resultArray = [];
  for (let i = 0; i < arr.length; i++) {
    if (isPrimeAndOdd(arr[i])) {
      resultArray.push(arr[i]);
    }
  }
  return resultArray;
}

const array1 = [10, 2, 3, 5, 7, 9, 13, 21, 29];
const array2 = [];
const array3 = "abc";
const array4 = ["s", 5, 6];

alert(primeAndOddArray(array1));
alert(primeAndOddArray(array2));
alert(primeAndOddArray(array3));
alert(primeAndOddArray(array4));
