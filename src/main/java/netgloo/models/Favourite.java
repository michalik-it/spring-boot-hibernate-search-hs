package netgloo.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favourite")
@DiscriminatorColumn(name = "type")
@Indexed
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Favourite {

    @Id
    @GeneratedValue
    private Integer id;
    
//    @ManyToOne
//    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//    
    
}
