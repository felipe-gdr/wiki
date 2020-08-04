fn main() {
    problem_3_2()
}

fn problem_1() {
    const MAX: u32 = 1000;
    let mut counter = 0;
    let mut multiples_sum = 0;

    while counter < MAX {
        if counter % 3 == 0 {
            multiples_sum = multiples_sum + counter;
        } else if counter % 5 == 0 {
            multiples_sum = multiples_sum + counter;
        }

        counter = counter + 1;
    }

    println!("{}", multiples_sum)
}

fn problem_2() {
    const MAX: u32 = 4_000_000;
    let mut numbers = [1, 2];
    let mut current = 0;
    let mut total = 2;

    while current <= MAX {
        current = numbers[0] + numbers[1];
        if current % 2 == 0 {
            total = total + current;
        }

        numbers[0] = numbers[1];
        numbers[1] = current;
    }

    println!("{}", total)
}

fn problem_3_2() {
    let mut num: u64 = 600_851_475_143;
    let mut factor = 2;
    let mut lastFactor = 1;

    while num > 1 {
        if num % factor == 0 {
            lastFactor = factor;
            num = num / factor;

            while num % factor == 0 {
                num = num / factor;
            }

            factor = factor + 1;
        }
    }

    println!("{}", lastFactor);

}

fn problem_3() {
    let num: u64 = 600_851_475_143;
    let mut counter: u64 = 775_146; //NUM.sqrt();

    while counter > 0 {
        let is_prime = is_prime(counter);

        if num % counter == 0 {
            if is_prime {
                println!("problem 3: {}", counter);
                break
            }
        }

        counter = counter - 1;
    }
}

fn is_prime(num: u64) -> bool {
    let mut quotient = 2;

    while quotient < (num / 2) {
        if num % quotient == 0 {
            return false
        }
        quotient = quotient + 1;
    }

    return true
}
