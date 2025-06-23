let todos = JSON.parse(localStorage.getItem('todos')) || [];
let updateId = null;

const todoInput = document.getElementById('todo-input');
const createAndUpdateBtn = document.getElementById('btn-create-update');
const addBtn = document.querySelector('.btn-add');
const editBtn = document.querySelector('.btn-edit');

const todoList = document.getElementById('list-todo-div');

const clearAll = document.getElementById('btn-clearAll');
const total = document.getElementById('total');

// Hiển thị danh sách task (Read)
function renderlist() {
  todoList.innerHTML = '';
  todoList.innerHTML = todos
    .map(
      (t) => `
        <li id=${t.id}>
            ${t.content}
            <button class="trash-div"><i class="fa-solid fa-trash"></i></button>
        </li>
        `
    )
    .join('');
  // Cập nhật số task
  total.innerText = todos.length;
}
renderlist();

// Gắn sự kiện
//Thêm task (Create || Update)
createAndUpdateBtn.addEventListener('click', (e) => {
  e.preventDefault();
  if (todoInput.value.trim() === '') return;
  const task = {
    id: updateId || Date.now(),
    content: todoInput.value.trim(),
  };

  let index = todos.findIndex((t) => t.id === task.id);
  if (index !== -1) todos[index] = task; //Update
  else todos.push(task); //Add New
  localStorage.setItem('todos', JSON.stringify(todos)); //Save localStorage

  todoInput.value = '';
  updateId = null;
  addBtn.style.display = 'inline-block';
  editBtn.style.display = 'none';
  renderlist();
});

todoList.addEventListener('click', (e) => {
  const li = e.target.closest('li');
  const taskId = Number(li.getAttribute('id'));
  // Xóa task (Delete)
  if (e.target.classList.contains('fa-trash')) {
    todos = todos.filter((t) => t.id !== taskId);
    localStorage.setItem('todos', JSON.stringify(todos));
    renderlist();
    return;
  }
  // Edit (Update)
  if (e.target.closest('li')) {
    const taskCurrent = todos.find((t) => t.id === taskId);
    if (taskCurrent) {
      todoInput.value = taskCurrent.content;
      updateId = taskId;
      addBtn.style.display = 'none';
      editBtn.style.display = 'inline-block';
    }
  }
});

// Gắn sự kiện Clear All
clearAll.addEventListener('click', () => {
  todos = [];
  localStorage.setItem('todos', JSON.stringify(todos)); //Save localStorage
  renderlist();
});
