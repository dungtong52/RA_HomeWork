const employees = [];
let shouldContinue = true;
let idInput, nameInput, positionInput, salaryInput;
let index;

while (shouldContinue) {
  const choice = prompt(
    '1. Thêm nhân viên mới.\n' +
      '2. Xóa nhân viên theo id.\n' +
      '3. Cập nhật mức lương của nhân viên theo id.\n' +
      '4. Tìm kiếm nhân viên theo tên.\n' +
      '5. Thoát\n' +
      'Lựa chọn của bạn:'
  );

  switch (choice) {
    case '1':
      idInput = prompt('Hãy nhập id của nhân viên: ').trim();
      index = employees.findIndex((employee) => employee.id === idInput);
      if (index !== -1) alert('Nhân viên này đã tồn tại');
      else {
        nameInput = prompt('Nhập tên nhân viên:').trim();
        positionInput = prompt('Nhập vị trí nhân viên:').trim();
        salaryInput = +prompt('Nhập lương nhân viên:').trim();
        if (isNaN(salaryInput) || salaryInput < 0) {
          alert('Nhập sai định dạng');
          break;
        }
        employees.push({
          id: idInput,
          name: nameInput,
          position: positionInput,
          salary: salaryInput,
        });
      }

      alert('Danh sách nhân viên hiện tại:');
      alert(
        employees
          .map(
            (item) =>
              `id: ${item.id}, name: ${item.name}, position: ${item.position}, salary: ${item.salary}`
          )
          .join('\n')
      );
      break;

    case '2':
      idInput = prompt('Hãy nhập id của nhân viên: ');
      index = employees.findIndex((employee) => employee.id === idInput);
      if (index === -1) alert('Nhân viên này không tồn tại');
      else {
        const deleteChoice = prompt(
          'Bạn có muốn xóa không? Nhấn 1 nếu có xóa, 0 nếu không xóa'
        );
        if (deleteChoice === '1') {
          employees.splice(index, 1);
          alert('Danh sách nhân viên hiện tại:');
          alert(
            employees
              .map(
                (item) =>
                  `id: ${item.id}, name: ${item.name}, position: ${item.position}, salary: ${item.salary}`
              )
              .join('\n')
          );
        } else if (deleteChoice !== '0') {
          alert('Nhập sai định dạng!');
        }
      }
      break;

    case '3':
      idInput = prompt('Hãy nhập id của nhân viên: ').trim();
      index = employees.findIndex((employee) => employee.id === idInput);
      if (index === -1) alert('Nhân viên này không tồn tại');
      else {
        salaryInput = +prompt('Nhập lương nhân viên:').trim();
        if (isNaN(salaryInput) || salaryInput < 0) {
          alert('Nhập sai định dạng');
          break;
        }
        employees[index].salary = salaryInput;
        alert(
          `Đã cập nhật lương cho nhân viên:\n` +
            `id: ${employees[index].id}, name: ${employees[index].name}, position: ${employees[index].position}, salary: ${employees[index].salary}`
        );
      }
      break;

    case '4':
      nameInput = prompt('Hãy nhập tên của nhân viên: ');
      const nameList = employees.filter((item) => item.name === nameInput);
      if (nameList.length === 0) {
        alert('Không tìm thấy nhân viên nào');
      } else {
        alert(
          nameList
            .map(
              (item) =>
                `id: ${item.id}, name: ${item.name}, position: ${item.position}, salary: ${item.salary}`
            )
            .join('\n')
        );
      }
      break;

    case '5':
      shouldContinue = false;
      break;

    default:
      alert('Nhập sai định dạng!');
  }
}
