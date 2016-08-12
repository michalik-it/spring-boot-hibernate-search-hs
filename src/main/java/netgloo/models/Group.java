package netgloo.models;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Indexed
@Table(name = "groups")
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Field(store = Store.YES)
    @NotNull
    private String name;
    
    @Field(store = Store.YES)
    @NotNull
    private String altName;
    
    @OneToMany(mappedBy="group")
    private List<User> users;
    
    @Field(store = Store.YES)
    private Integer extid;
    
    @Field(store = Store.YES)
    private String years;
    
    @Field(store = Store.YES)
    @NumericField
    private Integer yearb;
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Integer getExtid() {
        return extid;
    }

    public void setExtid(Integer extid) {
        this.extid = extid;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Integer getYearb() {
        return yearb;
    }

    public void setYearb(Integer yearb) {
        this.yearb = yearb;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
    
}
