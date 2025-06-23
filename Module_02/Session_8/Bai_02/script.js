const car = {
  brand: 'Toyota',
  model: 'Corolla',
  year: 2020,
};

car.color = 'red';
car.year = 2022;
console.log(car);

console.log('----------------------');
for (let key in car) {
  console.log(`${key}: ${car[key]}`);
}

console.log('----------------------');
Object.keys(car).forEach((key) => {
  console.log(`${key}: ${car[key]}`);
});
