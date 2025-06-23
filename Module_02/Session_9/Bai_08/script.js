const sampleDatas = [
  {
    content: 'Pay bills',
    isChecked: false,
  },
  {
    content: 'Pay bills',
    isChecked: true,
  },
];
const addBtn = document.querySelector('.addBtn');
const myUL = document.getElementById('myUL');

function renderList() {
  myUL.innerText = '';
  sampleDatas.forEach((item) => {
    const liData = document.createElement('li');
    liData.textContent = item.content;
    item.isChecked ? liData.classList.add('checked') : '';
    liData.addEventListener('click', () => {
      item.isChecked = !item.isChecked;
      item.isChecked
        ? liData.classList.add('checked')
        : liData.classList.remove('checked');
    });

    const spanData = document.createElement('span');
    spanData.textContent = 'X';
    spanData.className = 'close';
    spanData.addEventListener('click', () => {
      liData.remove();
      const index = sampleDatas.indexOf(item);
      if (index !== -1) sampleDatas.splice(index, 1);
    });

    liData.appendChild(spanData);
    myUL.appendChild(liData);
  });
}

renderList();

addBtn.addEventListener('click', () => {
  const textInput = document.getElementById('myInput').value;
  if (textInput === '') {
    alert('Không được nhập ô trống');
    return;
  }

  sampleDatas.push({
    content: textInput,
    isChecked: false,
  });

  renderList();
  document.getElementById('myInput').value = '';
});
