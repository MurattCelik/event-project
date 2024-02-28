# Konferans Sunum Projesi
Gün içerisinde 9:00 - 17:00 arasında belirli dakikalarda olan
sunumları otomatize ederek, düzenli bir program çıtkısı elde eder.

-------------------------------------------------------------
## Api Akışı
İstekler swagger üzerinden tetiklenebilir.
Swagger Adresi
http://localhost:8072/swagger-ui/index.html#/

-------------------------------------------------------------

## Apiler
### /event/save
  Yeni bir event kaydetme apisi

### /event/get
  Id'ye göre berlirli eventi getirme apisi

### /event/update
  Id'ye göre belirli eventi update etme servisi

### /event/delete
  Id'ye göre belirli eventi delete etme servisi
  
### /event/find-all
  Tüm eventleri getirme servisi
  
### /event/presentation-program
  Salon sayısına göre program çıktısı hazırlayan api

-------------------------------------------------------------

 ## Kullanılan Kütüphaneler
* spring-boot-starter-web
* spring-boot-devtools
* h2 database
* lombok
* spring-boot-starter-test
* springdoc-openapi-starter-webmvc-ui
* spring-boot-starter-data-jpa

-------------------------------------------------------------

## Data Migration 
H2 Database dataların toplu bir şekilde oluşturulması 
için h2 ortamına aşağıdaki SQL çalıştırılabilir 
yada tekli data şeklinde Swagger üzerinden tetiklenebilir.
http://localhost:8072/h2

INSERT INTO EVENT(EVENT_NAME, SPEAKOR_FULL_NAME, DURATION, DURATION_UNIT) 
VALUES('Architecting Your Codebase', 'Anderson Monty', 60, 'min'),
('Overdoing it in Python', 'Bertha Liz', 45, 'min'),
('Flavors of Concurrency in Java', 'Caroline Alla', 30, 'min'),
('Ruby Errors from Mismatched Gem Versions', 'Braxton Jeff', 45, 'min'),
('JUnit 5 - Shaping the Future of Testing on the JVM', 'Charlie Koby', 45, 'min'),
('Communicating Over Distance','Vincent Vall', 60, 'min'),
('Cloud Native Java lightning', 'Kirk Als', 30, 'min'),
('AWS Technical Essentials ', 'Tylor Zac', 45, 'min'),
('Continuous Delivery', 'Christal Miley', 30, 'min'),
('Monitoring Reactive Applications', 'Allen Evn', 30, 'min'),
('Pair Programming vs Noise', 'Fredrick ALuı', 45, 'min'),
('Rails Magic', 'Gates Ma', 60, 'min'),
('Microservices "Just Right"', 'Cranston Axl', 60, 'min'),
('Clojure Ate Scala (on my project)', 'Hawkins Lou', 45, 'min'),
('Perfect Scalability', 'Wilson Grk', 30, 'min'),
('Apache Spark', 'Lennon Ken', 30, 'min'),
('A World Without HackerNews', 'Fairchild Loc', 30, 'min'),
('User Interface CSS in Apps', 'Jim Jolb', 30, 'min')




