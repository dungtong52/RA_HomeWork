const backgroundDatas = [
  {
    backgroundColor: 'black',
    textColor: 'white',
    buttonBackgroundColor: 'white',
    buttonTextColor: 'black',
  },
  {
    backgroundColor: 'white',
    textColor: 'black',
    buttonBackgroundColor: 'black',
    buttonTextColor: 'white',
  },
];

const container = document.querySelector('.container');
const toggleButton = document.getElementById('btn');
let currentIndex = 0;

function toggle() {
  currentIndex = (currentIndex + 1) % backgroundDatas.length;
  const data = backgroundDatas[currentIndex];

  container.style.backgroundColor = data.backgroundColor;
  container.style.color = data.textColor;
  toggleButton.style.backgroundColor = data.buttonBackgroundColor;
  toggleButton.style.color = data.buttonTextColor;
}

toggleButton.addEventListener('click', toggle);
