package unimap.groupf.zerohunger;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.group.Group;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class MedicalCondition {
    private String emailAddr;
    @ManyToOne
    @JoinColumn(name = "medical_conditions_id")
    private MedicalCondition medicalCondition;
    private String name;
    private long user_id;

    public void User(String userName, String emailAddr, MedicalCondition medicalCondition) {
        this.name = userName;
        this.emailAddr = emailAddr;
        this.medicalCondition = medicalCondition;
    }
    public Long getUserId() {
        return user_id;
    } 
    public String getName() {
        return name;
    }
    public String getEmailAddr() {
        return emailAddr;
    }
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Group group;
    @OneToMany(mappedBy = "group")
    private List<User> members = new ArrayList<>();
}
