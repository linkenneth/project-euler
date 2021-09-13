fn main() {
    let sum_of_squares: u64 = (1..101).map(|x: u64| x.pow(2)).sum();
    let square_of_sums: u64 = (1..101).sum::<u64>().pow(2);
    println!("{:#?}", square_of_sums - sum_of_squares);
}
