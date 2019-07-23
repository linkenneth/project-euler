let sum = 0;
for (let m = 0; m < 1000; m++) {
  if (m % 3 === 0 || m % 5 === 0) {
    sum += m;
  }
}

console.log(sum);
