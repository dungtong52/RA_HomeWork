const students = [
  { name: 'Trần Trí Dương', scores: { math: 9, english: 8, literature: 7 } },
  { name: 'Hà Bích Ngọc', scores: { math: 3, english: 2, literature: 5 } },
  { name: 'Bùi Thái Sơn', scores: { math: 9.5, english: 9, literature: 9 } },
  { name: 'Lê Minh Tuấn', scores: { math: 6.5, english: 7, literature: 6 } },
  { name: 'Phạm Thu Hà', scores: { math: 10, english: 9.5, literature: 9 } },
];

const result = students.filter((student) => {
  const scoresArray = Object.values(student.scores);
  const total = scoresArray.reduce((sum, score) => sum + score, 0);
  const average = total / scoresArray.length;
  return average >= 8;
});

console.log(result);
