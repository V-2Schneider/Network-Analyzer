# Network-Analyzer
## Czym jest Network Analyzer?
Dla administratorów różnego rodzaju sieci (energetycznych, wodociągowych itp.) nasza aplikacja Network Analyzer umożliwi znajdowanie ekonomicznych połączeń pomiędzy węzłami sieci oraz dostarczy informacji o samej sieci.
## Budowa
- Node = każdy węzeł opisany jest poprzez:
  - id = unikalny identyfikator
  - name = opcjonalny tekst opisujący węzeł
  - type = entry, exit, regular
  - outgoing = lista połączeń wychodzących z tego węzła
  - incoming = lista połączeń wchodzących z innych węzłów
- Connection = powiązanie węzłów
  - Połączenie dwóch węzłów:  
    - from = z jakiego węzła
    - to = do jakiego węzła
    - value = wartość, która w zależności od dziedziny biznesowej może oznaczać np. dystans, przepustowość, itd.
