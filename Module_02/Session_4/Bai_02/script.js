let nameStudent = "";
let ageStudent = null;
let mathScore,
  literatureScore,
  englishScore = null;
let averageScore = null;
let performance = "";
let exit = false;
let valid;

while (!exit) {
  let choiceOutput = prompt(
    "===Menu quản lý thông tin===\n" +
      "1. Nhập tên học sinh\n" +
      "2. Nhập tuổi học sinh\n" +
      "3. Nhập điểm Toán, Văn, Anh\n" +
      "4. Tính điểm trung bình và xếp loại\n" +
      "5. Hiển thị thông tin học sinh\n" +
      "6. Thoát\n" +
      "Lựa chọn của bạn:"
  );

  switch (choiceOutput) {
    case "1":
      nameStudent = prompt("Nhập tên học sinh:").trim();
      alert("Đã lưu tên học sinh. Tiếp tục!");
      break;
    case "2":
      valid = false;
      while (!valid) {
        ageStudent = Number(prompt("Nhập tuổi học sinh:").trim());
        if (isNaN(ageStudent) || ageStudent <= 0) {
          alert("Tuổi không hợp lệ. Vui lòng nhập lại.");
        } else {
          valid = true;
          alert("Đã lưu tuổi học sinh. Tiếp tục!");
        }
      }
      break;
    case "3":
      valid = false;
      while (!valid) {
        mathScore = Number(prompt("Nhập điểm Toán: ").trim());
        literatureScore = Number(prompt("Nhập điểm Văn: ").trim());
        englishScore = Number(prompt("Nhập điểm Anh: ").trim());
        if (
          isNaN(mathScore) ||
          mathScore < 0 ||
          mathScore > 10 ||
          isNaN(literatureScore) ||
          literatureScore < 0 ||
          literatureScore > 10 ||
          isNaN(englishScore) ||
          englishScore < 0 ||
          englishScore > 10
        ) {
          alert("Có điểm nhập sai. Vui lòng nhập lại toàn bộ 3 điểm.");
        } else {
          valid = true;
          alert("Đã lưu điểm của học sinh. Tiếp tục!");
        }
      }

      break;
    case "4":
      if (
        mathScore === null ||
        literatureScore === null ||
        englishScore === null
      ) {
        alert("Bạn chưa nhập đủ điểm!");
      } else {
        averageScore = (mathScore + literatureScore + englishScore) / 3;
        averageScore = Number(averageScore).toFixed(2);
        if (averageScore >= 8.5) performance = "Giỏi";
        else if (averageScore >= 7) performance = "Khá";
        else if (averageScore >= 5) performance = "Trung bình";
        else performance = "Yếu";
        alert("Đã tính điểm trung bình và học lực của học sinh. Tiếp tục!");
      }
      break;
    case "5":
      if (nameStudent === "") {
        alert("Bạn chưa nhập tên!");
      } else if (ageStudent === null) {
        alert("Bạn chưa nhập tuổi!");
      } else if (
        mathScore === null ||
        literatureScore === null ||
        englishScore === null
      ) {
        alert("Bạn chưa nhập đủ điểm!");
      } else {
        alert(
          "Tên học sinh: " +
            nameStudent +
            "\n" +
            "Tuổi học sinh: " +
            ageStudent +
            "\n" +
            "Điểm Toán, Văn, Anh: " +
            mathScore +
            ", " +
            literatureScore +
            ", " +
            englishScore +
            "\n" +
            "Điểm trung bình: " +
            averageScore +
            "\n" +
            "Học lực: " +
            performance
        );
      }
      break;
    case "6":
      exit = true;
      alert("Chương trình kết thúc!");
      break;
    default:
      alert("Số bạn nhập không hợp lệ. Mời nhập lại: ");
  }
}
