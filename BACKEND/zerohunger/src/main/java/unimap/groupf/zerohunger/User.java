package unimap.groupf.zerohunger;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import jakarta.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import com.querydsl.core.group.Group;

import javax.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

//created when create JavaProject.SpringBoot.maven.(3.0.2).Java17.unimap.groupf.zerohunger
@Entity     //defining JPA entity class
@Table(name = " users ")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "USERS_SEQ")
    private Long id;

    @Column( name = "user_id" )   //long id; as non-public 
    private Long user_id;
    @Column( name = "userName" )      //account name of users
    private String userName;
    @Column( name = "emailAddr" )     //to reset users' password
    private String emailAddr;
    @Column( name = "phoneNumber" )     //to reset users' password
    private String phoneNumber;
    @ManyToOne
    @JoinColumn( name = "medical_conditions_id" )
    private MedicalCondition medicalCondition;
    public User( String userName, String string ) {}
    public Long getUserId(){ return user_id ; } 
    public String getName(){ return userName ; }
    public String emailAddr(){ return emailAddr ; }
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Group group;
    @OneToMany(mappedBy = "group")
    private List<User> members = new ArrayList<>();
    
}