function countInterger(array) {
  let count = 0;
  for (let i = 0; i < array.length; i++) {
    if (Number.isInteger(array[i]) && array[i] > 0) {
      count++;
    }
  }
  if (count === 0) return "không có số nguyên dương trong mảng";
  return count;
}

alert(`[4, 5.4, 6, -2]: ${countInterger([4, 5.4, 6, -2])}`);
alert(`[1, 2, 5, 7, -8, 6]: ${countInterger([1, 2, 5, 7, -8, 6])}`);
alert(`[1.2, -3, -6]: ${countInterger([1.2, -3, -6])}`);
