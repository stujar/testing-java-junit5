package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    @Test
    void processCreationFormHasErrorsTest() {

        Owner owner = new Owner(1L, "John", "Doe");

        // Given
        given(bindingResult.hasErrors()).willReturn(true);
        // When
        String result = ownerController.processCreationForm(owner, bindingResult);

        // Then
        assertThat(result).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);

    }

    @Test
    void processCreationFormNoErrorsTest() {

        Owner savedOwner = new Owner(5L, "John", "Doe");

        // Given
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(savedOwner);
        // When
        String result = ownerController.processCreationForm(savedOwner, bindingResult);

        // Then
        assertThat(result).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }
}
