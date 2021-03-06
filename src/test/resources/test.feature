#language: ru
Функционал: Вклады

  @Test
  Структура сценария: Калькулятор "Рассчитайте доходность по вкладу"

    * выбран пункт "Вклады" главного меню
    * выбрана валюта "Рубли"
    
    * выбран на срок "6 месяцев"
    
    * заполняются поля:
    |Сумма вклада          |<amount>   |
    |Ежемесячное пополнение|<replenish>|
    
    * выбранны чекбоксы:
    |Ежемесячная капитализация|<capitalization>|
    |Частичное снятие         |<partialOut>    |

    * проверка результатов:
    |Ставка    |<rate>             |
    |К снятию  |<resultAfterPeriod>|
    |Начисленно|<earned>           |
    |Пополнение|<replenishOnPeriod>|

    Примеры:
    |amount|replenish|capitalization|partialOut|-|rate |resultAfterPeriod|earned   |replenishOnPeriod|
    |300000|50000    |да            |нет       |-|5.80%|562 350,83       |12 350,83|250 000          |
    |500000|70000    |да            |да        |-|5.65%|869 107,22       |19 107,22|350 000          |