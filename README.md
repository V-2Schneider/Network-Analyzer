# Network-Analyzer
## Czym jest Network Analyzer?
Dla administratorów różnego rodzaju sieci (energetycznych, wodociągowych itp.) nasza aplikacja Network Analyzer umożliwi znajdowanie ekonomicznych połączeń pomiędzy węzłami sieci oraz dostarczy informacji o samej sieci.
## Budowa sieci (network)
Sieć jest reprezentowana przez graf, czyli zbiór wierzchołków (Nodes) i połączeń (Connections).

- Node = (węzeł) opisany jest przez:
  - id = unikalny identyfikator
  - name = opcjonalny tekst opisujący węzeł
  - type = entry, exit, regular
  - outgoing = lista połączeń wychodzących z tego węzła
  - incoming = lista połączeń wchodzących z innych węzłów
- Connection = (połączenie między dwoma węzłami) opisane jest przez:
  - from = z jakiego węzła
  - to = do jakiego węzła
  - value = wartość, która w zależności od dziedziny biznesowej może oznaczać np. dystans, przepustowość, itd.
## Założenia
- Aplikacja składa się z serwera posiadającego REST API, przy pomocy którego klienci mogą wysyłać zapytania typu "podaj najtańszą drogę z A do B",
- Reprezentacja sieci jest przechowywana po stronie serwera (ale może być zmieniana przez administratora)
- Część serwerowa posiada tryb/panel administracyjny, w którym wyświetlane są informacje dotyczące sieci, oraz narzędzia do edycji struktury sieci
