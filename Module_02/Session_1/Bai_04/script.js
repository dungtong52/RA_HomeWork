function averageScore() {
  const mathScore = parseFloat(document.getElementById("mathScore").value);
  const physicsScore = parseFloat(
    document.getElementById("physicsScore").value
  );
  const chemistryScore = parseFloat(
    document.getElementById("chemistryScore").value
  );

  let averageScore = (mathScore + physicsScore + chemistryScore) / 3;
  averageScore = averageScore.toFixed(2);
  document.getElementById(
    "result"
  ).innerText = `Điểm trung bình 3 môn: ${averageScore}`;
}
