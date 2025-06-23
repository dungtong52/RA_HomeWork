const display = document.getElementById('display');
const hideBtn = document.getElementById('hide');
const showBtn = document.getElementById('show');

// Cách 1
hideBtn.addEventListener('click', () => {
  display.style.display = 'none';
});

showBtn.addEventListener('click', () => {
  display.style.display = 'block';
});

// Cách 2
const hideBtn2 = document.getElementById('hide2');
const showBtn2 = document.getElementById('show2');

hideBtn2.addEventListener('click', () => {
  display.classList.add('hidden');
});
showBtn2.addEventListener('click', () => {
  display.classList.remove('hidden');
});
