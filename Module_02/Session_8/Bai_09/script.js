let shouldContinue = true;
let products = [
  {
    id: 1,
    name: 'mèn mén',
    price: 20000,
    quantity: 20,
    category: 'món ăn dân tộc Mông',
  },
  {
    id: 2,
    name: 'mứt',
    price: 80000,
    quantity: 21,
    category: 'món ăn dân tộc Kinh',
  },
  {
    id: 3,
    name: 'cơm lam',
    price: 40000,
    quantity: 15,
    category: 'món ăn dân tộc Mông',
  },
  {
    id: 4,
    name: 'bánh đậu xanh',
    price: 60000,
    quantity: 30,
    category: 'món ăn dân tộc Kinh',
  },
];
let cart = [];
let idInput, quantityInput;
let indexInProducts, indexInCart, categoryInput;

function listDisplay(arr) {
  alert(
    `Danh sách:\n` +
      arr
        .map(
          (item) =>
            `- id: ${item.id}, name: ${item.name}, price: ${item.price}, quantity: ${item.quantity}, category: ${item.category}\n`
        )
        .join('\n')
  );
}
function printByProduct(arr, category) {
  let newArr = arr.filter((item) => item.category.includes(category));
  listDisplay(newArr);
}

while (shouldContinue) {
  const choice = prompt(
    '1. Hiển thị các sản phẩm theo tên danh mục.\n' +
      '2. Chọn sản phẩm để mua bằng cách nhập id sản phẩm.\n' +
      '3. Sắp xếp các sản phẩm trong cửa hàng theo giá.\n' +
      '4. Tính số tiền thanh toán trong giỏ hàng.\n' +
      '5. Thoát.\n' +
      'Lựa chọn của bạn:'
  );
  switch (choice) {
    case '1':
      let categoryArr = [];
      products.map((item) =>
        !categoryArr.includes(item.category)
          ? categoryArr.push(item.category)
          : ''
      );
      const categoryChoice = +prompt(
        `Chọn category muốn hiển thị sản phẩm:\n` +
          categoryArr.map((item, index) => {
            return `${index + 1}. ${item}\n`;
          })
      );
      for (let i = 1; i <= categoryArr.length; i++) {
        if (categoryChoice === i) {
          printByProduct(products, categoryArr[i]);
        }
      }

      break;
    case '2':
      do {
        idInput = +prompt('Nhập id của sản phẩm:').trim();
      } while (isNaN(idInput) || !Number.isInteger(idInput));
      indexInProducts = products.findIndex((item) => item.id === idInput);
      indexInCart = cart.findIndex((item) => item.id === idInput);
      if (indexInProducts === -1) {
        alert('Sản phẩm không có trong cửa hàng');
      } else {
        do {
          quantityInput = +prompt('Nhập số lượng muốn mua:');
        } while (isNaN(quantityInput) || !Number.isInteger(quantityInput));
        if (
          products[indexInProducts].quantity < quantityInput ||
          products[indexInProducts].quantity === 0
        )
          alert('Số lượng hàng không đủ');
        else {
          if (indexInCart === -1) {
            cart.push({
              id: idInput,
              name: products[indexInProducts].name,
              price: products[indexInProducts].price,
              quantity: quantityInput,
              category: products[indexInProducts].category,
            });
          } else {
            cart[indexInCart].quantity += quantityInput;
          }
          alert('Mua hàng thành công! Cảm ơn quý khách.');
          products[indexInProducts].quantity -= quantityInput;
          listDisplay(products);
          listDisplay(cart);
        }
      }

      break;
    case '3':
      let valid = false;
      while (!valid) {
        let priceSortChoice = prompt(
          'Sắp xếp các sản phẩm trong cửa hàng theo giá:\n' +
            'a. Tăng dần\n' +
            'b. Giảm dần'
        );
        if (priceSortChoice === 'a') {
          products.sort((a, b) => a.price - b.price);
          listDisplay(products);
        } else if (priceSortChoice === 'b') {
          products.sort((a, b) => b.price - a.price);
          listDisplay(products);
        } else alert('Nhập sai định dạng!');
        valid = true;
      }
      break;
    case '4':
      let total = 0;
      if (cart.length === 0) alert('Giỏ hàng không có sản phẩm');
      else {
        total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);
        alert(`Số tiền thanh toán: ${total}`);
      }
      break;
    case '5':
      shouldContinue = false;
      break;
    default:
      alert('Nhập sai định dạng!');
  }
}
