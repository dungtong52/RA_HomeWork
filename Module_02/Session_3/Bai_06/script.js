function resultPrint() {
  const result = document.getElementById("result");
  let string = "";

  for (let i = 1; i <= 100; i++) {
    if (i % 3 === 0) string = "Fizz";
    else if (i % 5 === 0) string = "Buzz";
    else if (i % 15 === 0) string = "FizzBuzz";
    else string = i;
    result.innerHTML += `${string}<br/>`;
  }
}
document.querySelector(".btn").addEventListener("click", resultPrint);
