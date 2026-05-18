import subprocess, requests
subprocess.run("clear", shell=True)

#import matplotlib

# response = requests.get("https://httpbin.dev/delay/10", timeout=3)
response = requests.get("https://jsonplaceholder.typicode.com/todos/1", timeout=3)

# print(response.text)
print(response.json())