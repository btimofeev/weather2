Добрый день.

На тестовое задание у меня было мало времени, т.ч. ограничился выводом только трех параметров и совсем не занимался дизайном.

Архитектура MVVM, потому что мне кажется, что это проще и удобнее MVP 
(а последнюю я делал в подобном тест.задании для другой компании, вот можете посмотреть код https://github.com/btimofeev/weather-android/, 
там было требование не использовать никакие библиотеки, кроме парсинга json)). 

Есть репозиторий, который вместе со всеми своими зависимостями инжектится через dagger. Репозиторий грузит текущую погоду из сети, при ошибке из базы. 
Для прогноза на следующие 5 дней грузит только из сети, если нужно сделаю и базу тоже (но там по сути все тоже самое).