function colorRandom() {
  const hexCharacters = "0123456789ABCDEF";
  let color = "#";
  for (let i = 0; i < 6; i++) {
    color += hexCharacters[Math.floor(Math.random() * 16)];
  }
  return color;
}
function colorize(text, color) {
  console.log(`%c${text}`, `color: ${color}`);
}
for (let i = 0; i < 10; i++) {
  colorize("Màu sắc đã được thay đổi", colorRandom());
}
