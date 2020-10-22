# thrift-sample

Test repo to implement a thrift sample server.

It started as an exploration of ktor to serve thrift, and as I failed miserably to get an AsyncIface served in that setup, I chose to use plain Armeria (https://armeria.dev)


Usage:
if you need to generate the java thrift files, run:

```bash
$ make compile-thrift
```

You can run the application.kt file that will serve three thrift servers on your localhost:

- http://localhost:7070/api -> Serves requests with a normal Service.Iface implementation
- http://localhost:8080/api -> Serves requests with a Service.ASyncIFace implementation
- http://localhost:9090/api -> Serves requests with a Service.AsyncIface implementation, utilizing a bespoke CoroutineScope on top of a custom thread pool to leverage coroutines and non-blocking code

