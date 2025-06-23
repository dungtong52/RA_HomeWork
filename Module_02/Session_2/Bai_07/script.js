function typeTriangle() {
  const edgeA = Number(document.getElementById("edge-a").value);
  const edgeB = Number(document.getElementById("edge-b").value);
  const edgeC = Number(document.getElementById("edge-c").value);
  const checkTriangle = document.getElementById("checkTriangle");

  if (edgeA + edgeB > edgeC && edgeA + edgeC > edgeB && edgeB + edgeC > edgeA)
    checkTriangle.innerText = `Tam giác không hợp lệ.`;
  else {
    if (edgeA == edgeB && edgeB == edgeC)
      checkTriangle.innerText = `Tam giác đều`;
    else if (edgeA == edgeB || edgeB == edgeC)
      checkTriangle.innerText = `Tam giác cân`;
    else if (
      edgeA * edgeA + edgeB * edgeB == edgeC * edgeC ||
      edgeA * edgeA + edgeC * edgeC == edgeB * edgeB ||
      edgeB * edgeB + edgeC * edgeC == edgeA * edgeA
    )
      checkTriangle.innerText = `Tam giác vuông`;
    else checkTriangle.innerText = `Tam giác thường`;
  }
}

document.querySelector(".btn").addEventListener("click", typeTriangle);
