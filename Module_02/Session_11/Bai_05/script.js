let bookmarkList = JSON.parse(localStorage.getItem('bookmarkList')) || [];
let updateId = null;

const addBtn = document.querySelector('.btn-add');
const saveBtn = document.querySelector('.btn-save');
const editBtn = document.querySelector('.btn-edit');
const popup = document.getElementById('popUp-bookmark-container');
const bookmarkDetailInput = document.querySelector('.bookmark-detail-input');
const bookmarkListContanier = document.getElementById('list-container');
const popupClose = document.getElementById('popup-close');
const overlay = document.querySelector('.overlay');

const websiteName = document.getElementById('website-name-input');
const websiteUrl = document.getElementById('website-url');

// Gắn sự kiện cho nút add bookmark
addBtn.addEventListener('click', () => {
  popup.classList.remove('hidden');
  overlay.classList.remove('hidden');
});
// Gắn sự kiện button close popup
popupClose.addEventListener('click', () => {
  popup.classList.add('hidden');
  overlay.classList.add('hidden');
});
// Gắn sự kiện click bất kỳ ngoài popup thì popup close
overlay.addEventListener('click', () => {
  popup.classList.add('hidden');
  overlay.classList.add('hidden');
});
// render list bookmark
function renderList() {
  bookmarkListContanier.innerHTML = '';
  bookmarkListContanier.innerHTML = bookmarkList
    .map(
      (b) => `
        <div class="bookmark-detail-div" id="${b.id}">
            <img
                src="${b.favicon}"
                alt="${b.name}" />
            <p>${b.name}</p>
            <i class="fas fa-edit edit-btn"></i>
            <i class="fa-solid fa-x delete-btn"></i>
        </div>
        `
    )
    .join('');
  console.log(bookmarkList);
}
renderList();

// Thêm mới || cập nhật bookmark
bookmarkDetailInput.addEventListener('submit', (e) => {
  e.preventDefault();
  const url = websiteUrl.value.trim();
  const favicon = `https://www.google.com/s2/favicons?domain=${url}&sz=64`;
  if (websiteName.value.trim() === '' || websiteUrl.value.trim() === '') {
    alert('Không được để trống dữ liệu');
    return;
  }
  const bookmark = {
    id: updateId || Date.now(),
    name: websiteName.value.trim(),
    url: url,
    favicon: favicon,
  };

  let index = bookmarkList.findIndex((b) => b.id === bookmark.id);
  if (index !== -1) bookmarkList[index] = bookmark; // Update
  else bookmarkList.push(bookmark); // Add new
  localStorage.setItem('bookmarkList', JSON.stringify(bookmarkList)); // update in localStorage

  updateId = null;
  bookmarkDetailInput.reset();
  alert('Them ok');
  popup.classList.add('hidden');
  overlay.classList.add('hidden');
  renderList();
});

// Update || Delete bookmark
bookmarkListContanier.addEventListener('click', (e) => {
  e.stopPropagation();
  const bookmarkCard = e.target.closest('.bookmark-detail-div');
  console.log(bookmarkCard);
  const bookmarkId = +bookmarkCard.getAttribute('id');

  const bookmark = bookmarkList.find((b) => b.id === bookmarkId);

  //Delete bookmark
  if (e.target.classList.contains('delete-btn')) {
    bookmarkList = bookmarkList.filter((b) => b.id !== bookmarkId);
    console.log(bookmarkList);
    localStorage.setItem('bookmarkList', JSON.stringify(bookmarkList)); // update in localStorage
    renderList();
    return;
  }
  // Update
  if (bookmark && e.target.classList.contains('edit-btn')) {
    popup.classList.remove('hidden');
    overlay.classList.remove('hidden');
    editBtn.classList.remove('hidden');
    saveBtn.classList.add('hidden');
    websiteName.value = bookmark.name;
    websiteUrl.value = bookmark.url;
    updateId = bookmark.id;
    return;
  }
  //Access url
  window.open(`${bookmark.url}`, '_blank');
});
