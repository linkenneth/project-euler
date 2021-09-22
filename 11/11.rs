use std::fs;

// TODO: ideally do this with iterators. learn traits, then learn iterators,
// and learn how to make a lazy iterator for each of the indices?

fn main() {
    let data = fs::read_to_string("11.in").expect("fail");
    let grid: Vec<Vec<u32>> =
        data.trim()
            .split("\n")
            .map(
                |row| row.split(" ").map(
                    |item| item.parse::<u32>().unwrap()
                ).collect::<Vec<u32>>())
            .collect();
    println!("{:#?}", grid);
}
