let arr;
let resultArr;
let arrayLength, numberInput;
let exit = false;
let valid = false;
let count = 0;
let sum = 0;

while (!exit) {
  const choice = prompt(
    "================== MENU ===================\n" +
      "1. Nhập số phần tử cần nhập và giá trị các phần tử\n" +
      "2. In ra giá trị các phần tử đang quản lý\n" +
      "3. In ra giá trị các phần tử chẵn và tính tổng\n" +
      "4. In ra giá trị lớn nhất và nhỏ nhất trong mảng\n" +
      "5. In ra các phần tử là số nguyên tố trong mảng và tính tổng\n" +
      "6. Nhập vào một số và thống kê trong mảng có bao nhiêu phần tử đó\n" +
      "7. Thêm một phần từ vào vị trí chỉ định\n" +
      "8. Thoát\n" +
      "============================================" +
      "Lựa chọn của bạn: "
  );
  switch (choice) {
    case "1":
      arr = [];
      arrayLength = Number(prompt("Nhập số phần tử của mảng:").trim());
      if (isNaN(arrayLength) || arrayLength <= 0) {
        alert("Số nhập không hợp lệ.");
        break;
      } else {
        for (let i = 0; i < arrayLength; i++) {
          numberInput = Number(prompt(`Nhập số thứ ${i + 1}: `).trim());
          if (isNaN(numberInput)) {
            alert("Số nhập không hợp lệ.");
            break;
          } else {
            arr.push(numberInput);
          }
        }
      }
      break;

    case "2":
      if (arr.length === 0) {
        alert("Bạn chưa nhập phần tử mảng!");
      } else alert(arr);
      break;

    case "3":
      if (arr.length === 0) {
        alert("Bạn chưa nhập phần tử mảng!");
      } else {
        resultArr = [];
        sum = 0;
        for (let i = 0; i < arr.length; i++) {
          if (arr[i] % 2 === 0) {
            resultArr.push(arr[i]);
            sum += arr[i];
          }
        }
        alert(`Các phần tử chẵn: ${resultArr}; Tổng các phần tử chẵn: ${sum}`);
      }

      break;
    case "4":
      if (arr.length === 0) {
        alert("Bạn chưa nhập phần tử mảng!");
      } else {
        let max = arr[0];
        let min = arr[0];
        for (let i = 1; i < arr.length; i++) {
          if (arr[i] > max) {
            max = arr[i];
          }
          if (arr[i] < min) {
            min = arr[i];
          }
        }
        alert(`Số lớn nhất trong mảng: ${max}; Số nhỏ nhất trong mảng: ${min}`);
      }
      break;
    case "5":
      if (arr.length === 0) {
        alert("Bạn chưa nhập phần tử mảng!");
      } else {
        resultArr = [];
        sum = 0;
        for (let i = 0; i < arr.length; i++) {
          let isPrime = true;
          if (arr[i] < 2) isPrime = false;
          else
            for (let j = 2; j <= Math.sqrt(arr[i]); j++) {
              if (arr[i] % j === 0) {
                isPrime = false;
                break;
              }
            }
          if (isPrime) {
            resultArr.push(arr[i]);
            sum += arr[i];
          }
        }
        alert(
          `Các số nguyên tố trong mảng: ${resultArr}, tổng của chúng là: ${sum}`
        );
      }
      break;

    case "6":
      valid = false;
      count = 0;
      while (!valid) {
        numberInput = Number(prompt("Nhập 1 số bất kỳ:").trim());
        if (isNaN(numberInput)) {
          alert("Số nhập không hợp lệ");
        } else {
          for (let i = 0; i < arr.length; i++) {
            if (arr[i] === numberInput) {
              count++;
            }
          }
          alert(`Có ${count} phần tử ${numberInput} trong mảng`);
          valid = true;
        }
      }
      break;

    case "7":
      valid = false;
      while (!valid) {
        let index = Number(prompt("Nhập vị trí muốn thêm phần tử: ").trim());
        numberInput = Number(prompt("Nhập số muốn thêm vào mảng:").trim());
        if (
          isNaN(index) ||
          index > arr.length ||
          index < 0 ||
          isNaN(numberInput)
        ) {
          alert("Số nhập không hợp lệ");
        } else {
          arr.splice(index, 0, numberInput);
          alert(arr);
          valid = true;
        }
      }
      break;

    case "8":
      exit = true;
      alert("Chương trình hoàn thành!");
      break;
  }
}
