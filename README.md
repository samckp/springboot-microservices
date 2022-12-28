# springboot-microservices-using load balancer
1. Hotel Service
2. User Service  --- Feign client used to call Hotel-service , commented RestTemplate method to consume Hotel-service
3. Rating Service
4. Eureka Service Registry
5. Erueka Discovery Client
6. API-GATEWAY
7. Configuration server

#H2 database for Hotel Service
#MySql for User service
#MongodDB for Rating Service 
#Circuit breaker added - resilience4j

http://localhost:8083/h2  --H2DB

Eureka server - http://localhost:8761/

API-GATEWAY running on port no - 8085
------------
http://localhost:8085/users
http://localhost:8085/rating
http://localhost:8085/hotel
http://localhost:8085/staffs



