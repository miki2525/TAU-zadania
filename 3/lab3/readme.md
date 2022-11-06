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
<li>Wejście na stronę: "https://sklep.sfd.pl"</li>
<li>Sprawdzenie tytułu strony: "Suplementy i odżywki: Największy sklep online w PL"</li>
<li>(opcjonalnie) pojawienie się okienka z akceptacją ciasteczek
    <ol>
        <li>Kliknięcie w przycisk: "Akceptuj wszystkie"
        </li>
    </ol>
</li>
<li>Wpisanie w pole "Szukaj..." słowa: "Białko" i wciśnięcie 'Enter'</li>
<li>Sprawdzenie czy znaleziono jakieś produkty</li>
<li>Kliknięcie na perwszy produkt z listy</li>
<li>Kliknięcie w przycisk "WYBIERZ SMAK"</li>
<li>Kliknięcie w przycisk "WYBIERZ SMAK"</li>
<li>Sprawdzenie czy okno ze smakami się pojawia</li>
</ol>
