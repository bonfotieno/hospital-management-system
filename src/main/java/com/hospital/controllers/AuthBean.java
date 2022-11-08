package com.hospital.controllers;

import com.hospital.model.Admin;
import com.hospital.model.Auth;
import com.hospital.model.Patient;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuthBean {
    @PersistenceContext
    EntityManager em;
    public Admin loginAdmin(Auth auth) throws Exception {
        if (auth.getUsername() == null || auth.getPassword() == null)
            throw new Exception("Invalid password or username");
        String password = DigestUtils.md5Hex(auth.getPassword()); //convert the password to md5 hash

        List<Auth> auths = em.createQuery("FROM Auth a WHERE a.username=:usrName " +
                        "and a.password=:pwd", Auth.class)
                .setParameter("usrName", auth.getUsername())
                .setParameter("pwd", password)
                .getResultList();

        if (auths == null || auths.isEmpty() || auths.get(0) == null)
            throw new Exception("Invalid username or password");

        return auths.get(0).getAdmin();
    }


    public Patient loginPatient(String email, String password) {

        Patient user = null;

//        try {
//            Connection connection = dataSource.getConnection();;
//            Statement sqlStmt = connection.createStatement();
//
//            ResultSet result = sqlStmt.executeQuery("select * from patients where email='" + email + "' and " +
//                    "password='" + password + "'");
//            while (result.next()) {
//                user = new Patient();
//                user.setId((long) result.getInt("id"));
//                user.setName(result.getString("name"));
//                user.setEmail(result.getString("email"));
//                user.setAddress(result.getString(""));
//                user.setPhone(result.getString("phone"));
//                user.setReasonOfVisit(result.getString("reason_of_visit"));
//                user.setRoomNo(result.getString("room_no"));
//                user.setBedNo(result.getString("bed_no"));
//                user.setGender(result.getString("gender"));
//                user.setAge(Integer.parseInt(result.getString("age")));
//                user.setBloodGroup(result.getString("blood_group"));
//            }
//
//        }catch (Exception ex) {
//            System.out.println("Log In Error: " + ex.getMessage());
//            ex.printStackTrace();
//        }

        return user;

    }
}
