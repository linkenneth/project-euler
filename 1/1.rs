fn main() {
    let numbers = (3..1000).filter(|&i| i % 3 == 0 || i % 5 == 0);
    println!("{}", numbers.sum::<u32>());
}
