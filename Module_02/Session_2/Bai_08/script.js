function solveQuadratic() {
  const numberA = Number(document.getElementById("number-a").value);
  const numberB = Number(document.getElementById("number-b").value);
  const numberC = Number(document.getElementById("number-c").value);

  const result = document.getElementById("quadratic");

  const delta = numberB * numberB - 4 * numberA * numberC;
  let root1, root2;

  if (numberA == 0) {
    if (numberB == 0) {
      if (numberC == 0) result.innerText = `Phương trình vô số nghiệm`;
      else result.innerText = `Phương trình vô nghiệm`;
    } else {
      root1 = -(numberC / numberB);
      result.innerText = `Phương trình có nghiệm duy nhất: ${root1}`;
    }
  } else {
    if (delta < 0) result.innerText = `Phương trình vô nghiệm`;
    else if (delta == 0) {
      root1 = -(numberB / (2 * numberA));
      result.innerText = `Phương trình có nghiệm kép: ${root1}`;
    } else {
      root1 = (-numberB + Math.sqrt(delta)) / (2 * numberA);
      root2 = (-numberB - Math.sqrt(delta)) / (2 * numberA);
      result.innerText = `Phương trình có nghiệm: x1 = ${root1} và x2 = ${root2}`;
    }
  }
}

document.querySelector(".btn").addEventListener("click", solveQuadratic);
