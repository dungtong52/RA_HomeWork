let studentList = [
  {
    id: 1,
    name: 'Nguyễn Văn A',
    age: 20,
    class: 'A',
  },
  {
    id: 2,
    name: 'Trần Thị B',
    age: 21,
    class: 'B',
  },
  {
    id: 3,
    name: 'Lê Văn C',
    age: 19,
    class: 'C',
  },
  {
    id: 4,
    name: 'Phạm Thị D',
    age: 22,
    class: 'A',
  },
  {
    id: 5,
    name: 'Hoàng Văn E',
    age: 20,
    class: 'B',
  },
];
let editingId = null;
const form = document.getElementById('addStudent-form');
const nameInput = document.getElementById('name');
const ageInput = document.getElementById('age');
const classInput = document.getElementById('class');
const tableBody = document.getElementById('table-body');
const btnAddStudent = document.querySelector('.btn-add');
const studentSearch = document.getElementById('student-search');

const btnAdd = document.querySelector('.btn-add');
// Kiểm tra nhập vào có hợp lệ không
function isValid(nameStudent, ageStudent, classStudent) {
  return (
    nameStudent !== '' &&
    classStudent !== '' &&
    Number.isInteger(ageStudent) &&
    ageStudent > 0
  );
}
// In danh sách sinh viên
function renderTable() {
  tableBody.innerHTML = '';
  editingId = null;
  tableBody.innerHTML = studentList
    .map(
      (student) =>
        `
        <tr id="${student.id}">
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.class}</td>
            <td class="btn-action">
                <span class="btn-edit">Sửa</span>
                <span class="btn-delete">Xóa</span>
            </td>
        </tr>
     `
    )
    .join('');
}
// Gắn sự kiện cho form
renderTable();
form.addEventListener('submit', (e) => {
  e.preventDefault();
  const student = {
    id: editingId || studentList.length + 1, //editing null thì là add new, khác null thì là edit
    name: nameInput.value,
    age: +ageInput.value,
    class: classInput.value,
  };
  if (!isValid(student.name, student.age, student.class)) {
    alert('Nhập sai định dạng. Hãy nhập lại!');
    return;
  }

  const index = studentList.findIndex((s) => s.id === student.id);
  if (index !== -1) studentList[index] = student; //update
  else studentList.push(student); //add new student

  editingId = null;
  form.reset();
  btnAddStudent.textContent = 'Thêm sinh viên';
  renderTable();
});
// Gắn sự kiện cho button xóa, sửa
tableBody.addEventListener('click', (e) => {
  const tr = e.target.closest('tr');
  let studentId = null;
  if (tr) {
    studentId = Number(tr.getAttribute('id'));
  }
  //Button Edit
  if (e.target.classList.contains('btn-edit')) {
    const student = studentList.find((s) => s.id === studentId);
    if (student) {
      nameInput.value = student.name;
      ageInput.value = student.age;
      classInput.value = student.class;
      editingId = studentId;
      btnAddStudent.textContent = 'Sửa sinh viên';
    }
  }
  //Button Delete
  if (e.target.classList.contains('btn-delete')) {
    studentList = studentList.filter((s) => s.id !== studentId);
    renderTable();
  }
});

//Tìm kiếm sinh viên
studentSearch.addEventListener('input', () => {
  const keyword = studentSearch.value.trim().toLowerCase();
  const studentFindList = studentList.filter((s) =>
    s.name.toLowerCase().includes(keyword)
  );

  tableBody.innerHTML = studentFindList
    .map(
      (student) =>
        `
        <tr id="${student.id}">
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.class}</td>
            <td class="btn-action">
                <span class="btn-edit">Sửa</span>
                <span class="btn-delete">Xóa</span>
            </td>
        </tr>
     `
    )
    .join('');
});
