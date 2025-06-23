function convertTime() {
  const timeInput = document.getElementById("timeInput").value;
  const timeOutput = document.getElementById("timeOutput");

  const timePattern = /^([01]\d|2[0-3]):[0-5]\d:[0-5]\d$/;
  if (!timePattern.test(timeInput)) {
    timeOutput.innerText = `Thời gian không hợp lệ. Vui lòng nhập đúng định dạng HH:MM:SS`;
    return;
  }

  let hour = +timeInput.slice(0, 2);
  const minute = +timeInput.slice(3, 5);
  const second = +timeInput.slice(6, 8);
  let period = "AM";

  if (hour >= 12) {
    hour -= 12;
    period = "PM";
  }
  if (hour === 0) {
    hour = 12;
  }

  timeOutput.innerText = `${hour}:${minute}:${second} ${period}`;
}

document.querySelector(".btn").addEventListener("click", convertTime);
