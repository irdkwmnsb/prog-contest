from itertools import count
from sys import stderr, stdin, stdout, argv
from subprocess import Popen, PIPE, STDOUT, TimeoutExpired
from random import *
from string import ascii_lowercase


def get_output(args, test: bytes = None, tl: int = 1):
    proc = Popen(args, stdout=PIPE, stdin=PIPE, stderr=stderr)
    if test:
        proc.stdin.write(test)
        proc.stdin.flush()
        proc.stdin.close()

    try:
        proc.wait(tl)
    except TimeoutExpired:
        print("TL")
        print(args)
        exit(1)

    if proc.returncode != 0:
        print("RE")
        exit(2)
    elif proc.returncode == 0:
        return proc.stdout.read()


def make_random_string(length, charsource):
    return "".join(choice(charsource) for i in range(length))


def test(i: int):
    seed(i)
    n, m = 5, 5
    a = [randint(1, m) for _ in range(n)]
    return f"{n} {m} \n{' '.join(map(str, a))}"
    # return get_output("cmake-build-debug/test_gen.exe").strip().decode()


def solve(test: str):
    import re
    pattern, word = test.split()
    p = re.compile(pattern.replace(".", r"\.").replace("*", r".*").replace("?", "."))
    return "YNEOS"[not bool(p.fullmatch(word))::2]


def check(correct: str, testing: str):
    return correct == testing


if __name__ == "__main__":
    for i in count(int(argv[1] or "1")):
        tt = test(i)
        print("test %d: " % i, end='')
        # print(tt)
        # answerok = get_output("python correct.py", tt).strip().decode()
        # answerok = solve(tt)
        answerok = get_output("java output/Main.java", tt.encode(), tl=10).strip().decode()
        answer = get_output(r"C:\Users\me\Downloads\Telegram Desktop\a.exe", tt.encode()).strip().decode()
        print()
        print(tt)
        print(answer)
        print(answerok)
        if answer != answerok:
            print("WA")
            print("Test was:")
            print(tt)
            print("Expected:")
            print(answerok)
            print("Got:")
            print(answer)
            exit(0)
        else:
            print("OK")
