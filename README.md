# Emitter Demo

1. Run dev mode (`quarkus dev` or `./mvnw quarkus dev`)
2. Open a new terminal
3. Run `curl -kv http://localhost:8080/data`
4. Open a new terminal
5. Run `curl -X 'POST' http://localhost:8080/data -H 'Content-Type: application/json' -d '{"name": "Task 1","description": "Some task"}'` a bunch of times, changing the JSON payload for something different.
6. See the output from the first terminal:

```bash
╰─ curl -v http://localhost:8080/data
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* connect to ::1 port 8080 from ::1 port 50095 failed: Connection refused
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
> GET /data HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.7.1
> Accept: */*
> 
* Request completely sent off
< HTTP/1.1 200 OK
< Content-Type: text/event-stream
< X-SSE-Content-Type: application/json
< transfer-encoding: chunked
< 
data:{"id":0,"name":"Task 1","description":"Some task"}

data:{"id":1,"name":"Task 2","description":"Some other task"}


```

```bash 
╰─ curl -X 'POST' http://localhost:8080/data -H 'Content-Type: application/json' -d '{"name": "Task 1","description": "Some task"}'

╰─ curl -X 'POST' http://localhost:8080/data -H 'Content-Type: application/json' -d '{"id": 1,"name": "Task 2","description": "Some other task"}'
```