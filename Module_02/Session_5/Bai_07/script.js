function sort(arr) {
  let arrClone = [...arr];
  let temp;
  for (let i = 0; i < arrClone.length - 1; i++) {
    for (let j = 0; j < arrClone.length - 1 - i; j++) {
      if (arrClone[j] > arrClone[j + 1]) {
        temp = arrClone[j];
        arrClone[j] = arrClone[j + 1];
        arrClone[j + 1] = temp;
      }
    }
  }
  return arrClone;
}

let arr = [2, 5, 7, 4, 1, 8, 6, 2, 5, 7];
alert(sort(arr));

arr = [4, 8, 7, 8, 1, 9, 6, 9, 15, 3];
alert(sort(arr));

arr = [3, 9, 7, 4, 1, 8, 6, 2, 5, 7];
alert(sort(arr));
