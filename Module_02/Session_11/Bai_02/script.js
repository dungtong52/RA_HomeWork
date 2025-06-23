//Lấy đối tượng từ localStorage, nếu null thì đặt như dưới
let courses = JSON.parse(localStorage.getItem('courses')) || [
  {
    id: 1,
    content: 'Learn Javascript Session 01',
    dueDate: '2023-04-17',
    status: 'Pending',
    assignedTo: 'Anh Bách',
  },
  {
    id: 2,
    content: 'Learn Javascript Session 2',
    dueDate: '2023-04-17',
    status: 'Pending',
    assignedTo: 'Lâm th',
  },
  {
    id: 3,
    content: 'Learn CSS Session 1',
    dueDate: '2023-04-17',
    status: 'Pending',
    assignedTo: 'Hiếu Ci Ớt Ớt',
  },
];

const form = document.getElementById('input-container');
const content = document.getElementById('content');
const dueDate = document.getElementById('due-date');
const taskStatus = document.getElementById('status');
const username = document.getElementById('username');
const btnSubmit = document.querySelector('.btn');

const table = document.getElementById('table-list-content');
const bodyTable = document.getElementById('body-table');

let updateId = null;

//Check input valid
function isValid(content, dueDate, status, username) {
  return content !== '' && dueDate !== '' && status !== '' && username !== '';
}
// Hiển thị danh sách
function renderTable() {
  bodyTable.innerHTML = '';
  bodyTable.innerHTML = courses
    .map(
      (item) =>
        `<tr id="${item.id}">
            <td>${item.id}</td>
            <td>${item.content}</td>
            <td>${item.dueDate}</td>
            <td>${item.status}</td>
            <td>${item.assignedTo}</td>
            <td>
                <button class="btn-edit">Sửa</button>
                <button class="btn-delete">Xóa</button>
            </td>
        </tr>`
    )
    .join('');
}
renderTable();

//Gắn sự kiện vào form
form.addEventListener('submit', (e) => {
  e.preventDefault();
  const contentInput = content.value.trim();
  const dueDateInput = dueDate.value.trim();
  const statusInput = taskStatus.value.trim();
  const usernameInput = username.value.trim();

  if (!isValid(contentInput, dueDateInput, statusInput, usernameInput)) {
    alert('Bạn phải nhập đầy đủ dữ liệu');
    return;
  }

  const course = {
    id: updateId || courses.length + 1,
    content: contentInput,
    dueDate: dueDateInput,
    status: statusInput,
    assignedTo: usernameInput,
  };

  let index = courses.findIndex((c) => c.id === course.id);
  index !== -1
    ? (courses[index] = course) //UPDATE
    : courses.push(course); //ADD NEW

  localStorage.setItem('courses', JSON.stringify(courses)); //Lưu vào storage
  console.log(courses);
  btnSubmit.textContent = 'Submit';
  updateId = null;
  form.reset();
  renderTable();
});

// Gắn sự kiện update, delete
table.addEventListener('click', (e) => {
  const tr = e.target.closest('tr');
  let courseId = null;
  if (tr) courseId = +tr.getAttribute('id');
  //Button Edit
  if (e.target.classList.contains('btn-edit')) {
    const course = courses.find((c) => c.id === courseId);
    if (course) {
      content.value = course.content;
      dueDate.value = course.dueDate;
      taskStatus.value = course.status;
      username.value = course.assignedTo;
      btnSubmit.textContent = 'Update';
      updateId = course.id;
    }
  }
  //Button Delete
  if (e.target.classList.contains('btn-delete')) {
    courses = courses.filter((c) => c.id !== courseId);
    renderTable();
    console.log(courses);
    localStorage.setItem('courses', JSON.stringify(courses)); //Lưu vào storage
  }
});
