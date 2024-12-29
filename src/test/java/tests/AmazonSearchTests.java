package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты поиска на сайте amazon")
public class AmazonSearchTests {

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

    @ValueSource(strings = {
            "Apple Watch", "Samsung Galaxy"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдаваться не пустой список карточек")
    @Tag("Smoke")
    void searchResultsShouldNotBeEmptyTest(String searchQuery){
        $(".nav-input").setValue(searchQuery).pressEnter();
        $$("[role='listitem']").shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "iPhone, Apple",
            "Galaxy, Samsung"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отображаться бренд {1} в панели фильтров")
    @Tag("Smoke")
    void filterPanelShouldContainBrandNameTest(String searchQuery, String brandName){
        $(".nav-input").setValue(searchQuery).pressEnter();
        $("#brandsRefinements").shouldHave(text(brandName));
    }


}
