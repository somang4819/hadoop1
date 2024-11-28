#data.py
import json
import random
from concurrent.futures import ThreadPoolExecutor, as_completed, ProcessPoolExecutor
import csv

def heavy_work(i):
    # 임의의 양의 정수 1,000,000,000/80개를 리스트에 저장
    # n = 1_000_000//80
    n = 1_000_000

    l = [random.randint(1, 1000_000) for _ in range(n)]

    # CSV 파일로 저장
    with open("data.csv", "w", newline="", encoding="utf-8") as csv_file:
        writer = csv.writer(csv_file)
        # 한 줄씩 리스트 데이터를 작성
        for value in l:
            writer.writerow([value])
sublists = []
with ThreadPoolExecutor() as executor: 
    futures = []
    for i in range(1): 
        futures.append(executor.submit(heavy_work, i))
    for future in as_completed(futures):
        print(future._state)
        # sublists.append(future.result())