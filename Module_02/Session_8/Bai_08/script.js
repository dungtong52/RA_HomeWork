let shouldContinue = true;
const bookList = [
  {
    id: 1,
    title: 'Lập Trình Java Cơ Bản',
    author: 'Nguyễn Văn A',
    year: 2020,
    price: 120000,
    isAvailable: true,
  },
  {
    id: 2,
    title: 'Học React Dễ Dàng',
    author: 'Trần Thị B',
    year: 2022,
    price: 150000,
    isAvailable: false,
  },
  {
    id: 3,
    title: 'Cấu Trúc Dữ Liệu & Giải Thuật',
    author: 'Lê Văn C',
    year: 2019,
    price: 180000,
    isAvailable: true,
  },
  {
    id: 4,
    title: 'Spring Boot Từ A Đến Z',
    author: 'Phạm Văn D',
    year: 2021,
    price: 200000,
    isAvailable: false,
  },
  {
    id: 5,
    title: 'Javascript Nâng Cao',
    author: 'Đỗ Thị E',
    year: 2023,
    price: 170000,
    isAvailable: true,
  },
];
let idInput, titleInput, authorInput, yearInput, priceInput, availableInput;
let index;

function listDisplay(arr) {
  alert(
    `Danh sách:\n` +
      arr
        .map(
          (item) =>
            `- id: ${item.id}, title: ${item.title}, author: ${item.author}, year: ${item.year}, price: ${item.price}, isAvailable: ${item.isAvailable}\n`
        )
        .join('\n')
  );
}

while (shouldContinue) {
  const choice = prompt(
    '1. Thêm sách mới.\n' +
      '2. Hiển thị danh sách sách.\n' +
      '3. Tìm kiếm sách theo tiêu đề.\n' +
      '4. Cập nhật trạng thái mượn/trả sách theo id sách.\n' +
      '5. Xóa sách theo id sách ra khỏi danh sách.\n' +
      '6. Sắp xếp sách theo giá tăng dần.\n' +
      '7. Thoát.\n' +
      'Lựa chọn của bạn:'
  );
  switch (choice) {
    case '1':
      do {
        idInput = +prompt('Nhập id của sách:').trim();
      } while (isNaN(idInput) || !Number.isInteger(idInput));
      if (bookList.some((item) => item.id === idInput)) {
        alert('Sách này đã có');
      } else {
        titleInput = prompt('Nhập tên sách: ').trim();
        authorInput = prompt('Nhập tên tác giả: ').trim();
        do {
          yearInput = +prompt('Nhập năm xuất bản: ').trim();
        } while (isNaN(yearInput) || !Number.isInteger(yearInput));
        do {
          priceInput = +prompt('Nhập giá sách: ').trim();
        } while (isNaN(priceInput) || !Number.isInteger(priceInput));
        do {
          availableInput = prompt(
            'Nhập trạng thái mượn sách: (1 = true / 0 = false)'
          );
        } while (availableInput !== '1' && availableInput !== '0');

        bookList.push({
          id: idInput,
          title: titleInput,
          author: authorInput,
          year: yearInput,
          price: priceInput,
          isAvailable: availableInput === '1',
        });
      }
      listDisplay(bookList);
      break;

    case '2':
      listDisplay(bookList);
      break;

    case '3':
      titleInput = prompt('Nhập tên sách: ');
      let bookFindArray = bookList.filter((book) =>
        book.title.toLowerCase().includes(titleInput.toLowerCase())
      );
      if (bookFindArray.length === 0) alert('Không tìm được sách');
      else listDisplay(bookFindArray);
      break;

    case '4':
      do {
        idInput = +prompt('Nhập id của sách:').trim();
      } while (isNaN(idInput) || !Number.isInteger(idInput));
      index = bookList.findIndex((item) => item.id === idInput);
      if (index === -1) {
        alert('Sách này không tồn tại');
      } else {
        do {
          availableInput = prompt(
            'Cập nhật trạng thái mượn sách: (1 = true / 0 = false)'
          );
        } while (availableInput !== '1' && availableInput !== '0');

        bookList[index].isAvailable = availableInput === '1';
        alert(
          `Đã cập nhật trạng thái:\n` +
            `- id: ${bookList[index].id}, title: ${bookList[index].title}, author: ${bookList[index].author}, year: ${bookList[index].year}, price: ${bookList[index].price}, isAvailable: ${bookList[index].isAvailable}`
        );
      }
      break;
    case '5':
      do {
        idInput = +prompt('Nhập id của sách:').trim();
      } while (isNaN(idInput) || !Number.isInteger(idInput));
      index = bookList.findIndex((item) => item.id === idInput);
      if (index === -1) {
        alert('Sách này không tồn tại');
      } else {
        bookList.splice(index, 1);
        listDisplay(bookList);
      }
      break;
    case '6':
      bookList.sort((a, b) => a.price - b.price);
      listDisplay(bookList);
      break;
    case '7':
      shouldContinue = false;
      break;
    default:
      alert('Nhập sai định dạng!');
  }
}
