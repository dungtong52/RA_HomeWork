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
    "=== MENU PHÂN TÍCH SỐ NGUYÊN N ===\n" +
      "1. In tất cả các số nguyên tố từ 1 đến N\n" +
      "2. Đếm và tính tổng các số chia hết cho 3 hoặc 7 từ 1 đến N\n" +
      "3. In ra N dòng tam giác sao cân (từ 1 tới N dòng)\n" +
      "4. Kiểm tra N có phải số palindrome (đối xứng)\n" +
      "5. In ra các số có tổng chữ số là chẵn từ 1 đến N\n" +
      "6. Tìm số lớn nhất nhỏ hơn N chia hết cho cả 3 và 5\n" +
      "7. Thoát\n" +
      "8. Lựa chọn của bạn:\n"
  );

  switch (choiceOutput) {
    case "1":
      result = "";
      for (let i = 2; i <= numberN; i++) {
        count = 0;
        for (let j = 1; j <= i; j++) {
          if (i % j === 0) {
            count++;
          }
        }
        if (count === 2) {
          result += `${i}, `;
        }
      }
      alert(`Các số nguyên tố từ 1 đến ${numberN}: ${result.slice(0, -2)}`);
      break;

    case "2":
      sum = 0;
      count = 0;
      for (let i = 1; i <= numberN; i++) {
        if (i % 3 === 0 || i % 7 === 0) {
          count++;
          sum += i;
        }
      }
      alert(
        `Tổng các số chia hết cho 3 hoặc 7 từ 1 đến ${numberN}: ${sum}\n` +
          `Có ${count} số`
      );
      break;

    case "3":
      const character = "*";
      const space = "-";
      result = "";
      for (let i = 0; i <= numberN; i++) {
        result +=
          space.repeat(numberN - i) + character.repeat(2 * i + 1) + "\n";
      }
      alert(result);
      break;

    case "4":
      let numberNCopy = "";
      numberN = numberN.toString();
      let index = numberN.length;
      for (let i = 0; i < index; i++) {
        numberNCopy += numberN[index - 1 - i];
      }
      if (numberNCopy === numberN) alert(`Số ${numberN} là số Palindrome`);
      else alert(`Số ${numberN} KHÔNG PHẢI là số Palindrome`);
      break;

    case "5":
      sum = 0;
      result = "";
      for (let i = 1; i <= numberN; i++) {
        sum = 0;
        let temp = i;
        while (temp > 0) {
          sum += temp % 10;
          temp = Math.floor(temp / 10);
        }
        if (sum % 2 === 0) {
          result += `${i}` + ", ";
        }
      }
      alert(`Các số có tổng chữ số là chẵn từ 1 đến ${numberN}: ${result}`);
      break;

    case "6":
      result = Math.floor((numberN - 1) / 15) * 15;
      alert(result);
      break;

    case "7":
      exit = true;
      alert("Chương trình kết thúc!");
      break;
    default:
      alert("Số bạn nhập không hợp lệ. Mời nhập lại: ");
  }
}
