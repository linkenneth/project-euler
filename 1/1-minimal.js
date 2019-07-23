console.log(
  [...Array(1000).keys()]
    .filter(m => m % 3 === 0 || m % 5 === 0)
    .reduce((a, b) => a + b)
)
