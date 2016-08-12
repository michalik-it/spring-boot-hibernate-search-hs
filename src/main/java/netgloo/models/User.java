package netgloo.models;

import netgloo.bridge.CustomeDateBridge;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Indexed
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Field(store = Store.YES)
    @NotNull
    private String email;

    @Field(store = Store.YES)
    @NotNull
    private String name;

    @Field(store = Store.YES)
    private String city;
    
    @Field(store = Store.YES)
    private String years;
    
    @Field(store = Store.YES)
    @NumericField
    private Integer yeari;
    
    @Field(store = Store.YES)
    //@DateBridge(resolution=Resolution.DAY, encoding=EncodingType.STRING)
    @FieldBridge(impl=CustomeDateBridge.class, params = @Parameter(name="resolution", value="MINUTE"))
    private Date birthDay;
    
    @ManyToOne
    @IndexedEmbedded
    private Group group;
    
    @OneToMany(mappedBy="users")
    @IndexedEmbedded
    private List<UserProject> userProject;

    public User() {}

    public User(long id) {
        this.id = id;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Integer getYeari() {
        return yeari;
    }

    public void setYeari(Integer yeari) {
        this.yeari = yeari;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<UserProject> getUserProject() {
        return userProject;
    }

    public void setUserProject(List<UserProject> userProject) {
        this.userProject = userProject;
    }

} 
