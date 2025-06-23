let users = JSON.parse(localStorage.getItem('users')) || [];

const loginForm = document.getElementById('login-form');
const email = document.getElementById('email');
const password = document.getElementById('password');

const btnLogin = document.querySelector('.btn');
const passwordDisplay = document.getElementById('password-display');
const passwordHidden = document.getElementById('password-hidden');

const timeLogin = document.getElementById('time-login');

// Kiểm tra nhập hợp lệ và tồn tại
function isValid(email, password) {
  if (email !== '' && password !== '') {
    const user = users.find((u) => u.email === email);
    if (user.password === password) return true;
  }
  return false;
}

// Ẩn/Hiển thị password
passwordDisplay.addEventListener('click', () => {
  password.type = 'text';
  passwordDisplay.style.display = 'none';
  passwordHidden.style.display = 'block';
});
passwordHidden.addEventListener('click', () => {
  password.type = 'password';
  passwordDisplay.style.display = 'block';
  passwordHidden.style.display = 'none';
});
// Auto-fill nếu có thông tin đăng nhập còn hạn
window.onload = () => {
  const loggedIn = JSON.parse(localStorage.getItem('loggedInUser'));
  if (loggedIn) {
    const loginExpired = 24 * 60 * 60 * 1000;

    if (Date.now() - loggedIn.loginTime < loginExpired) {
      email.value = loggedIn.email;
      password.value = loggedIn.password;
    } else {
      alert('Hết hạn đăng nhập');
      localStorage.removeItem('loggedInUser');
    }
  }
};
// Gắn sự kiện Login
loginForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const emailInput = email.value.trim();
  const passwordInput = password.value.trim();
  if (!isValid(emailInput, passwordInput)) {
    alert('Email hoặc Password nhập vào không đúng.');
    return;
  }
  //Checkbox được tích thì sẽ lưu người dùng đang đăng nhập vào mảng loginingUser
  if (timeLogin.checked) {
    const loginUser = {
      email: emailInput,
      password: passwordInput,
      loginTime: Date.now(),
    };
    localStorage.setItem('loggedInUser', JSON.stringify(loginUser));
  } else {
    localStorage.removeItem('loggedInUser');
  }

  alert('Đăng nhập thành công');
  //Chuyển trang
  window.location.href = './index.html';
});
