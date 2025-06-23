function checkMonth() {
  const monthNumber = Number(document.getElementById("monthNumber").value);
  const resultOutput = document.getElementById("result");

  switch (monthNumber) {
    case 1:
    case 2:
    case 3:
      resultOutput.innerText = `mùa xuân`;
      break;
    case 4:
    case 5:
    case 6:
      resultOutput.innerText = `mùa hạ`;
      break;
    case 7:
    case 8:
    case 9:
      resultOutput.innerText = `mùa thu`;
      break;
    case 10:
    case 11:
    case 12:
      resultOutput.innerText = `mùa đông`;
      break;
    default:
      resultOutput.innerText = `tháng không hợp lệ`;
  }
}

document.querySelector(".btn").addEventListener("click", checkMonth);
