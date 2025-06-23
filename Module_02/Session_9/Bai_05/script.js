const container = document.querySelector('.container');
const colorContainer = document.querySelector('.color-container');

const colors = ['green', 'red', 'purple', 'blue', 'yellow', 'pink'];

colors.forEach((item) => {
  const newElement = document.createElement('span');
  newElement.textContent = item;
  newElement.style.backgroundColor = item;
  newElement.style.padding = '20px';
  newElement.style.marginRight = '10px';
  newElement.style.color = 'white';
  newElement.addEventListener('click', () => {
    container.style.backgroundColor = item;
  });
  colorContainer.appendChild(newElement);
});
