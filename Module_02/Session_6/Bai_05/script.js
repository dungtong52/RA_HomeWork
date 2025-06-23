let stringInput = prompt("Hãy nhập chuỗi ký tự: ").trim();
function isPalindrome(string) {
  let result = "là chuỗi đối xứng";
  for (let i = 0; i < Math.floor(string.length / 2); i++) {
    if (string[i] !== string[string.length - 1 - i]) {
      result = "không phải chuỗi đối xứng";
      break;
    }
  }
  return result;
}

alert(isPalindrome(stringInput));
