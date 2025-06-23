function changeDegree() {
  const degreeCelsius = parseFloat(
    document.getElementById("degreeCelsius").value
  );

  const result = (degreeCelsius * 9) / 5 + 32;
  document.getElementById("result").innerText = `Độ F: ${result}`;
}
