function checkPrime(a) {
  if (!Number.isInteger(a) || a <= 0) {
    return "dữ liệu không hợp lệ";
  } else {
    if (a === 1) return "không phải là số nguyên tố";
    for (let i = 2; i < a; i++) {
      if (a % i === 0) return "không phải là số nguyên tố";
    }
    return "là số nguyên tố";
  }
}

alert(`17: ${checkPrime(17)}`);
alert(`6: ${checkPrime(6)}`);
alert(`"text": ${checkPrime("text")}`);
