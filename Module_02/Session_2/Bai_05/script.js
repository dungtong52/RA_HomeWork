function checkLeapYear() {
  const yearInput = Number(document.getElementById("yearInput").value);
  const textOutput = document.getElementById("result");

  if ((yearInput % 4 == 0 && yearInput % 100 != 0) || yearInput % 400 == 0) {
    textOutput.innerText = `Năm ${yearInput} là năm nhuận`;
  } else textOutput.innerText = `Năm ${yearInput} KHÔNG PHẢI là năm nhuận`;
}

document.getElementById("btn").addEventListener("click", checkLeapYear);
