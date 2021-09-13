use itertools::Itertools;

fn is_palindrome(x: &u32) -> bool {
    let bytes = x.to_string().into_bytes();
    let (mut i, mut j) = (0, bytes.len() - 1);
    while i < j {
        if bytes[i] != bytes[j] {
            return false;
        }
        i += 1;
        j -= 1;
    }
    return true;
}

fn main() {
    let max_palindrome = (100..1000).cartesian_product(100..1000)
                                    .map(|(x, y)| x * y)
                                    .filter(is_palindrome)
                                    .max();
    println!("{:#?}", max_palindrome);
}
