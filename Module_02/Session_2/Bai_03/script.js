function checkAccount() {
  const accountInput = document.getElementById("accountInput").value;
  const passwordInput = document.getElementById("passwordInput").value;

  if (accountInput === "ADMIN") {
    if (passwordInput === "TheMaster") alert("Welcome");
    else if (passwordInput === "") alert("Cancelled");
    else alert("Wrong password");
  } else if (accountInput === "") alert("Cancelled");
  else alert("I Don’t know you");
}

document.getElementById("btn").addEventListener("click", checkAccount);
