package com.santhi.service;

import com.santhi.model.Address;
import com.santhi.model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressService {
    public static void addAddressesAndUpdateMembers() {
        // Open SessionFactory
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        //Session begins
        session.beginTransaction();
        //fetch existing members
        List<Member> members=session.createQuery("from Member",Member.class).getResultList();
        if(members.isEmpty()){
            System.out.println("No members found");
        }else{
            //define address mapping to members id
            Map<Long,String> addressMap=Map.of(1L,"145,oak street",
                    2L,"345,Dutch lake",
                    3L,"678,Lake street",
                    4L,"456,Tamil Street");
            //Iteate over members and assgin address
            for(Member member:members){
                String street=addressMap.get(member.getId());
                if(street != null){
                    Address address=new Address();
                    address.setStreet(street);
                    //set address in the member entity
                    member.setAddress(address);
                    //updating member
                    session.merge(member);
                }
        }

        }
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

}

