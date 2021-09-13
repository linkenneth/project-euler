fn main() {
    let mut n = 2520;
    let number = loop {
        if (11..21).all(|x| n % x == 0) {
            break n;
        }
        n += 2520;
    };
    println!("{:#?}", number);
}
