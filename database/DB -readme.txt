Ustawienie serwera bazy danych:

1. Uruchomi� lokalny serwer bazy danych MySQL
2  Je�li nie istnieje, to utworzy� urzytkownika 'root' z has�em 'password'.
3 - zaimportowa� tabele (powinny utworzy� si� 2 tabele node oraz connection w schemacie networkanalizer)

Mo�liwe problemuy
- U�ytkownik mo�e nie mie� wszystkich uprawnie� do schematu - wtakim przypadku nada� mu je
- Klient nie wspiera protoko�u autoryfikacji wykorzystywanego przez serwer - wykona� polecenie ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password'