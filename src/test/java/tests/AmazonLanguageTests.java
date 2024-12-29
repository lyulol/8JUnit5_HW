package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import tests.data.Language;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AmazonLanguageTests {

    @BeforeEach
    void setUp() {
        open("https://www.amazon.de/ref=nav_logo");
        if ($("#sp-cc-rejectall-link").exists()) {
            $("#sp-cc-rejectall-link").click();
        }
        if ($("[role=alertdialog]").exists()) {
            $(".a-button-input").click();
        }
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "При изменении языка на {0} должен отображаться текст на языке {0}")
    @Tag("Smoke")
    void amazonShouldDisplayCorrectTextAfterLanguageChange(Language language) {
        $("#icp-nav-flyout").hover();
        $$(".nav-item").find(text(language.name())).click();
        $("#desktop-banner-stripe").shouldHave(text(language.description));
    }

    static Stream<Arguments> amazonShouldDisplayCorrectButtonsAfterLanguageChange() {
        return Stream.of(
                Arguments.of(
                        Language.DE,
                        List.of("Bestseller", "Neuerscheinungen", "Amazon Basics", "Angebote", "Bücher", "Shopping-Tipps", "Mode", "Gutscheine")
                ),
                Arguments.of(
                        Language.CS,
                        List.of("Nejprodávanější", "Novinky", "Amazon Basics", "Dnešní akce", "Knihy", "Nákupní nástroje", "Móda", "Dárkové karty", "Domov a kuchyně", "Nápady na dárky", "Elektronika a fotografické potřeby")
                ),
                Arguments.of(
                        Language.NL,
                        List.of("Bestsellers", "Nieuwe uitgaven", "Amazon Basics", "Aanbiedingen", "Boeken en audioboeken", "Shopper Toolkit", "Mode", "Cadeaubonnen", "Keuken, huishouden en wonen")
                ),
                Arguments.of(
                        Language.PL,
                        List.of("Bestsellery", "Nowości", "Amazon Basics", "Okazje", "Książki", "Zestaw narzędzi dla kupujących", "Moda", "Karty podarunkowe", "Kuchnia, dom i wyposażenie wnętrz","Pomysły na prezenty")
                )
        );
    }

    @MethodSource
    @ParameterizedTest(name = "При изменении языка на {0} должны отображаться кнопки {1}")
    @Tag("Smoke")
    void amazonShouldDisplayCorrectButtonsAfterLanguageChange(Language language, List<String> expectedButtons) {
        $("#icp-nav-flyout").hover();
        $$(".nav-item").find(text(language.name())).click();
        $$("#nav-xshop a").filter(visible).shouldHave(texts(expectedButtons));
    }

}
