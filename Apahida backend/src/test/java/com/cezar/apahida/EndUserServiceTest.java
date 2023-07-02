package com.cezar.apahida;
import com.cezar.apahida.model.EndUser;
import com.cezar.apahida.repository.EndUserRepository;
import com.cezar.apahida.service.EndUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EndUserServiceTest {

    @Mock
    private EndUserRepository endUserRepository;

    @InjectMocks
    private EndUserService endUserService;

    public EndUserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        // Mock the behavior of the repository
        long id = 1L;
        EndUser expectedUser = new EndUser("Cezar", "cezar", "$2a$10$D0zp/CNOLNXByh1Bh2D6GuTuYy79vpKgssG.g3FLc.2U7ao85Y91S", "ADMIN");
        expectedUser.setId(id);

        EndUser actualUser = null;
        try {
            actualUser = endUserService.getById(id);
        } catch (Exception e) {
            fail();
        }

        // Verify the result
        assertEquals(expectedUser, actualUser);
    }
}