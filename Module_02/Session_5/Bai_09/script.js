let todoListOfDiep = ["Nhậu tăng 1", "Karaoke tăng 2", "Mát trườn tăng 3"];
let exit = false;
let numberInput, textInput;
let valid = false;

while (!exit) {
  const choice = prompt(
    "1. Thêm mới 1 công việc vào danh sách\n" +
      "2. Hiển thị toàn bộ công việc trong danh sách\n" +
      "3. Cập nhật công việc trong danh sách tại vị trí diep mong muốn\n" +
      "4. Xoá một công việc tại vị trí diep mong muốn\n" +
      "5. Thoát\n" +
      "Chọn: "
  );
  switch (choice) {
    case "1":
      textInput = prompt("Nhập gì đó vào đây:");
      todoListOfDiep.push(textInput);
      break;
    case "2":
      alert(todoListOfDiep);
      break;
    case "3":
      valid = false;
      textInput = prompt("Nhập công việc muốn cập nhật:");
      while (!valid) {
        numberInput = Number(
          prompt(
            "Nhập vị trí muốn cập nhật (phải nhỏ hơn số lượng phần tử): "
          ).trim()
        );

        if (
          isNaN(numberInput) ||
          numberInput > todoListOfDiep.length ||
          numberInput < 0
        ) {
          alert("Số nhập không hợp lệ");
        } else {
          todoListOfDiep[numberInput] = textInput;
          alert(todoListOfDiep);
        }
        valid = true;
      }

      break;
    case "4":
      valid = false;
      while (!valid) {
        numberInput = Number(prompt("Nhập vị trí muốn xóa: ").trim());
        if (
          isNaN(numberInput) ||
          numberInput > todoListOfDiep.length ||
          numberInput < 0
        ) {
          alert("Số nhập không hợp lệ");
        } else {
          todoListOfDiep.splice(numberInput, 1);
          alert(todoListOfDiep);
        }

        valid = true;
      }
      break;
    case "5":
      exit = true;
      alert("Chương trình hoàn thành!");
      break;
  }
}
