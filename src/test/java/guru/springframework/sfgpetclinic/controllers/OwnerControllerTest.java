package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                List<Owner> ownerList = new ArrayList<>();

                String name = invocation.getArgument(0);


                if ( name.equals("%Doe%") ) {
                     ownerList.add(new Owner(1L, "John", "Doe"));
                     return ownerList;
                } else if ( name.equals("%DontFindMe%")) {
                    return ownerList;
                } else if ( name.equals("%FindMe%")) {
                    ownerList.add(new Owner(1L, "John", "Doe"));
                    ownerList.add(new Owner(2L, "John", "Doe1"));
                    ownerList.add(new Owner(3L, "John", "Doe2"));
                    return ownerList;
                }
                throw new RuntimeException("Invalid Argument");
        });
    }

    @Test
    void processWildCardStrings() {
        // Given
        Owner owner = new Owner(1L, "John", "Doe");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // Then
        assertThat("%Doe%").isEqualToIgnoringCase(captor.getValue());
    }

    @Test
    void processWildCardStringsAnnotation() {
        // Given
        Owner owner = new Owner(1L, "John", "Doe");
//        List<Owner> ownerList = new ArrayList<>();
//        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // Then
        assertThat("%Doe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
        verifyZeroInteractions(model);
    }

    @Test
    void processFindFormWildcardNotFound() {
        // Given
        Owner owner = new Owner(1L, "John", "DontFindMe");
        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);
        // Then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
        verifyNoMoreInteractions(model);
    }

    @Test
    void processFindFormWildcardFound() {
        // Given
        Owner owner = new Owner(1L, "John", "FindMe");
        InOrder inOrder = inOrder(ownerService, model);
        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, model);
        // Then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        verifyNoMoreInteractions(ownerService);

        // InOrder asserts
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());
    }

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
