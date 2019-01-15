Ustawienie serwera bazy danych:

1. Uruchomiæ lokalny serwer bazy danych MySQL
2  Jeœli nie istnieje, to utworzyæ urzytkownika 'root' z has³em 'password'.
3 - zaimportowaæ tabele (powinny utworzyæ siê 2 tabele node oraz connection w schemacie networkanalizer)

Mo¿liwe problemuy
- U¿ytkownik mo¿e nie mieæ wszystkich uprawnieñ do schematu - wtakim przypadku nadaæ mu je
- Klient nie wspiera protoko³u autoryfikacji wykorzystywanego przez serwer - wykonaæ polecenie ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password'