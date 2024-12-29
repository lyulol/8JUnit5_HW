package tests.data;

public enum Language {
    DE("Wir zeigen Produkte an, die an deinen Standort versendet werden. Du kannst im Menü oben einen anderen Standort auswählen. Klicke hier, um mehr zu erfahren."),
    CS("Zobrazujeme produkty, které jsou dodávány do vaší lokality. V nabídce výše můžete vybrat jiné umístění. Kliknutím sem se dozvíte více o mezinárodní přepravě."),
    NL("We tonen producten die naar je locatie worden verzonden. In het menu hierboven kun je een andere locatie selecteren. Klik hier voor meer informatie over internationale verzending."),
    PL("Wyświetlamy produkty, które są wysyłane do Twojej lokalizacji. Możesz wybrać inną lokalizację w menu powyżej. Kliknij tutaj, aby dowiedzieć się więcej o wysyłce międzynarodowej.");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
