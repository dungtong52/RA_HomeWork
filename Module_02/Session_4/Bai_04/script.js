let numberN = "";
let exit = false;
let result;
let count = 0;
let sum = 0;

do {
  numberN = Number(prompt("Hãy nhập số nguyên dương N").trim());
} while (numberN <= 0 || isNaN(numberN));

while (!exit) {
  const choiceOutput = prompt(
    "=== MENU BẢNG CỬU CHƯƠNG NÂNG CAO ===\n" +
      "(N là số nguyên dương bạn nhập ban đầu)\n" +
      "1. Kiểm tra N có phải số nguyên tố\n" +
      "2. Tính tổng các số từ 1 đến N\n" +
      "3. Tính giai thừa của N (N!)\n" +
      "4. In bảng nhân của N\n" +
      "5. Đếm số ước của N\n" +
      "6. Kiểm tra N có phải số hoàn hảo\n" +
      "7. In tất cả số chia hết cho 3 và 5 từ 1 đến N\n" +
      "8. In tất cả số lẻ từ 1 đến N\n" +
      "9. Thoát\n" +
      "10. Lựa chọn của bạn:\n"
  );

  switch (choiceOutput) {
    case "1":
      for (let i = 1; i <= numberN; i++) {
        if (numberN % i === 0) {
          count++;
        }
      }
      if (count === 2) {
        alert(`Số ${numberN} là số nguyên tố`);
      } else alert(`Số ${numberN} KHÔNG PHẢI là số nguyên tố`);
      break;

    case "2":
      for (let i = 1; i <= numberN; i++) {
        sum += i;
      }
      alert(`Tổng từ 1 đến ${numberN} là ${sum}`);
      break;

    case "3":
      let factorial = 1;
      for (let i = 1; i <= numberN; i++) {
        factorial *= i;
      }
      alert(`${numberN}! = ${factorial}`);
      break;

    case "4":
      result = "";
      for (let i = 1; i <= 9; i++) {
        result += `${numberN} x ${i} = ${numberN * i}` + ", ";
      }
      alert(`Bảng nhân: ${result}`);
      break;

    case "5":
      count = 0;
      for (let i = 1; i <= numberN; i++) {
        if (numberN % i === 0) {
          count++;
        }
      }
      alert(`Số ước của ${numberN}: ${count}`);
      break;

    case "6":
      sum = 0;
      for (let i = 1; i < numberN; i++) {
        if (numberN % i === 0) {
          sum += i;
        }
      }
      if (sum === numberN) {
        alert(`Số ${numberN} là số hoàn hảo`);
      } else alert(`Số ${numberN} KHÔNG PHẢI là số hoàn hảo`);
      break;

    case "7":
      result = "";
      for (let i = 1; i <= numberN; i++) {
        if (i % 15 === 0) {
          result += `${i}` + ", ";
        }
      }
      alert(`Các số chia hết cho 3 và 5 từ 1 đến ${numberN}: ${result}`);
      break;

    case "8":
      result = "";
      for (let i = 1; i <= numberN; i++) {
        if (i % 2 !== 0) {
          result += `${i}` + ", ";
        }
      }
      alert(`Các số lẻ từ 1 đến ${numberN}: ${result}`);
      break;

    case "9":
      exit = true;
      alert("Chương trình kết thúc!");
      break;
    default:
      alert("Số bạn nhập không hợp lệ. Mời nhập lại: ");
  }
}
