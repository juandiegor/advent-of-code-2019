
def at_least_two_exact_consecutive(number):
    number = str(number)
    pos = 0
    at_least_a_pair = False
    banned = []
    for digit in number:
        if digit not in banned and pos < len(number) - 2 and number[pos+1] == digit and digit not in number[pos+2:]:
            at_least_a_pair = True
        else:
            if pos < len(number) - 2:
                banned.append(digit)

        if digit not in banned and pos == len(number) - 2 and number[pos+1] == digit:
            at_least_a_pair = True
        pos += 1
    return at_least_a_pair


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
        if all_numbers_same_or_increasing(i) and at_least_two_exact_consecutive(i):
            count += 1
    return count


print(find_passwords(402328, 864247))
