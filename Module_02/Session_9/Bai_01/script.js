const employees = [
  {
    id: 1,
    name: 'John',
    age: 18,
    address: 'New York',
  },
  {
    id: 2,
    name: 'Mike',
    age: 22,
    address: 'Canada',
  },
  {
    id: 3,
    name: 'Linda',
    age: 19,
    address: 'California',
  },
  {
    id: 4,
    name: 'Peter',
    age: 25,
    address: 'London',
  },
  {
    id: 5,
    name: 'Tony',
    age: 17,
    address: 'New York',
  },
];

const table = document.createElement('table');

// Thêm tiêu đề cột
const headerRow = table.insertRow();
const headerCells = Object.keys(employees[0]);
headerCells.forEach((key) => {
  const th = document.createElement('th');
  if (key === 'id') {
    th.textContent = 'STT';
  } else {
    th.textContent = key.toUpperCase();
  }
  headerRow.appendChild(th);
});

// Thêm data từng ô
employees.map((item) => {
  const dataRow = table.insertRow();
  const cells = Object.values(item);
  cells.forEach((value) => {
    const td = document.createElement('td');
    td.textContent = value;
    dataRow.appendChild(td);
  });
});

document.getElementById('table-container').appendChild(table);
