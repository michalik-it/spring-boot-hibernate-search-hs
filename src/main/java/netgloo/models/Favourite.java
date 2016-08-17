package netgloo.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "favourite")
@DiscriminatorColumn(name = "type")
@Indexed
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Favourite {

    @Id
    @GeneratedValue
    private Integer id;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    SOLUTION 1
    
//    @Field
//    public abstract String getName();
//    
//    public abstract void setName(String name);

    
}
