package com.ra.service;

import com.ra.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User("Nguyen Van A", 25, new Date(), "a.nguyen@example.com", "0987654321"));
        users.add(new User("Tran Thi B", 30, new Date(), "b.tran@example.com", "0912345678"));
        users.add(new User("Le Van C", 22, new Date(), "c.le@example.com", "0901234567"));
        users.add(new User("Pham Thu D", 28, new Date(), "d.pham@example.com", "0976543210"));
        users.add(new User("Hoang Van E", 35, new Date(), "e.hoang@example.com", "0965432109"));
        users.add(new User("Dang Thi F", 21, new Date(), "f.dang@example.com", "0954321098"));
        users.add(new User("Bui Van G", 40, new Date(), "g.bui@example.com", "0943210987"));
        users.add(new User("Do Thi H", 29, new Date(), "h.do@example.com", "0932109876"));
        users.add(new User("Nguyen Van I", 26, new Date(), "i.nguyen@example.com", "0921098765"));
        users.add(new User("Tran Thu K", 33, new Date(), "k.tran@example.com", "0910987654"));
        return users;
    }
}
