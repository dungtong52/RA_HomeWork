function total(a, b) {
  if (!Number.isInteger(a) || !Number.isInteger(b))
    return "dữ liệu không hợp lệ";
  else return a + b;
}

alert(`2 + 6 = ${total(2, 6)}`);
alert(`3 + 'text' = ${total(3, "text")}`);
alert(`7 + (-7) = ${total(7, -7)}`);
alert(`4.5 + 3 = ${total(4.5, 3)}`);
