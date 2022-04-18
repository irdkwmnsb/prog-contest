from itertools import count
from sys import stderr, stdin, stdout, argv
from subprocess import Popen, PIPE, STDOUT, TimeoutExpired
from random import *
from string import ascii_lowercase
import string


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


def test(number):
    seed(number)
    kn = 3
    vn = 10
    keys = ["".join(sample(string.ascii_lowercase, k=randint(2, 5))) for _ in range(kn)]
    vals = ["".join(sample(string.ascii_lowercase, k=randint(2, 5))) for _ in range(vn)]
    i = set()

    def put():
        k = choice(keys)
        i.add(k)
        return "put " + k + " " + choice(vals)

    def delete():
        if i:
            k = sample(i, 1)[0]
            i.remove(k)
            return "delete " + k
        else:
            return "delete isempty"

    def get():
        if i:
            return "get " + sample(i, 1)[0]
        else:
            return "get isempty"

    ops = [
        put, delete, get
    ]
    opscound = 5000
    ans = [choice(ops)() for _ in range(opscound)]
    return "\r\n".join(ans) + "\r\n"


def solve(test: str):
    ops = test.split("\n")
    ans = []
    map = {}
    for op in ops:
        if op:
            name, *args = op.split()
            if name == "put":
                map[args[0]] = args[1]
            if name == "delete":
                if args[0] in map:
                    del map[args[0]]
            if name == "get":
                ans.append(map.get(args[0], "none"))
    return "\n".join(ans) + "\n"


def check(correct: str, testing: str):
    return correct == testing


if __name__ == "__main__":
    for i in count(int(argv[1] or "1")):
        tt = test(i)
        print("test %d: " % i, end='')
        # answerok = get_output("python correct.py", tt).strip().decode()
        answerok = solve(tt).strip()
        answer = get_output(r"java -cp output Main",
                            tt.encode(), tl=10).decode().strip().replace("\r\n","\n")
        # answer = get_output(r"C:\Users\me\Downloads\Telegram Desktop\a.exe", tt.encode()).strip().decode()
        if answer != answerok:
            print("WA")
            print("Test was:")
            print(tt)
            print("Expected:")
            print(answerok)
            print(repr(answerok))
            print("Got:")
            print(answer)
            print(repr(answer))
            exit(0)
        else:
            print("OK")
