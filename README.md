# Emitter Demo

1. Run dev mode (`quarkus dev` or `./mvnw quarkus dev`)
2. Open a new terminal
3. Run `curl -kv http://localhost:8080/data`
4. Open a new terminal
5. Run `curl -X 'POST' http://localhost:8080/data -H 'Content-Type: text/plain' -d 'Hi there!'` a bunch of times, changing `Hi there!` for something different.
6. See the output from the first terminal:

```
╰─ curl -kv http://localhost:8080/data
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* connect to ::1 port 8080 from ::1 port 49369 failed: Connection refused
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
< X-SSE-Content-Type: text/plain
< transfer-encoding: chunked
< 
data:Hi there!

data:hello

data:Yay!

```