package co.eficacia.com.rewardsapp.service;

import co.eficacia.com.rewardsapp.persistance.model.Invoice;
import co.eficacia.com.rewardsapp.persistance.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(User user);

    User getUser(UUID id);

    User getSignedInUser();

    List<User> getUsers();

    User updateUser(User user);

    boolean deleteUser(UUID id);

    void accumulatePoints(User user, Invoice invoice);
}
