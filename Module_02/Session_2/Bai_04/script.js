function convertToText() {
  const numberInput = Number(document.getElementById("numberInput").value);
  const result = document.getElementById("textOutput");

  switch (numberInput) {
    case 0:
      result.innerText = `Số Không`;
      break;
    case 1:
      result.innerText = `Số Một`;
      break;
    case 2:
      result.innerText = `Số Hai`;
      break;
    case 3:
      result.innerText = `Số Ba`;
      break;
    case 4:
      result.innerText = `Số Bốn`;
      break;
    case 5:
      result.innerText = `Số Năm`;
      break;
    case 6:
      result.innerText = `Số Sáu`;
      break;
    case 7:
      result.innerText = `Số Bảy`;
      break;
    case 8:
      result.innerText = `Số Tám`;
      break;
    case 9:
      result.innerText = `Số Chín`;
      break;
  }
}

document.getElementById("btn").addEventListener("click", convertToText);
