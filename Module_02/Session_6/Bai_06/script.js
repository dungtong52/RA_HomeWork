function firstLetterUpperCase(string) {
  let stringInput = string.toLowerCase();
  let arrString = stringInput.split("");

  arrString[0] = arrString[0].toUpperCase();
  for (let i = 1; i < arrString.length - 1; i++) {
    if (arrString[i] == " ") {
      arrString[i + 1] = arrString[i + 1].toUpperCase();
    }
  }
  return arrString.join("");
}

let textInput = prompt("Nhập chuỗi: ");

let stringOut = firstLetterUpperCase(textInput);
alert(stringOut);

