const company = {
  name: 'RikkeiSoft',
  location: 'Hà Nội',
  employee: [
    { name: 'Nguyễn Văn Luận', position: 'Developer' },
    { name: 'Nguyễn Văn Hoàng', position: 'Tester' },
    { name: 'Hoàng Nam Cao', position: 'Manager' },
  ],
};

console.log('Tên công ty:', company.name);
console.log('Danh sách nhân viên:');
company.employee.map((person) => {
  console.log(`- ${person.name}`);
});
