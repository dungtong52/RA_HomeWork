const users = [
  {
    username: 'huanrose@gmail.com',
    password: '123456',
  },
];

function loginForm(event) {
  event.preventDefault();

  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  const hasUser = users.some((item) => {
    return item.username === username && item.password === password;
  });
  const display = document.getElementById('display');
  if (hasUser) display.innerText = 'Đăng nhập thành công';
  else display.innerText = 'Đăng nhập KHÔNG thành công';
}

document.getElementById('btn').addEventListener('click', loginForm);
