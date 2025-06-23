function interestCalculation() {
  const amountInput = Number(document.getElementById("amountInput").value);
  const monthInput = Number(document.getElementById("monthInput").value);
  const INTEREST_RATE = 4.3 / 100;

  const amountInterest = ((amountInput * INTEREST_RATE) / 12) * monthInput;

  const amountOutput = amountInterest
    .toLocaleString("vi-VN", {
      style: "currency",
      currency: "VND",
    })
    .replace("₫", "VNĐ");

  document.getElementById(
    "amountOutput"
  ).innerText = `Số tiền lãi nhận được: ${amountOutput}`;
}
