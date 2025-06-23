function exchangeMoney() {
  const EXCHANGE_RATE = 25000;
  const usdAmount = parseFloat(document.getElementById("usdCurrency").value);

  const vndAmount = usdAmount * EXCHANGE_RATE;
  document.getElementById(
    "vndAmount"
  ).innerText = `Số tiền VNĐ: ${vndAmount} VND`;
}
