package org.capgemini.carmen.demo.webservice.controllers;

        import org.capgemini.carmen.demo.webservice.model.User;
        import org.junit.Assert;
        import org.junit.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.test.web.servlet.MockMvc;

        import java.util.Iterator;

        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.when;

public class MainControllerMOCKTest {
    @Test
    public void addNewUser() {
        MainController mainController = mock(MainController.class);
        when(mainController.addNewUser("ccccc", "ccccc@capgemini.com")).thenReturn("ccccc", "ccccc@capgemini.com");
        Iterable<User> allUsers = mainController.getAllUsers();
        for (User user : allUsers) {
            String name = user.getName();
            Assert.assertEquals("ccccc", name);
        }
    }

    @Test
    public void getAllUsers() {
        MainController mainController = mock(MainController.class);
        Iterable<User> allUsers = mainController.getAllUsers();
        Iterator<User> iterator = allUsers.iterator();

        when(mainController.getAllUsers()).thenReturn((Iterable<User>) iterator);
        for(User user : allUsers) {
            when(iterator.next()).thenReturn(user);
        }
    }

    @Test
    public void updateUser() {

    }
}
