fn is_pythagorean_triplet(a: u32, b: u32, c: u32) -> bool {
    return a.pow(2) + b.pow(2) == c.pow(2);
}

fn main() {
    let (mut a, mut b) = (1, 1);
    while a + b < 1000 {
        let c = 1000 - a - b;
        if is_pythagorean_triplet(a, b, c) {
            println!("{}", a * b * c);
            break;
        }
        if b < c {
            b += 1;
        } else {
            a += 1;
            b = 1;
        }
    }
}
