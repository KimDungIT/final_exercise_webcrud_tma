mvn clean install

feature:repo-add mvn:com.tma.apa.training.device.management/device-mgmt-features/1.0-SNAPSHOT/xml
feature:install device-mgmt-api
feature:install device-mgmt-impl
feature:install device-mgmt-rest