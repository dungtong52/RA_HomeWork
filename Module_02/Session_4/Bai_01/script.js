let nameUser = "";
let ageUser = null;
let exit = false;

while (!exit) {
  let choiceOutput = prompt(
    ">===Menu quản lý thông tin===\n" +
      "1. Nhập tên người dùng\n" +
      "2. Nhập tuổi người dùng\n" +
      "3. Kiểm tra người dùng đủ tuổi trưởng thành chưa\n" +
      "4. In ra tên viết hoa\n" +
      "5. Thoát\n" +
      "Lựa chọn của bạn:"
  );

  switch (choiceOutput) {
    case "1":
      nameUser = prompt("Nhập tên người dùng:").trim();
      alert("Đã lưu tên người dùng. Tiếp tục!");
      break;
    case "2":
      let valid = false;
      while (!valid) {
        ageUser = Number(prompt("Nhập tuổi người dùng").trim());
        if (isNaN(ageUser) || ageUser <= 0) {
          alert("Tuổi không hợp lệ. Vui lòng nhập lại.");
        } else {
          valid = true;
          alert("Đã lưu tuổi người dùng. Tiếp tục!");
        }
      }
      break;
    case "3":
      if (ageUser === null) alert("Bạn chưa nhập tuổi");
      else if (ageUser >= 18) alert("Bạn đã đủ tuổi trưởng thành.");
      else alert("Bạn CHƯA đủ tuổi trưởng thành.");
      break;
    case "4":
      if (nameUser === "") alert("Bạn chưa nhập tên");
      else alert("Tên viết hoa: " + nameUser.toUpperCase());
      break;
    case "5":
      exit = true;
      alert("Chương trình kết thúc!");
      break;
    default:
      alert("Số bạn nhập không hợp lệ");
  }
}
