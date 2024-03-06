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
    public void checkKeysTable() {
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
                //.retrieveKeyGuid(0, guidRef)
                .retrieveName(0, nameRef)
                //.retrieveType(0, typeRef)
                .retrieveStatus(0, statusRef)
                //.retrieveLastActivity(0, lastActivityRef)
                //.retrieveCreationDate(0, creationDateRef)
                .clickKeyDocumentation(0)
                .retrieveCurrentUrl(urlRef)
                .closeTab()
                .end();


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(guidRef.get()).isEqualTo("expected GUID that should not be empty but is because column doesn't exist");
        softly.assertThat(nameRef.get()).isEqualTo("Client-Side Key");
        softly.assertThat(typeRef.get()).isEqualTo("expected type that should not be empty but is because column doesn't exist");
        softly.assertThat(statusRef.get()).isEqualTo("Traffic received");
        softly.assertThat(lastActivityRef.get()).isEqualTo("expected last activity that should not be empty but is because column doesn't exist");
        softly.assertThat(creationDateRef.get()).isEqualTo("expected creation date that should not be empty but is because column doesn't exist");
        softly.assertThat(urlRef.get()).isEqualTo("expected");
        softly.assertAll();
    }

}
