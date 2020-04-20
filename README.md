# Проект автоматизации LMS системы для BNTU
### Stack
* Java8 - [docs](https://docs.oracle.com/javase/8/docs/api/)
* Gradle - [docs](https://docs.gradle.org/current/userguide/userguide.html)
* JUnit5 - [docs](https://junit.org/junit5/docs/current/user-guide/)
* Cucumber - [docs](https://cucumber.netlify.app/docs/cucumber/)
* Allure - [docs](https://docs.qameta.io/allure/)
* AssertJ - [docs](https://assertj.github.io/doc/)
* Awaitility - [docs](https://github.com/awaitility/awaitility/wiki/Usage)
* Selenium WD - [docs](https://www.selenium.dev/documentation/en/)
* Jackson - [docs](https://github.com/FasterXML/jackson-docs/wiki/Finding-Javadoc)

### Навигация по проекту
1) main/java/in/bntu/lms/
    * framework - фреймворк
        * annotation - пакет с аннотациями
        * base - пакет с абстрактными базовыми клссами
        * browser - пакет с фабрикой браузеров (покуда только chrome)
        * configuration - пакет с классами для хранения конфигураций полученных из .yaml файлов
        * driver - пакет для работы с WebDriver
        * ui - пакет с web элементами
        * uiparser - пакет с парсерами для ui (из String в Type)
    * util - пакет для утилитарных методов
2) test/java/in/bntu/lms/
    * feature - пакет с тестами описанными на языке gherkin
    * hooks - пакет с cucumber hooks (after/before)
    * listeners - пакет с listener
    * models - паке с тестовыми моделями
    * pages - пакет с PageObject
    * runner - пакет с классами для запуска тестов
    * steps - пакет со степами

### Навигация по ресурсным файлам
1) main/java/resources/ - пакет с ресурсными файлами для фреймворка
    * scripts - пакет с набором js скриптов
2) test/java/resources/ - пакет с ресурсными файлами для тестов
    * browsers - пакет с конфигурациями для браузера
    * testdata - пакет с тестовыми данными

##### browsers/chrome.yaml
```yaml
name: chrome #название браузера
version: 79.0 #версия chrome
is_remote: false #запускать на удаленной машине или локально
remote: http://url/wd/hub #url удаленной машины
args: #аргументы для запуска chrome
  - start-maximized
  - no-sandbox
capabilities: #капабилити для chrome
  enableVnc: true
options: #опции chrome
  intl.accept_languages: en
  safebrowsing.enabled: true
  profile.default_content_settings.popups: 0
  disable-popup-blocking: true
  download.prompt_for_download: false
```
##### allure.yaml
```yaml
allure.results.directory: build/allure-results #директория для хранения allure данных по тестам
```
##### selenium.yaml
```yaml
browser: chrome #браузер на котором будут запускаться тесты
url: https://educats.bntu.by/Account/Login #url тестируемого объекта
testDataPath: testdata #название директории с тестовыми данными
conditionTimeOut: #таймаут выполнения проверки условий
  timeUnit: SECONDS #Ед. измерения (@see java.util.concurrent.TimeUnit)
  timeOut: 30 #время
pollingTimeOut: #время ожидания между итерациями проверки условия
  timeUnit: MILLISECONDS
  timeOut: 200
```

### Запуск автотестов и генерация отчета
##### Запуск всех тестов: 
```bash
gradlew.bat test 
```
##### Запуск тестов по тегу
```bash
gradlew.bat test -Dcucumber.options="--tags @news" #Запуск тестов с тегом @news 
```
##### Создание отчета
```bash
gradlew.bat allureReport #отчет лежит в директории build/reports/allure-report/index.html
```