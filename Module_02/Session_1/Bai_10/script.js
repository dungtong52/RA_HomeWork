function changeVND() {
  const numberInput = document.querySelector(".numberInput").value;
  const numberChange = Number(numberInput);
  let changeVND = numberChange.toLocaleString("vi-VN", {
    style: "currency",
    currency: "VND",
  });
  changeVND = changeVND.replace("₫", "VNĐ");
  document.querySelector(".numberOutput").innerText = `Output: ${changeVND}`;
}
