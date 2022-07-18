
Client -> Service1 -> Service2


immediate failure
catch exception and return
repeated calls when remote service is down

timeout failure
assigned thread waiting for response when timeout
problem if there is high request rate and all wait for timeout
eventually callign service can run out of resources

	flooding target service may cause issue for other services that use it

single failures are common
we could implement counter of failed/success requests e.g. for last 50 requests
























https://stackoverflow.com/questions/63980103/is-circuit-breaker-pattern-applicable-for-asynchronous-requests-also
https://spring.io/blog/2019/04/16/introducing-spring-cloud-circuit-breaker
https://www.baeldung.com/spring-cloud-circuit-breaker
https://microservices.io/patterns/reliability/circuit-breaker.html



