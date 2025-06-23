function upperCase() {
  const nameInput = document.getElementById("textInput").value;

  const upperName = nameInput.toUpperCase();
  document.getElementById("result").innerText = `${upperName}`;
}
