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
#Circuit breaker added - resilience4j.
#Retry implemented - resilience4j
#RateLimiter implemented - resilience4j.

http://localhost:8083/h2  --H2DB

Eureka server - http://localhost:8761/

API-GATEWAY running on port no - 8085
------------
http://localhost:8085/users
http://localhost:8085/rating
http://localhost:8085/hotel
http://localhost:8085/staffs


------Run Application in sequence----
1. API-GATEWAY
2. Service-Registry
3. Config-server
4. rating-service
5. hotel-service
6. user-service

----------------
# Jmeter test result added for User Service