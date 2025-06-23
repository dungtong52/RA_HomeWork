let exit = false;
let checkCount = 0;
let valid;
let numberOfTest = "";

while (!exit) {
  const choiceOutput = prompt(
    "=== MENU BẢNG CỬU CHƯƠNG NÂNG CAO ===\n" +
      "1. Hiển thị bảng cửu chương từ 1 đến 9\n" +
      "2. Hiển thị bảng cửu chương của một số cụ thể\n" +
      "3. Kiểm tra kết quả phép nhân\n" +
      "4. Hiển thị bảng cửu chương ngược\n" +
      "5. Luyện tập bảng nhân với số lần tùy chọn\n" +
      "6. Thống kê kết quả đúng/sai\n" +
      "7. Thoát\n" +
      "8. Lựa chọn của bạn:\n"
  );

  switch (choiceOutput) {
    case "1":
      let multiplicationTable = "";
      for (let i = 1; i < 10; i++) {
        for (let j = 1; j < 10; j++) {
          multiplicationTable += `${i} x ${j} = ${i * j}` + ", ";
        }
        multiplicationTable += "\n";
      }
      alert(multiplicationTable);
      break;

    case "2":
      valid = false;
      let resultCase2 = "";
      while (!valid) {
        const numberInput = Number(
          prompt("Nhập số bất kỳ trong khoảng 1 đến 9: ").trim()
        );
        if (isNaN(numberInput) || numberInput < 1 || numberInput > 9) {
          alert("Số nhập vào không hợp lệ. Mời nhập lại!");
        } else {
          for (let i = 1; i < 10; i++) {
            resultCase2 += `${numberInput} x ${i} = ${numberInput * i}` + ", ";
          }
          alert(resultCase2);
          valid = true;
        }
      }
      break;

    case "3":
      valid = false;
      while (!valid) {
        const numberOne = Number(
          prompt("Nhập số bất kỳ trong khoảng (0 - 9). Số thứ nhất: ").trim()
        );
        const numberTwo = Number(
          prompt("Nhập số bất kỳ trong khoảng (0 - 9). Số thứ hai: ").trim()
        );
        const resultMutiplication = Number(
          prompt("Nhập kết quả pháp nhân: ").trim()
        );
        if (
          isNaN(numberOne) ||
          isNaN(numberTwo) ||
          isNaN(resultMutiplication) ||
          numberOne < 1 ||
          numberTwo < 1 ||
          numberOne > 9 ||
          numberTwo > 9
        ) {
          alert("Số nhập vào không hợp lệ. Mời nhập lại!");
        } else if (resultMutiplication !== numberOne * numberTwo) {
          alert("Kết quả của bạn sai rồi. Làm lại thôi!");
        } else {
          alert("Kết quả đúng rồi! Well done!");
          valid = true;
        }
      }
      break;

    case "4":
      let multiplicationTableReverse = "";
      for (let i = 9; i > 0; i--) {
        for (let j = 9; j > 0; j--) {
          multiplicationTableReverse += `${i} x ${j} = ${i * j}` + ", ";
        }
        multiplicationTableReverse += "\n";
      }
      alert(multiplicationTableReverse);
      break;

    case "5":
      valid = false;

      while (!valid) {
        numberOfTest = Number(
          prompt("Nhập số phép tính bạn muốn kiểm tra: ").trim()
        );
        if (isNaN(numberOfTest) || numberOfTest < 1) {
          alert("Số nhập vào không hợp lệ. Mời nhập lại!");
        } else {
          for (let i = 1; i <= numberOfTest; i++) {
            const numberOne = Number(
              prompt(
                "Nhập số bất kỳ trong khoảng (0 - 9). Số thứ nhất: "
              ).trim()
            );
            const numberTwo = Number(
              prompt("Nhập số bất kỳ trong khoảng (0 - 9). Số thứ hai: ").trim()
            );
            const resultMutiplication = Number(
              prompt("Nhập kết quả pháp nhân: ").trim()
            );
            if (
              isNaN(numberOne) ||
              isNaN(numberTwo) ||
              isNaN(resultMutiplication) ||
              numberOne < 1 ||
              numberTwo < 1 ||
              numberOne > 9 ||
              numberTwo > 9
            ) {
              alert("Số nhập vào không hợp lệ. Mời nhập lại!");
            } else {
              if (resultMutiplication !== numberOne * numberTwo) {
                checkCount++;
              }
            }
          }
          alert("Bạn đã làm xong! Mời kiểm tra kết quả ở phần sau.");
          valid = true;
        }
      }
      break;

    case "6":
      if (numberOfTest === "") {
        alert("Bạn chưa làm kiểm tra. Mời quay lại bước 5!");
      } else {
        alert(
          `Bạn đã làm ${numberOfTest} phép tính. Có ${checkCount} phép tính đúng, ${
            numberOfTest - checkCount
          } phép tính sai`
        );
      }
      break;

    case "7":
      exit = true;
      alert("Chương trình kết thúc!");
      break;
    default:
      alert("Số bạn nhập không hợp lệ. Mời nhập lại: ");
  }
}
