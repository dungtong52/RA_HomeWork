document.getElementById("btn").addEventListener("click", academicRanking);

function averageScore() {
  const mathScore = parseInt(document.getElementById("mathScore").value);
  const literatureScore = parseInt(
    document.getElementById("literatureScore").value
  );
  const englishScore = parseInt(document.getElementById("englishScore").value);

  return ((mathScore + literatureScore + englishScore) / 3).toFixed(2);
}

function academicRanking() {
  const average = averageScore();
  const result = document.getElementById("result");

  if (average >= 8)
    result.innerHTML = `Điểm trung bình: <b>${average}</b>. Xếp loại học lực: <b>Giỏi</b>`;
  if (average >= 6.5 && average <= 7.9)
    result.innerText = `Điểm trung bình: <b>${average}</b>. Xếp loại học lực: <b>Khá</b>`;
  if (average >= 5 && average <= 6.4)
    result.innerText = `Điểm trung bình: <b>${average}</b>. Xếp loại học lực: <b>Trung bình</b>`;
  if (average < 5)
    result.innerText = `Điểm trung bình: <b>${average}</b>. Xếp loại học lực: <b>Yếu</b>`;
}
