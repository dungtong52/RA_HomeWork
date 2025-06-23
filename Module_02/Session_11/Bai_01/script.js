//Lấy đối tượng từ localStorage, nếu null thì đặt như dưới
let users = JSON.parse(localStorage.getItem('users')) || [];
const loginForm = document.getElementById('login-form');
const email = document.getElementById('email');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm-password');

const letter = document.getElementById('letter');
const capital = document.getElementById('capital');
const character = document.getElementById('character');
const number = document.getElementById('number');
const length = document.getElementById('length');
const msgPassword = document.getElementById('msg-psw');

const msgEmail = document.getElementById('msg-email');

const msg = document.getElementById('msg');
const empty = document.getElementById('empty');
const pswConfirm = document.getElementById('psw-confirm');

// Kiểm tra tính hợp lệ
function valid(email, password, confirmPassword) {
  if (email !== '' && password !== '' && confirmPassword !== '') {
    empty.classList.remove('invalid');
    empty.classList.add('valid');
  } else {
    empty.classList.add('invalid');
    empty.classList.remove('valid');
  }
  if (
    password !== '' &&
    confirmPassword !== '' &&
    password === confirmPassword
  ) {
    pswConfirm.classList.remove('invalid');
    pswConfirm.classList.add('valid');
  } else {
    pswConfirm.classList.add('invalid');
    pswConfirm.classList.remove('valid');
  }
  msg.style.display = 'block';
  return email && password && confirmPassword && password === confirmPassword;
}
// Kiểm tra password nhập
function checkPassword(password) {
  const minLength = 8;
  const hasUpperCase = /[A-Z]/.test(password);
  const hasLowerCase = /[a-z]/.test(password);
  const hasNumber = /[0-9]/.test(password);
  const hasSpecialChar = /[^A-Za-z0-9\s]/.test(password);

  if (password.length >= minLength) {
    length.classList.remove('invalid');
    length.classList.add('valid');
  } else {
    length.classList.add('invalid');
    length.classList.remove('valid');
  }
  if (hasUpperCase) {
    capital.classList.remove('invalid');
    capital.classList.add('valid');
  } else {
    capital.classList.add('invalid');
    capital.classList.remove('valid');
  }
  if (hasLowerCase) {
    character.classList.remove('invalid');
    character.classList.add('valid');
  } else {
    character.classList.add('invalid');
    character.classList.remove('valid');
  }
  if (hasNumber) {
    number.classList.remove('invalid');
    number.classList.add('valid');
  } else {
    number.classList.add('invalid');
    number.classList.remove('valid');
  }
  if (hasSpecialChar) {
    letter.classList.remove('invalid');
    letter.classList.add('valid');
  } else {
    letter.classList.add('invalid');
    letter.classList.remove('valid');
  }
  return (
    password.length > minLength &&
    hasUpperCase &&
    hasLowerCase &&
    hasNumber &&
    hasSpecialChar
  );
}
// Kiểm tra email nhập
function checkEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const validateEmail = emailRegex.test(email);

  msgEmail.style.display = validateEmail ? 'none' : 'block';
  return validateEmail;
}

// click vào ô password thì hiện ra
password.onfocus = function () {
  msgPassword.style.display = 'block';
};

// Sự kiện submit form
loginForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const emailInput = email.value.trim();
  const passwordInput = password.value.trim();
  const confirmPasswordInput = confirmPassword.value.trim();
  const isValid = valid(emailInput, passwordInput, confirmPasswordInput);
  const isPasswordValid = checkPassword(passwordInput);
  const isEmailValid = checkEmail(emailInput);

  if (isValid && isPasswordValid && isEmailValid) {
    //Kiểm tra email tồn tại
    const isDuplicate = users.some((u) => u.email === emailInput);
    if (isDuplicate) {
      alert('Email này đã được đăng ký!');
      return;
    }

    const user = {
      email: emailInput,
      password: passwordInput,
    };

    users.push(user);
    localStorage.setItem('users', JSON.stringify(users)); //Lưu lại vào storage
    console.log('Đã lưu vào localStorage:', users);
    alert('Đăng ký thành công!');

    loginForm.reset();

    // Ẩn các thông báo sau khi submit thành công
    msg.style.display = 'none';
    msgPassword.style.display = 'none';
    msgEmail.style.display = 'none';

    //// Reset trạng thái class của các điều kiện
    [letter, capital, character, number, length, empty, pswConfirm].forEach(
      (item) => {
        item.classList.remove('valid', 'invalid');
      }
    );
  }
});
