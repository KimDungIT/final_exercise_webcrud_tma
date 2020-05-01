mvn clean install

feature:repo-add mvn:com.tma.apa.training.device.management/device-mgmt-features/1.0-SNAPSHOT/xml
feature:install device-mgmt-api
feature:install device-mgmt-impl
feature:install device-mgmt-rest

-------------------------------
copy files jar into deploy folder of karaf folder then run commands:

feature:repo-add cxf 3.3.5
feature:install transaction jndi pax-jdbc-config pax-jdbc-mariadb pax-jdbc-pool-dbcp2
feature:install jdbc aries-blueprint hibernate jpa
feature:install cxf
---------------------------
docker build -t <image_name> .
docker-compose up -d



