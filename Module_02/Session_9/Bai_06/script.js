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

const myUL = document.getElementById('myUL');
sampleDatas.forEach((item) => {
  const liData = document.createElement('li');
  liData.innerHTML = `${item.content} <span class="close">X</span>`;
  item.isChecked ? liData.classList.add('checked') : '';
  myUL.appendChild(liData);
});
