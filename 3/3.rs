use std::collections::HashSet;

fn primes(x: u64) -> HashSet<u64> {
    let root = (x as f64).sqrt() as u64 + 1;
    let mut results = HashSet::new();
    let mut i = 2u64;
    let mut n = x;
    while i < root && n > 1 {
        if n % i == 0 {
            results.insert(i);
            n = n / i;
        } else {
            i += 1;
        }
    }
    return results;
}

fn main() {
    let x = 600851475143;
    println!("{:#?}", primes(x).iter().max());
}
