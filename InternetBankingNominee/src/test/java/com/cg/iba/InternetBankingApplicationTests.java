package com.cg.iba;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.iba.entities.Account;
import com.cg.iba.entities.Nominee;
import com.cg.iba.entities.Relation;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.EmptyListException;
import com.cg.iba.exception.InvalidAccountException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.repository.INomineeRepository;
import com.cg.iba.service.NomineeServiceImpl;

@SpringBootTest
class InternetBankingApplicationTests {
    @Autowired
    private NomineeServiceImpl nomineeServiceImpl;

    @MockBean
    private INomineeRepository nomineeRepository;
    Account account = new Account(1, 20000.0, 3.4, LocalDate.parse("2010-01-25", DateTimeFormatter.ofPattern("yyyy-MM-d")));
    Nominee nominee = new Nominee(1, "pavithra", "123", "aadhar", "9876243413", Relation.SISTER, account);
    Nominee nominee1 = new Nominee(2, "megha", "456", "pan", "8976524324", Relation.SISTER, account);

    /*
     * @Test void contextLoads() { }
     */

    @Test
    public void testAddNominee() throws InvalidDetailsException {
        when(nomineeRepository.save(nominee)).thenReturn(nominee);
        Nominee addedNominee = null;
        addedNominee = nomineeServiceImpl.addNominee(nominee);
        assertNotNull(addedNominee);
        assertEquals(nominee, addedNominee);
    }

    @Test
    public void testAddNomineeThrowsInvalidDetailsException() {
        Nominee nominee = new Nominee(1, "", "123", "aadhar", "9876243413", Relation.SISTER, account);
        when(nomineeRepository.save(nominee)).thenReturn(nominee);
        Assertions.assertThrows(InvalidDetailsException.class, () -> {
            nomineeServiceImpl.addNominee(nominee);
        });
    }

    @Test
    public void testUpdateNominee() throws InvalidDetailsException {
        when(nomineeRepository.findById((long) 1)).thenReturn(Optional.of(nominee));
        when(nomineeRepository.save(nominee)).thenReturn(nominee);
        Nominee updatedNominee = nomineeServiceImpl.updateNominee(nominee);
        assertNotNull(updatedNominee);
        assertEquals(nominee, updatedNominee);
    }

    @Test
    public void testUpdateNomineeThrowsInvalidDetailsException() {
        Nominee nominee = new Nominee(10, "pavithra", "123", "aadhar", "9876243413", Relation.SISTER, account);
        when(nomineeRepository.findById((long) 10)).thenReturn(Optional.of(new Nominee()));
        Assertions.assertThrows(InvalidDetailsException.class, () -> {
            nomineeServiceImpl.updateNominee(nominee);
        });
    }

    @Test
    public void testFindNominee() {
        when(nomineeRepository.findById((long) 1)).thenReturn(Optional.of(nominee));
        Nominee fetchedNominee = nomineeServiceImpl.findNomineeById(1);
        assertNotNull(fetchedNominee);
        assertEquals(nominee, fetchedNominee);
    }

    @Test
    public void testFindNomineeThrowsDetailsNotFoundException() {
        Nominee nominee = new Nominee(1, "pavithra", "123", "aadhar", "9876243413", Relation.SISTER, account);
        when(nomineeRepository.findById((long) 1)).thenReturn(Optional.of(nominee));
        Assertions.assertThrows(DetailsNotFoundException.class, () -> {
            nomineeServiceImpl.findNomineeById(4);
        });

    }

    @Test
    public void testDeleteNominee() {
        when(nomineeRepository.findById((long) 1)).thenReturn(Optional.of(nominee));
        boolean deleteNominee = nomineeServiceImpl.deleteNominee(1);
        verify(nomineeRepository, times(1)).deleteById((long) 1);
        assertNotNull(deleteNominee);
        assertEquals(true, deleteNominee);
    }

    @Test
    public void testDeleteNomineeThrowsDetailsNotFoundException() {
        Nominee nominee = new Nominee(2, "pavithra", "123", "aadhar", "9876243413", Relation.SISTER, account);
        when(nomineeRepository.findById((long) 2)).thenReturn(Optional.of(nominee));
        Assertions.assertThrows(DetailsNotFoundException.class, () -> {
            nomineeServiceImpl.deleteNominee(4);
        });

    }

    @Test
    public void testListAllNominees() throws InvalidAccountException, EmptyListException {
        Set<Nominee> allNominees = new HashSet<Nominee>();
        allNominees.add(nominee);
        allNominees.add(nominee1);
        when(nomineeRepository.listAllNominees(1)).thenReturn(allNominees);
        Set<Nominee> allNomineesSet = nomineeServiceImpl.listAllNominees(1);
        assertNotNull(allNomineesSet);
        assertEquals(allNominees, allNomineesSet);
    }

    @Test
    public void testListAllNomineesThrowsEmptyListException() {
        Set<Nominee> nomineeSet = new HashSet<Nominee>();
        when(nomineeRepository.listAllNominees(1)).thenReturn(nomineeSet);
        Assertions.assertThrows(EmptyListException.class, () -> {
            nomineeServiceImpl.listAllNominees(1);
        });

    }

}
