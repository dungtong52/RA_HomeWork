const person = {
  name: 'John Doe',
  age: 25,
  job: 'Developer',
};
console.log('Cách 1:');
console.log(person);

console.log('Cách 2:');
Object.keys(person).forEach((key) => {
  console.log(`${key}: ${person[key]}`);
});

console.log('Cách 3:');
for (let key in person) {
  console.log(`${key}: ${person[key]}`);
}
