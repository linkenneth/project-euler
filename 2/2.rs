fn main() {
    let mut a = 1;
    let mut b = 1;
    let mut sum = 0;
    while b < 4_000_000 {
        if b % 2 == 0 {
            sum += b;
        }
        let next = a + b;
        a = b;
        b = next;
    }
    println!("{}", sum)
}
