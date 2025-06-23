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
//trong JS có thể dùng .sort()
function removeDuplicateElements(arr) {
  let resultArr = [];
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] !== arr[i + 1]) {
      resultArr.push(arr[i]);
    }
  }
  return resultArr;
}

let arr = [2, 1, 7, 1, 1, 8, 6, 2, 2, 7];
alert(removeDuplicateElements(sort(arr)));

arr = [4, 4, 4, 6, 1, 6, 6, 4, 4, 3];
alert(removeDuplicateElements(sort(arr)));

arr = [3, 3, 7, 7, 1, 7, 6, 7, 5, 7];
alert(removeDuplicateElements(sort(arr)));
