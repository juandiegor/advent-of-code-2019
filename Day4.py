
def at_least_two_consecutives(number):
    number = str(number)
    pos = 0
    for digit in number:
        if pos != len(number) - 1 and number[pos+1] == digit:
            return True
        pos += 1
    return False


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
        if at_least_two_consecutives(i) and all_numbers_same_or_increasing(i) and size_6(i):
            count += 1
    return count


print(find_passwords(402328, 864247))
