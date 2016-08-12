package netgloo.models;

import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_projects")
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    User users;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    @IndexedEmbedded
    Project projects;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Project getProjects() {
        return projects;
    }

    public void setProjects(Project projects) {
        this.projects = projects;
    }

        
    
}
