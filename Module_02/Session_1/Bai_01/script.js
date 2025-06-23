function area() {
  const widthInput = document.getElementById("width");
  const width = parseFloat(widthInput.value);
  const heightInput = document.getElementById("height");
  const height = parseFloat(heightInput.value);

  const area = width * height;
  document.getElementById("result").innerText = `Kết quả là: ${area}`;
}
