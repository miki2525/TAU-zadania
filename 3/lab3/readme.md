<h1>Lab 3: Selenium</h1>

---
<h2>Model testowania</h2>

<h3>Użyte technologie i biblioteki:</h3>
<ul>
<li>JAVA 17,</li>
<li>Selenium - framework do testowania stron,</li> 
<li>Webdrivermanager - konfiguracja webdriverów,</li> 
<li>jUnit5 - asercje</li> 
</ul>

<h3>Testowane przeglądarki:</h3>
<ul>
<li>Mozilla Firefox</li>
<li>Google Chrome</li>
</ul>

---
<h2>Scenariusze</h2>

<h3>Scenariusz 1 </h3>
(plik Scenario1.java)

<h4>Dodawanie elementu do koszyka</h4>
<ol>
<li>Otwarcie przeglądarki (chrome/firefox)</li>
<li>Wejście na stronę: <b><u>"https://sklep.sfd.pl"</u></b></li>
<li>Sprawdzenie tytułu strony: <b><u>"Suplementy i odżywki: Największy sklep online w PL"</u></b></li>
<li>(opcjonalnie) Sprawdzenie czy pojawia się okienko z akceptacją ciasteczek
    <ol>
        <li> Jeśli TAK -> Kliknięcie w przycisk: "Akceptuj wszystkie"
        </li>
    </ol>
</li>
<li>Wpisanie w pole "Szukaj..." słowa: <b><u>"Białko"</u></b> i wciśnięcie 'Enter'</li>
<li>Sprawdzenie czy znaleziono jakieś produkty: <b><u>> 0</u></b></li>
<li>Kliknięcie na pierwszy produkt z listy</li>
<li>Kliknięcie w przycisk "WYBIERZ SMAK"</li>
<li>Sprawdzenie czy okno ze smakami się pojawia: <b><u>TAK</u></b></li>
<li>Sprawdzenie czy są jakiekolwiek smaki: <b><u>> 0</u></b></li>
<li>Kliknięcie w pierwszy wariant smaku/li>
<li>Kliknięcie w przycisk "DODAJ DO KOSZYKA"/li>
<li>Sprawdzenie czy pojawiło się okno z potwierdzeniem dodania produktu do koszyka: <b><u>TAK</u></b>/li>
<li>Sprawdzenie czy produkt został dodany do koszyka: <b><u>"PRODUKT ZOSTAŁ DODANY"</u></b>/li>
</ol>
======================================================================

<h3>Scenariusz 2 </h3>
(plik Scenario2.java)

<h4>Odzyskiwanie hasła dla nieistniejącego konta</h4>
<ol>
<li>Otwarcie przeglądarki (chrome/firefox)</li>
<li>Wejście na stronę: <b><u>"https://sklep.sfd.pl"</u></b></li>
<li>Sprawdzenie tytułu strony: <b><u>"Suplementy i odżywki: Największy sklep online w PL"</u></b></li>
<li>(opcjonalnie) Sprawdzenie czy pojawia się okienko z akceptacją ciasteczek
    <ol>
        <li> Jeśli TAK -> Kliknięcie w przycisk: "Akceptuj wszystkie"
        </li>
    </ol>
</li>
<li>Kliknięcie w przycisk "MOJE KONTO"</li>
<li>Sprawdzenie czy pojawia się boczny panel "Moje Konto": <b><u>TAK</u></b></li>
<li>Kliknięcie w przycisk "Zaloguj"</li>
<li>Sprawdzenie URL formularza logowania: <b><u>"https://sklep.sfd.pl/aspx/Login.aspx"</u></b></li>
<li>Kliknięcie w przycisk "Odzyskaj hasło"</li>
<li>Wpisanie w pole "LOGIN LUB EMAIL:" loginu: <b><u>loginDoesNotExist</u></b></li>
<li>Sprawdzenie czy pojawił się komunikat: <b><u>NIE ZNALEZIONO PODANEGO LOGINU/ADRESU EMAIL.</u></b></li>
</ol>
