const keyboard = document.querySelector('.keyboard-div');
let textOutput = document.getElementById('text-output');

const stringCharacter = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
let characterArr = stringCharacter.split('');

const characterButton = document.createElement('span');
const deleteButton = `<span class="btn-delete">Xóa</span>`;
keyboard.innerHTML =
  characterArr
    .map((c) => `<span class="character-btn" id="${c}">${c}</span>`)
    .join('') + deleteButton;

keyboard.addEventListener('click', (e) => {
  // Gắn sự kiện cho nút nhập
  if (e.target.classList.contains('character-btn')) {
    const character = e.target.getAttribute('id');
    textOutput.value += character;
  }
  // Gắn sự kiện cho nút xóa
  if (e.target.classList.contains('btn-delete')) {
    textOutput.value = '';
  }
});
