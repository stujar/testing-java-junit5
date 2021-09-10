package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;
    // Will need to dig in deeper with @Spy in api
    @Spy
    PetMapService petServiceMap;

    @InjectMocks
    VisitController visitController;

    @Test
    void setAllowedFields() {
    }

    @Test
    void loadPetWithVisit() {
        // Given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);

//        petServiceMap.save(pet);
        //given(petService.findById(anyLong())).willReturn(pet);
        given(petServiceMap.findById(anyLong())).willReturn(pet);

        // When
        Visit visit = visitController.loadPetWithVisit(1L, model);

        // Then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(1L);
    }

    @Test
    void initNewVisitForm() {
    }

    @Test
    void processNewVisitForm() {
    }
}