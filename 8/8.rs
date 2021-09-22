use std::fs;

fn to_digits(s: &str) -> Vec<u32> {
    return s.chars().map(|c| c.to_digit(10).unwrap()).collect();
}

fn main() {
    let data = fs::read_to_string("8.in").expect("fail");
    let digits = to_digits(&data.replace("\n", ""));
    let products = (1..987).map(|i|
        (i..(i + 13)).map(|j| digits[j] as u64).product::<u64>()
    );
    println!("{:#?}", products.max());
}
