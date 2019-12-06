
def at_least_two_exact_consecutive(number):
    number = str(number)
    pos = 0
    for digit in number:
        if pos < len(number) - 2 and number[pos+1] == digit and number[pos+2] != digit:
            return True, number
        pos += 1
    return False, None


def no_more_than_two_consecutive(number, consec):
    number = str(number)
    pos = 0
    for digit in number:
        if pos < len(number) - 2 and number[pos+1] == digit and number[pos+2] == digit:
            return False
        pos += 1
    return True


def all_numbers_same_or_increasing(number):
    number = str(number)
    pos = 0
    for digit in number:
        if pos != len(number) - 1 and digit > number[pos+1]:
            return False
        pos += 1
    return True


def size_6(number):
    return len(str(number)) == 6


def find_passwords(min_n, max_n):
    count = 0
    for i in range(min_n, max_n):
        at_most_two, _ = at_least_two_exact_consecutive(i)
        if at_most_two and all_numbers_same_or_increasing(i) and size_6(i):
            count += 1
    return count


print(find_passwords(111111, 111112))
