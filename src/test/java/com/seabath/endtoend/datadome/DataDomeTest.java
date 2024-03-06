package com.seabath.endtoend.datadome;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

public class DataDomeTest extends AbstractTDataDome {


    @Test
    @DisplayName("Check Keys Table")
    @Description("Check the presence of each column of the keys table, and click the first documentation link.")
    public void checkClientSideKeysTable() {
        String login = "premium-staff@datadome.co";
        String password = "dd-ext-138ae%!";

        AtomicReference<String> guidRef = new AtomicReference<>();
        AtomicReference<String> nameRef = new AtomicReference<>();
        AtomicReference<String> typeRef = new AtomicReference<>();
        AtomicReference<String> statusRef = new AtomicReference<>();
        AtomicReference<String> lastActivityRef = new AtomicReference<>();
        AtomicReference<String> creationDateRef = new AtomicReference<>();
        AtomicReference<String> urlRef = new AtomicReference<>();

        getDirector().start()
                .fillLogin(login)
                .fillPassword(password)
                .loginSuccessfully()
                .retrieveKeyGuid(0, guidRef)
                .retrieveName(0, nameRef)
                .retrieveType(0, typeRef)
                .retrieveStatus(0, statusRef)
                .retrieveLastActivity(0, lastActivityRef)
                .retrieveCreationDate(0, creationDateRef)
                .clickClientSideKeyDocumentation()
                .retrieveCurrentUrl(urlRef)
                .closeTab()
                .end();


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(guidRef.get()).isEqualTo("3b886ce5-e00e-45ab-92a2-0f37fc68ab83");
        softly.assertThat(nameRef.get()).isEqualTo("Client-Side key");
        softly.assertThat(typeRef.get()).isEqualTo("Client Side Key");
        softly.assertThat(statusRef.get()).isEqualTo("done\nTraffic received");
        softly.assertThat(lastActivityRef.get()).isEqualTo("Mar 5, 2024, 12:40:38 PM");
        softly.assertThat(creationDateRef.get()).isEqualTo("Apr 27, 2017, 11:53:33 AM");
        softly.assertThat(urlRef.get()).isEqualTo("https://docs.datadome.co/docs/javascript-tag");
        softly.assertAll();
    }

}
